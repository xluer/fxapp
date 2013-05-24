package com.xu.db;

import com.xu.model.User;
import com.xu.util.DBManager;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {

    private static BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);

    public static User getUserById(Long id) throws SQLException {
        Connection con = DBManager.getConnection();
        QueryRunner run = new QueryRunner();
        try {
            return run.query(con, "SELECT * FROM user WHERE id = ?", userBeanHandler, id);
        } finally {
            DbUtils.close(con);
        }
    }

    public static User getUserByAccount(String account) throws SQLException {
        Connection con = DBManager.getConnection();
        QueryRunner run = new QueryRunner();
        try {
            return run.query(con, "SELECT * FROM user WHERE account = ?", userBeanHandler, account);
        } finally {
            DbUtils.close(con);
        }
    }
}
