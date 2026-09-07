package com.signoraann.javalearning.lesson26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (Connection connection = DatabaseManager.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            logger.info("Connected to database!");
            List<String> usernames = userRepository.findAllUsernames();
            printAllUsernamesFromDatabase(usernames);
            if (usernames.isEmpty()) {
                logger.warn("Database is empty. Program has closed.");
                return;
            }
            logger.info("Enter username to search User in database: ");
            Scanner scanner = new Scanner(System.in);
            String userInputName = scanner.nextLine();
            User foundUser = userRepository.findUserByUsername(userInputName).orElse(null);
            if (foundUser == null) {
                logger.warn("User {} not found", userInputName);
            } else {
                logger.info("Found user: {}", foundUser);
            }
        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        }
    }

    private static void printAllUsernamesFromDatabase(List<String> usernames) {
        logger.info("All users from database:");
        for (String username : usernames) {
            logger.info("User {}", username);
        }
    }
}
