package com.xu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginUtils {

    static Logger logger = LoggerFactory.getLogger(LoginUtils.class);

    public static boolean check(String name, String pwd) {
/*        try {
            User u = UserDao.getUserByAccount(name);

            if (u.getPassword().equals(pwd))
                return true;
        } catch (SQLException e) {
            logger.error("db err", e);
        } catch (Exception e) {
            logger.error("err", e);
        }
        return false;*/
        logger.debug(name+pwd);
        return true;
    }
}
