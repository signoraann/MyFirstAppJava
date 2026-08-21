package com.signoraann.javalearning.lesson26;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new IllegalStateException(
                    "Database environment variables(DB_URL/DB_USER/DB_PASSWORD) are missing! See .env.example for"
                            + " details");
        }
        return DriverManager.getConnection(url, user, password);
    }
}
