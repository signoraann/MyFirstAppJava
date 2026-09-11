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
            List<User> usersBatch = List.of(
                    new User(null, "user2", "user2@mail.com", 19),
                    new User(null, "user3", "user3@gmail.com", 66),
                    new User(null, "user4", "user4", 45));
            int[] result = userRepository.addUsersInDatabase(usersBatch);
            logger.info("Users successfully added to the database!", result);
        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        }
    }

    private static void printUsersFoundByPartOfUsername(List<User> foundUsers) {
        logger.info("Found users: {}", foundUsers);
    }

    /*private static void openConnections() {
        try {
            for (int i = 1; i <= 200000; i++) {
                Connection connection = DatabaseManager.getConnection();
                logger.info("Connection {} opened", i);
            }
        } catch (SQLException e) {
            logger.error("Connection failed. {}", e.getMessage());
        }
    }*/
}
