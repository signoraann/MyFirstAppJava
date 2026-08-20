package com.signoraann.javalearning.lesson26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            logger.error("Database environment variables are not set!");
            return;
        }

        String sqlAllUsers = "SELECT username FROM users";
        try (Connection connection = getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlAllUsers)) {
            logger.info("Connected to Database!");
            try (ResultSet result = preparedStatement.executeQuery()) {
                boolean hasUsers = false;
                logger.info("All users from database:");
                while (result.next()) {
                    hasUsers = true;
                    String username = result.getString("username");
                    logger.info("User: {}", username);
                }
                if (hasUsers == false) {
                    logger.warn("No users found in the database!");
                }
            }
            Scanner scanner = new Scanner(System.in);
            logger.info("Enter username to search");
            String userInputName = scanner.nextLine();
            String sqlSearchUserByName = "SELECT username FROM users WHERE username = ?";
            try (PreparedStatement preparedStatementSearchByName = connection.prepareStatement(sqlSearchUserByName)) {
                preparedStatementSearchByName.setString(1, userInputName);
                try (ResultSet resultSearchByName = preparedStatementSearchByName.executeQuery()) {
                    if (resultSearchByName.next()) {
                        logger.info("Found user: {}", resultSearchByName.getString("username"));
                    } else {
                        logger.warn("User {} not found!", userInputName);
                    }
                }
            }

        } catch (SQLException e) {
            logger.error("Database error occurred", e);
        }
    }
}
