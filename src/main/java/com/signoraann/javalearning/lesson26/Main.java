package com.signoraann.javalearning.lesson26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static void main() {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            LOGGER.info("Connected to database!");
            List<String> usernames = userRepository.findAllUsernames();
            if (usernames.isEmpty()) {
                LOGGER.warn("Database is empty. Program has closed.");
                return;
            }
            printAllUsernamesFromDatabase(usernames);
            LOGGER.info("Enter username to search User in database: ");
            Scanner scanner = new Scanner(System.in);
            String userInputName = scanner.nextLine();
            userRepository
                    .findUserByUsername(userInputName)
                    .ifPresentOrElse(
                            user -> LOGGER.info("Found user: {}", user),
                            () -> LOGGER.warn("User {} not found", userInputName));
        } catch (SQLException e) {
            LOGGER.error("Database error: {}", e.getMessage());
        }
    }

    private static void printAllUsernamesFromDatabase(List<String> usernames) {
        LOGGER.info("All users from database:");
        for (String username : usernames) {
            LOGGER.info("User {}", username);
        }
    }
}
