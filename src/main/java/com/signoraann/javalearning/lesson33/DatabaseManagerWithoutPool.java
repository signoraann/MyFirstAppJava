package com.signoraann.javalearning.lesson33;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.signoraann.javalearning.lesson26.DatabaseManager;

public class DatabaseManagerWithoutPool {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        DatabaseManager.checkEnvironmentVariables(url, user, password);
        return DriverManager.getConnection(url, user, password);
    }
}
