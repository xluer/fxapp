package com.xu.db;

import com.xu.model.RoomReserve;
import com.xu.util.DBManager;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomHistoryDao {

    private static BeanListHandler<RoomReserve> roomHistoryBeanListHandler = new BeanListHandler<>(RoomReserve.class);

    public static List<RoomReserve> getReserveList() throws SQLException{
        Connection con = DBManager.getConnection();
        QueryRunner run = new QueryRunner();
        try {
            return run.query(con, "SELECT * FROM reserveList WHERE state = 0", roomHistoryBeanListHandler);
        } finally {
            DbUtils.close(con);
        }
    }
}
