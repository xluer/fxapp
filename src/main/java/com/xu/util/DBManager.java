package com.xu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import static com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource;

/**
 * 数据库管理
 */
public class DBManager {

    private static Logger log = LoggerFactory.getLogger(DBManager.class);
    private static ThreadLocal<Connection> cons = new ThreadLocal<>();
    private static DataSource dataSource;
    private static boolean show_sql = false;

    static {
        initDataSource(null);
    }

    /**
     * 初始化连接池
     *
     * @param dbProperties 连接池配置
     */
    private static void initDataSource(Properties dbProperties) {
        log.info("data source init");
        try {
            if (dbProperties == null) {
                dbProperties = new Properties();
                dbProperties.load(DBManager.class.getResourceAsStream("/db.properties"));
            }

            dataSource = createDataSource(dbProperties);
            Connection conn = getConnection();
            DatabaseMetaData mdm = conn.getMetaData();
            log.info("Connected to " + mdm.getDatabaseProductName() +
                    " " + mdm.getDatabaseProductVersion());
            closeConnection();
        } catch (Exception e) {
            log.error("data source init fail", e);
            //throw new DBException(e);
        }
    }

    /**
     * 断开连接池
     */
    public static void closeDataSource() {
        try {
            dataSource.getClass().getMethod("close").invoke(dataSource);
        } catch (NoSuchMethodException ignored) {
        } catch (Exception e) {
            log.error("Unabled to destroy DataSource!!! ", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = cons.get();
        if (conn == null || conn.isClosed()) {
            conn = dataSource.getConnection();
            cons.set(conn);
        }
        return (show_sql && !Proxy.isProxyClass(conn.getClass())) ?
                new _DebugConnection(conn).getConnection() : conn;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        Connection conn = cons.get();
        try {
            if (conn != null && !conn.isClosed()) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            log.error("Unabled to close connection!!! ", e);
        }
        cons.set(null);
    }

    /**
     * 用于跟踪执行的SQL语句
     *
     * @author Winter Lau
     */
    static class _DebugConnection implements InvocationHandler {

        private static Logger log = LoggerFactory.getLogger(_DebugConnection.class);

        private Connection conn = null;

        public _DebugConnection(Connection conn) {
            this.conn = conn;
        }

        /**
         * Returns the conn.
         *
         * @return Connection
         */
        public Connection getConnection() {
            return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),
                    conn.getClass().getInterfaces(), this);
        }

        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
            try {
                String method = m.getName();
                if ("prepareStatement".equals(method) || "createStatement".equals(method))
                    log.info("[SQL] >>> " + args[0]);
                return m.invoke(conn, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        }

    }

}
