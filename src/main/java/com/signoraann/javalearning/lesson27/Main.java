package com.signoraann.javalearning.lesson27;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson26.User;
import com.signoraann.javalearning.lesson26.UserRepository;
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
            if (usernames.isEmpty()) {
                logger.warn("Database is empty. Program has closed.");
                return;
            }
            logger.info("Enter username to search User in database: ");
            Scanner scanner = new Scanner(System.in);
            String userInputName = scanner.nextLine();
            List<User> foundUsers = userRepository.findUserByPartOfUsername(userInputName);
            if (foundUsers.isEmpty()) {
                logger.warn("User {} not found", userInputName);
            } else {
                printUsersFoundByPartOfUsername(foundUsers);
            }
        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        }
    }

    private static void printUsersFoundByPartOfUsername(List<User> foundUsers) {
        logger.info("Found users: {}", foundUsers);
    }
}
