package com.signoraann.javalearning.lesson26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String userInputName = "Ann";
        String sql = "SELECT username FROM users WHERE username = ?";
        try (Connection connection = getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            logger.info("Connected to Database!");
            preparedStatement.setString(1, userInputName);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    do {
                        String username = result.getString("username");
                        logger.info("User: {}", username);
                    } while (result.next());
                } else {
                    logger.warn("No users found in the database!");
                }
            }

        } catch (SQLException e) {
            logger.error("Database error occurred", e);
        }
    }
}
