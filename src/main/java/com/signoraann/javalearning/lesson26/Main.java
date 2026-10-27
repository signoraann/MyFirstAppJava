package com.signoraann.javalearning.lesson26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    static void main() {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            logger.info("Connected to database!");
            List<String> usernames = userRepository.findAllUsernames();
            if (usernames.isEmpty()) {
                logger.warn("Database is empty. Program has closed.");
                return;
            }
            printAllUsernamesFromDatabase(usernames);
            logger.info("Enter username to search User in database: ");
            Scanner scanner = new Scanner(System.in);
            String userInputName = scanner.nextLine();
            userRepository
                    .findUserByUsername(userInputName)
                    .ifPresentOrElse(
                            user -> logger.info("Found user: {}", user),
                            () -> logger.warn("User {} not found", userInputName));
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
