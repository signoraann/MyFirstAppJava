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
        // openConnections();
        UserGenerator userGenerator = new UserGenerator();
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection()) {
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
            List<User> foundUsers = userRepository.findUsersByPartOfUsername(userInputName);
            if (foundUsers.isEmpty()) {
                logger.warn("User {} not found", userInputName);
            } else {
                logger.info("Found users: {}", foundUsers);
            }
            List<User> usersBatch = userGenerator.generateUsers(100);
            int[] result = userRepository.addUsersInDatabase(usersBatch);
            logger.info("Users successfully added to the database, {}!", result);
            scanner.close();
        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        }
    }

    /*private static void openConnections() {
        try (DatabaseManager databaseManager = new DatabaseManager()) {
            for (int i = 1; i <= 20; i++) {
                Connection connection = databaseManager.getConnection();
                logger.info("Connection {} opened", i);
            }
        } catch (SQLException e) {
            logger.error("Connection failed. {}", e.getMessage());
        }
    }*/
}
