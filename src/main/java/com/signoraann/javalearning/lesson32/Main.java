package com.signoraann.javalearning.lesson32;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson26.User;
import com.signoraann.javalearning.lesson28.UserDaoJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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
            logger.info("""
                Menu:
                1. Save user
                2. Find user by username
                3. Find user by id
                4. Delete user by username
                5. Find all usernames
                6. Find all users posts
                7. Find all users
                8. Sort all users by username
                9. Exit
                Enter an option:
                """);
            String menuChoice = scanner.next();
            String message = switch (menuChoice) {
                case "1" -> {
                    logger.info("Enter username to a new user: ");
                    var username = scanner.next();
                    logger.info("Enter email to a new user: ");
                    var email = scanner.next();
                    logger.info("Enter age to a new user: ");
                    var age = scanner.nextInt();
                    var newUser = new com.signoraann.javalearning.lesson26.User(null, username, email, age, null);
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
                                    user -> logger.info("Found user by id={}: {}", searchId, user),
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
                case "7" -> {
                    Set<User> allUniqueUsers = new HashSet<>();
                    List<User> allUsers = userDao.findAllUsers();
                    allUniqueUsers.addAll(allUsers);
                    logger.info("First adding all users: {}", allUniqueUsers.size());
                    allUsers = userDao.findAllUsers();
                    allUniqueUsers.addAll(allUsers);
                    logger.info("Second adding all users: {}", allUniqueUsers.size());
                    logger.info("All unique users: {}", allUniqueUsers);
                    Map<Long, User> usersById = new HashMap<>();
                    for (User user : allUniqueUsers) {
                        usersById.put(user.id(), user);
                    }
                    logger.info("Enter users id to search: ");
                    while (!scanner.hasNextLong()) {
                        logger.warn("id should be a number! Try again:)");
                        scanner.next();
                    }
                    var searchId = scanner.nextLong();
                    User foundUserById = usersById.get(searchId);
                    logger.info("User with id {}: {}", searchId, foundUserById);
                    yield "Find all users and output ";
                }
                case "8" -> {
                    List<User> allUsers = userDao.findAllUsers();
                    logger.info("Sort users by usernames");
                    allUsers.stream()
                            .sorted(Comparator.comparing(User::username).thenComparing(User::id))
                            .forEach(user -> logger.info("{}", user));
                    logger.info("Sort users by usernames (reversed)");
                    allUsers.stream()
                            .sorted(Comparator.comparing(User::username)
                                    .thenComparing(User::id)
                                    .reversed())
                            .forEach(user -> logger.info("{}", user));

                    yield "Sort all users by username (usernames = )";
                }
                case "9" -> "Close program";
                default -> "Unknown menu option";
            };
            logger.info("{}", message);

            userDao.findUserById(1L);
            var user1 = new UserFake("name", 1L);
            var user2 = new UserFake("name1", 1L);
            logger.info("Comparison with ==: {}", user1 == user2);
            logger.info("Comparison with equals(): {}", user1.equals(user2));
            List<UserFake> users = new ArrayList<>();
            users.add(user1);
            logger.info("List contains user2: {}", users.contains(user2));

        } catch (SQLException | IllegalStateException e) {
            logger.error("Operation failed: ", e);
        }
    }
}
