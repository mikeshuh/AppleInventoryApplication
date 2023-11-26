package com.example.appleinventoryapp;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/AppleInventory";
    private static final String USER = "root";
    private static final String PASSWORD = "ryantang";
    private static Connection connection;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void executeSQL(String sql) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String sql) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}