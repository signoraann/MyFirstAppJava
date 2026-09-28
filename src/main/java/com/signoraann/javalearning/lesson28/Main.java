package com.signoraann.javalearning.lesson28;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson26.User;
import com.signoraann.javalearning.lesson27.UserGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection();
                Scanner scanner = new Scanner(System.in)) {
            logger.info("Connected to database!");
            UserDao userDao = new UserDaoJdbc(connection);
            UserGenerator userGenerator = new UserGenerator();
            User newUser = userGenerator.generateUsers(1).getFirst();
            userDao.saveUser(newUser);
            logger.info("Enter id to search User in database:");
            while (!scanner.hasNextLong()) {
                logger.warn("Id should be a number! Try again:");
                scanner.next();
            }
            Long id = scanner.nextLong();
            Optional<User> userOptional = userDao.findUserById(id);
            userOptional.ifPresentOrElse(
                    user -> logger.info("User found: {}", user), () -> logger.warn("User not found!"));

        } catch (SQLException | IllegalStateException e) {
            logger.error("Operation failed: ", e);
        }
    }
}
