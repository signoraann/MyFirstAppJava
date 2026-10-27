package com.signoraann.javalearning.lesson32;

// import com.signoraann.javalearning.lesson26.User;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson28.UserDao;
import com.signoraann.javalearning.lesson28.UserDaoJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    static void main() {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection();
                Scanner scanner = new Scanner(System.in)) {
            logger.info("Connected to database!");
            UserDao userDao = new UserDaoJdbc(connection);
            userDao.findAllUsernames();
            logger.info("Enter users id to search: ");
            while (!scanner.hasNextLong()) {
                logger.warn("id should be a number! Try again:)");
                scanner.next();
            }
            long searchId = scanner.nextLong();
            userDao.findUserById(searchId)
                    .ifPresentOrElse(
                            user -> logger.info("Found user: {}", user),
                            () -> logger.warn("User with id = {} not found", searchId));
            // typo for task3
            // User user = new User(null, "Ann", "signoraann@gmail.com", 19, null);
            // userDao.saveUser(user);
            Map<String, List<String>> foundUsesPosts = userDao.findAllUsersPosts();
            logger.info("Users with their posts: {}", foundUsesPosts);
        } catch (SQLException | IllegalStateException e) {
            logger.error("Operation failed: ", e);
        }
    }
}
