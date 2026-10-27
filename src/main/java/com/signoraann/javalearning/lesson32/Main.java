package com.signoraann.javalearning.lesson32;

import com.signoraann.javalearning.lesson26.User;

import com.signoraann.javalearning.lesson26.DatabaseManager;
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
            var userDao = new UserDaoJdbc(connection);
            // typo for task3
            // User user = new User(null, "Ann", "signoraann@gmail.com", 19, null);
            // userDao.saveUser(user);
            String menuChoice = scanner.next();
            String message = switch (menuChoice) {
                case "1" -> {
                    logger.info("Enter username to a new user: ");
                    var username = scanner.next();
                    logger.info("Enter email to a new user: ");
                    var email = scanner.next();
                    logger.info("Enter age to a new user: ");
                    var age = scanner.nextInt();
                    var newUser = new User(null, username, email, age, null);
                    userDao.saveUser(newUser);
                    logger.info("New user successfully saved");
                    yield "Save user";
                }
                case "2" -> {
                    logger.info("Enter username to search user: ");
                    var searchUsername = scanner.next();
                    userDao.findUserByUsername(searchUsername)
                            .ifPresentOrElse(
                                    user -> logger.info("Found user: {}", user),
                                    () -> logger.warn("User with username = {} not found", searchUsername));
                    yield "Find user by username";
                }
                case "3" -> {
                    logger.info("Enter users id to search: ");
                    while (!scanner.hasNextLong()) {
                        logger.warn("id should be a number! Try again:)");
                        scanner.next();
                    }
                    var searchId = scanner.nextLong();
                    userDao.findUserById(searchId)
                            .ifPresentOrElse(
                                    user -> logger.info("Found user: {}", user),
                                    () -> logger.warn("User with id = {} not found", searchId));
                    yield "Find user by id";
                }
                case "4" -> {
                    logger.info("Enter username to delete user: ");
                    var deleteUserByUsername = scanner.next();
                    userDao.deleteByUsername(deleteUserByUsername);
                    logger.info("User with username = {} successfully deleted", deleteUserByUsername);
                    yield "Delete user by username";
                }
                case "5" -> {
                    List<String> allUsernames = userDao.findAllUsernames();
                    logger.info("All usernames: {}", allUsernames);
                    yield "Find all usernames";
                }
                case "6" -> {
                    Map<String, List<String>> foundUsesPosts = userDao.findAllUsersPosts();
                    logger.info("Users with their posts: {}", foundUsesPosts);
                    yield "Find all users posts";
                }
                case "7" -> "Close program";
                default -> "Unknown menu option";
            };
            logger.info("{}", message);
        } catch (SQLException | IllegalStateException e) {
            logger.error("Operation failed: ", e);
        }
    }
}
