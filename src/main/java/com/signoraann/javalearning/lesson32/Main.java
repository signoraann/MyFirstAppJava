package com.signoraann.javalearning.lesson32;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson26.User;
import com.signoraann.javalearning.lesson28.UserDao;
import com.signoraann.javalearning.lesson28.UserDaoJdbc;
import com.signoraann.javalearning.lesson34.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static void main() {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection();
                Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Connected to database!");
            var userDao = new UserDaoJdbc(connection);
            // typo for task3
            // User user = new User(null, "Ann", "signoraann@gmail.com", 19, null);
            // userDao.saveUser(user);
            String menuChoice;
            do {
                LOGGER.info("""
                    Menu:
                    1. Save user
                    2. Find user by username
                    3. Find user by id
                    4. Delete user by email
                    5. Find all usernames
                    6. Find all users posts
                    7. Find all users
                    8. Sort all users by username
                    9. Task 9 (Сomparing objects using ==, equals() and contains())
                    10. Exit
                    Enter an option:
                    """);

                menuChoice = scanner.next();
                try {
                    String message = switch (menuChoice) {
                        case "1" -> {
                            handleSaveUser(scanner, userDao);
                            yield "Save user";
                        }
                        case "2" -> {
                            handleFindUserByUsername(scanner, userDao);
                            yield "Find user by username";
                        }
                        case "3" -> {
                            handleFindUserById(scanner, userDao);
                            yield "Find user by id";
                        }
                        case "4" -> {
                            handleDeleteUserByEmail(scanner, userDao);
                            yield "Delete user by email";
                        }
                        case "5" -> {
                            handleFindAllUsernames(userDao);
                            yield "Find all usernames";
                        }
                        case "6" -> {
                            handleFindAllUsersPosts(userDao);
                            yield "Find all users posts";
                        }
                        case "7" -> {
                            handleFindAllUsers(scanner, userDao);
                            yield "Find all users and output";
                        }
                        case "8" -> {
                            sortAllUsersTwoTimes(userDao);
                            yield "Sort all users by username (if usernames are the same by id)";
                        }
                        case "9" -> {
                            compareTwoUsersThreeWays();
                            yield "Comparing objects using ==, equals() and contains() (Task 9, lesson 32)";
                        }
                        case "10" -> "Close program";
                        default -> "Unknown menu option";
                    };
                    LOGGER.info("{}", message);
                } catch (UserNotFoundException exception) {
                    LOGGER.error(exception.getMessage());
                } catch (IllegalStateException e) {
                    LOGGER.warn("Operation failed!", e);
                }
            } while (!"10".equals(menuChoice));
        } catch (SQLException | IllegalStateException e) {
            LOGGER.error("Operation failed: ", e);
        }
    }

    private static long readUsersInputId(Scanner scanner) {
        LOGGER.info("Enter users id to search: ");
        while (!scanner.hasNextLong()) {
            LOGGER.warn("id should be a number! Try again:)");
            scanner.next();
        }
        return scanner.nextLong();
    }

    private static void handleSaveUser(Scanner scanner, UserDao userDao) {
        LOGGER.info("Enter username to a new user: ");
        var username = scanner.next();
        LOGGER.info("Enter email to a new user: ");
        var email = scanner.next();
        LOGGER.info("Enter age to a new user: ");
        while (!scanner.hasNextInt()) {
            LOGGER.warn("Age should be int! Try again:)");
            scanner.next();
        }
        var age = scanner.nextInt();
        var newUser = new User(null, username, email, age, null);
        userDao.saveUser(newUser);
        LOGGER.info("New user successfully saved");
    }

    private static void handleFindUserByUsername(Scanner scanner, UserDao userDao) {
        LOGGER.info("Enter username to search user: ");
        var searchUsername = scanner.next();
        List<User> foundUsers = userDao.findUsersByUsername(searchUsername);
        LOGGER.info("Found users by username: {}", foundUsers);
    }

    private static void handleFindUserById(Scanner scanner, UserDao userDao) {
        var searchId = readUsersInputId(scanner);
        userDao.findUserById(searchId).ifPresent(user -> LOGGER.info("Found user by id={}: {}", searchId, user));
    }

    private static void handleDeleteUserByEmail(Scanner scanner, UserDao userDao) {
        LOGGER.info("Enter email to delete user: ");
        var deleteUserByEmail = scanner.next();
        if (userDao.deleteByEmail(deleteUserByEmail)) {
            LOGGER.info("User with email = {} successfully deleted", deleteUserByEmail);
        } else {
            LOGGER.warn("User with email = {} not found", deleteUserByEmail);
        }
    }

    private static void handleFindAllUsers(Scanner scanner, UserDao userDao) {
        List<User> allUsers = userDao.findAllUsers();
        Set<User> allUniqueUsers = new HashSet<>(allUsers);
        LOGGER.info("First adding all users: {}", allUniqueUsers.size());
        allUsers = userDao.findAllUsers();
        allUniqueUsers.addAll(allUsers);
        LOGGER.info("Second adding all users: {}", allUniqueUsers.size());
        LOGGER.info("All unique users: {}", allUniqueUsers);
        Map<Long, User> usersById = new HashMap<>();
        for (User user : allUniqueUsers) {
            usersById.put(user.id(), user);
        }
        var searchId = readUsersInputId(scanner);
        User foundUserById = usersById.get(searchId);
        LOGGER.info("User with id {}: {}", searchId, foundUserById);
    }

    private static void sortAllUsersTwoTimes(UserDao userDao) {
        List<User> allUsers = userDao.findAllUsers();
        Comparator<User> byUsernameThenId = Comparator.comparing(User::username).thenComparing(User::id);
        LOGGER.info("Sort users by usernames");
        allUsers.stream().sorted(byUsernameThenId).forEach(user -> LOGGER.info("{}", user));
        LOGGER.info("Sort users by usernames (reversed)");
        allUsers.stream().sorted(byUsernameThenId.reversed()).forEach(user -> LOGGER.info("{}", user));
    }

    private static void compareTwoUsersThreeWays() {
        var user1 = new UserFake("name", 1L);
        var user2 = new UserFake("name1", 1L);
        LOGGER.info("Comparison with ==: {}", user1 == user2);
        LOGGER.info("Comparison with equals(): {}", user1.equals(user2));
        List<UserFake> users = new ArrayList<>();
        users.add(user1);
        LOGGER.info("List contains user2: {}", users.contains(user2));
    }

    private static void handleFindAllUsernames(UserDao userDao) {
        List<String> allUsernames = userDao.findAllUsernames();
        LOGGER.info("All usernames: {}", allUsernames);
    }

    private static void handleFindAllUsersPosts(UserDao userDao) {
        Map<String, List<String>> foundUsesPosts = userDao.findAllUsersPosts();
        LOGGER.info("Users with their posts: {}", foundUsesPosts);
    }
}
