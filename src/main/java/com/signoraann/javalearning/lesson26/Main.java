package com.signoraann.javalearning.lesson26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            return;
        }
        try (Connection connection = getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT users.username FROM users")) {
            logger.info("Connected to Database!");
            if (result.next()) {
                do {
                    String username = result.getString("username");
                    logger.info("User: {}", username);
                } while (result.next());
            } else {
                logger.warn("No users found in the database!");
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
