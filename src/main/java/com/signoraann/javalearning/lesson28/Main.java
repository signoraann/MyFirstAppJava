package com.signoraann.javalearning.lesson28;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson26.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection()) {
            logger.info("Connected to database!");
            UserDao userDao = new UserDaoJdbc(connection);
            Scanner scanner = new Scanner(System.in);
            User newUser = new User(1L, "Logan", "logan@gmail.com", 32, OffsetDateTime.now());
            userDao.save(newUser);
            logger.info("Enter id to search User in database:");
            Long id = scanner.nextLong();
            Optional<User> userOptional = userDao.findUserById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                logger.info("User found: {}", user);
            } else {
                logger.warn("User not found!");
            }
        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        }
    }
}
