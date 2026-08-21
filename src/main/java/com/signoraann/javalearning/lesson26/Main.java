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
            logger.info("Connected to database!");
            UserRepository userRepository = new UserRepository(connection);
            List<String> usernames = userRepository.findAllUsers();
            if (usernames.isEmpty()) {
                logger.warn("No users found in the database!");
            } else {
                logger.info("All users from database:");
                for (String username : usernames) {
                    logger.info("User {}", username);
                }
            }
            Scanner scanner = new Scanner(System.in);
            logger.info("Enter username to search");
            String userInputName = scanner.nextLine();
            String foundUser = userRepository.findUserByUsername(userInputName);
            if (foundUser == null) {
                logger.warn("User {} not found", userInputName);
            } else {
                logger.info("Found user: {}", foundUser);
            }

        } catch (SQLException e) {
            logger.error("Database error occurred", e);
        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
        }
    }
}