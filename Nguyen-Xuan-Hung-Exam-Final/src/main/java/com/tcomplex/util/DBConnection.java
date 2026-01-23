package com.tcomplex.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONN_URL = "jdbc:mysql://localhost:3306/tcomplex?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find driver for jdbc connection!");
        } catch (SQLException e) {
            System.out.println("Could not find database!");
        }
        return connection;
    }
}