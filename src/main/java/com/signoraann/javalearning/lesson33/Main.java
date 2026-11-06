package com.signoraann.javalearning.lesson33;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson26.User;
import com.signoraann.javalearning.lesson27.UserGenerator;

import com.signoraann.javalearning.lesson28.UserDaoJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    static void main() {
        /*logger.info("Using pool");
        UserGenerator userGenerator = new UserGenerator();
        try (DatabaseManager databaseManager = new DatabaseManager()) {
            for (int i = 1; i <= 1000; i++) {
                User user = userGenerator.generateUsers(1).getFirst();
                try (Connection connection = databaseManager.getConnection()) {
                    var userDao = new UserDaoJdbc(connection);
                    userDao.saveUser(user);
                    logger.info("User {} successfully saved", i);
                }
            }
        } catch (SQLException e) {
            logger.error("Database error", e);
        }

        logger.info("Open fresh connection every time");
        for (int i = 1; i <= 1000; i++) {
            User user = userGenerator.generateUsers(1).getFirst();
            try (Connection connection = DatabaseManagerWithoutPool.getConnection()) {
                var userDao = new UserDaoJdbc(connection);
                userDao.saveUser(user);
                logger.info("User {} successfully saved", i);
            } catch (SQLException e) {
                logger.error("Database error", e);
            }
        }*/
        openConnections();
    }

    private static void openConnections() {
        try (DatabaseManager databaseManager = new DatabaseManager()) {
            for (int i = 1; i <= 20; i++) {
                Connection connection = databaseManager.getConnection();
                logger.info("Connection {} opened", i);
            }
        } catch (SQLException e) {
            logger.error("Connection failed. {}", e.getMessage());
        }
    }
}
