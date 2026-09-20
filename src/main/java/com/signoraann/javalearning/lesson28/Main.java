package com.signoraann.javalearning.lesson28;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson26.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection()) {
            logger.info("Connected to database!");
            UserDao userDao = new UserDaoJdbc(connection);
            User newUser = new User(1L, "Den", "denny@gmail.com", 23);
            userDao.save(newUser);
        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        }
    }
}
