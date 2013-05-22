package com.xu.db;

import com.xu.model.Customer;
import com.xu.util.Cn2Spell;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.xu.util.DbConfig.*;

public class CustomerDao {
    private static BeanListHandler<Customer> hdr = new BeanListHandler<>(Customer.class);

    public static List<Customer> getCustomerListByName(String name) throws SQLException {
        if (name == null || name.length() < 1) return new ArrayList<>();
        String spell = Cn2Spell.converterToFirstSpell(name);
        Connection con = DriverManager.getConnection(url, uid, pwd);
        QueryRunner run = new QueryRunner();
        try {
            return run.query(con, "SELECT * FROM customer WHERE name like ? OR nameSpell like ?", hdr,
                    "%" + name + "%", "%" + spell + "%");
        } finally {
            DbUtils.close(con);
        }
    }
}
