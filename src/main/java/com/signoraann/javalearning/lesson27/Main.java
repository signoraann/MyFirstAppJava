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
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static void main() {
        // openConnections();
        UserGenerator userGenerator = new UserGenerator();
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            LOGGER.info("Connected to database!");
            LOGGER.info("Enter username to search User in database: ");
            Scanner scanner = new Scanner(System.in);
            String userInputName = scanner.nextLine();
            List<User> foundUsers = userRepository.findUsersByPartOfUsername(userInputName);
            if (foundUsers.isEmpty()) {
                LOGGER.warn("User {} not found", userInputName);
            } else {
                LOGGER.info("Found users: {}", foundUsers);
            }
            List<User> usersBatch = userGenerator.generateUsers(100);
            int[] result = userRepository.addUsersInDatabase(usersBatch);
            LOGGER.info("Users successfully added to the database! The number of added users: {}.", result.length);
            scanner.close();
        } catch (SQLException e) {
            LOGGER.error("Database error: {}", e.getMessage());
        }
    }

    /*private static void openConnections() {
        try (DatabaseManager databaseManager = new DatabaseManager()) {
            for (int i = 1; i <= 20; i++) {
                Connection connection = databaseManager.getConnection();
                LOGGER.info("Connection {} opened", i);
            }
        } catch (SQLException e) {
            LOGGER.error("Connection failed. {}", e.getMessage());
        }
    }*/
}
