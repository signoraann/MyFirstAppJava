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
            boolean hasUsers = printAllUsernamesFromDatabase(usernames);
            if (!hasUsers) {
                logger.warn("Database is empty. Program has closed.");
                return;
            }
            logger.info("Enter username to search User in database: ");
            Scanner scanner = new Scanner(System.in);
            String userInputName = scanner.nextLine();
            User foundUser = userRepository.findUserByUsername(userInputName).orElse(null);
            printUserSearchingByUsername(foundUser, userInputName);
        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        }
    }

    public static boolean printAllUsernamesFromDatabase(List<String> usernames) {
        if (usernames.isEmpty()) {
            logger.warn("No users found in the database!");
            return false;
        } else {
            logger.info("All users from database:");
            for (String username : usernames) {
                logger.info("User {}", username);
            }
            return true;
        }
    }

    public static boolean printUserSearchingByUsername(User user, String searchName) {

        if (user == null) {
            logger.warn("User {} not found", searchName);
            return false;
        } else {
            logger.info("Found user: {}", user);
            return true;
        }
    }
}
