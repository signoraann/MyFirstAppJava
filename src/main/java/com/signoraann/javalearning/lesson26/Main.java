package com.signoraann.javalearning.lesson26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (Connection connection = DatabaseManager.getConnection()) {
            logger.info("Connected to database!");
            UserRepository userRepository = new UserRepository(connection);
            if (!getAllUsernamesFromDatabase(userRepository)) {
                return;
            }
            Scanner scanner = new Scanner(System.in);
            searchUserInDatabase(userRepository, scanner);

        } catch (SQLException e) {
            logger.error("Database error occurred", e);
        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
        }
    }

    private static boolean getAllUsernamesFromDatabase(UserRepository userRepository) throws SQLException {
        List<String> usernames = userRepository.findAllUsernames();
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

    private static void searchUserInDatabase(UserRepository userRepository, Scanner scanner) throws SQLException {
        logger.info("Enter username to search");
        String userInputName = scanner.nextLine();
        Optional<User> foundUser = userRepository.findUserByUsername(userInputName);
        if (foundUser.isEmpty()) {
            logger.warn("User {} not found", userInputName);
        } else {
            logger.info("Found user: {}", foundUser);
        }
    }
}
