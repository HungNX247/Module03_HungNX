package com.codegym.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONN_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(CONN_URL,USERNAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
