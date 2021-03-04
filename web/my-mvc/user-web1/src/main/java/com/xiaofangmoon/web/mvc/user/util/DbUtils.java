package com.xiaofangmoon.web.mvc.user.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: xiaofang
 * @createTime: 2021/03/03 22:23
 * @description:
 */
public class DbUtils {

    public static final String url = "jdbc:mysql://127.0.0.1:3306/user_platform?useSSL=false";
    public static final String username = "root";
    public static final String password = "Fang#123456";

    public static final String driver = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
