package com.codegym.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JbdcConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONN_URL = "jdbc:mysql://localhost:3306/customer_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
            return connection = null;
        } catch (ClassNotFoundException var2) {
            System.out.println("Could not find driver for jdbc connection!");
        } catch (SQLException var3) {
            System.out.println("Could not find database!");
        }
        return connection;
    }
}
