package com.xu.db;

import com.xu.model.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.xu.util.DBConfig.*;

public class UserDao {

    private static BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);

    public static User getUserById(Long id) throws SQLException {
        Connection con = DriverManager.getConnection(url, uid, pwd);
        QueryRunner run = new QueryRunner();
        try {
            return run.query(con, "SELECT * FROM user WHERE id = ?", userBeanHandler, id);
        } finally {
            DbUtils.close(con);
        }
    }

    public static User getUserByAccount(String account) throws SQLException {
        Connection con = DriverManager.getConnection(url, uid, pwd);
        QueryRunner run = new QueryRunner();
        try {
            return run.query(con, "SELECT * FROM user WHERE account = ?", userBeanHandler, account);
        } finally {
            DbUtils.close(con);
        }
    }
}
