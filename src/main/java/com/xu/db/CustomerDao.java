package com.xu.db;

import com.xu.model.Customer;
import com.xu.util.Cn2Spell;
import com.xu.util.DBManager;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static BeanListHandler<Customer> hdr = new BeanListHandler<>(Customer.class);

    public static List<Customer> getCustomerListByName(String name) throws SQLException {
        if (name == null || name.length() < 1) return new ArrayList<>();
        String spell = Cn2Spell.converterToFirstSpell(name);
        Connection con = DBManager.getConnection();
        QueryRunner run = new QueryRunner();
        try {
            return run.query(con, "SELECT * FROM customer WHERE name like ? OR nameSpell like ?", hdr,
                    "%" + name + "%", "%" + spell + "%");
        } finally {
            DbUtils.close(con);
        }
    }
}
