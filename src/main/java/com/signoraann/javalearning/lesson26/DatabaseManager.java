package com.signoraann.javalearning.lesson26;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager implements AutoCloseable {
    private final HikariDataSource dataSource;

    public DatabaseManager() {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        checkEnvironmentVariables(url, user, password);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(5);
        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    static void checkEnvironmentVariables(String url, String user, String password) {
        checkVariable("DB_URL", url);
        checkVariable("DB_USER", user);
        checkVariable("DB_PASSWORD", password);
    }

    private static void checkVariable(String name, String value) {
        if (value == null) {
            throw new IllegalStateException(
                    String.format("Database environment variable %s is missing! See README.md for details", name));
        }
    }

    @Override
    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
