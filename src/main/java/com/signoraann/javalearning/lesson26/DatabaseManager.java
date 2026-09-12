package com.signoraann.javalearning.lesson26;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final HikariDataSource dataSource;

    static {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        checkEnvironmentVariables(url, user, password);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(5);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    static void checkEnvironmentVariables(String url, String user, String password) {
        if (url == null) {
            throw new IllegalStateException(
                    "Database environment variables DB_URL is missing! See README.md for" + " details");
        }
        if (user == null) {
            throw new IllegalStateException(
                    "Database environment variables DB_USER is missing! See README.md for" + " details");
        }
        if (password == null) {
            throw new IllegalStateException(
                    "Database environment variable DB_PASSWORD is missing! See README.md for" + " details");
        }
    }
}
