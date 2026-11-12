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
        UserGenerator userGenerator = new UserGenerator();
        try {
            logger.info("Using pool");
            long startTimeWithPool = System.nanoTime();
            insertWithPool(userGenerator);
            long endTimeWithPool = System.nanoTime();
            double timeUsingPool = (endTimeWithPool - startTimeWithPool) / 1000000000.0;
            logger.info("Time with using pool: {} seconds", timeUsingPool);
            logger.info("Open fresh connection every time");
            long startTimeWithoutPool = System.nanoTime();
            insertWithoutPool(userGenerator);
            long endTimeWithoutPool = System.nanoTime();
            double timeWithoutPool = (endTimeWithoutPool - startTimeWithoutPool) / 1000000000.0;
            logger.info("Time without using pool: {} seconds", timeWithoutPool);
            double differenceTime = timeWithoutPool - timeUsingPool;
            logger.info("Difference in time: {} seconds", differenceTime);
            openConnections();
        } catch (SQLException | IllegalStateException exception) {
            logger.error("wrong", exception);
        }
    }

    private static void insertWithPool(UserGenerator userGenerator) throws SQLException {
        try (DatabaseManager databaseManager = new DatabaseManager()) {
            for (int i = 1; i <= 1000; i++) {
                User user = userGenerator.generateUsers(1).getFirst();
                try (Connection connection = databaseManager.getConnection()) {
                    var userDao = new UserDaoJdbc(connection);
                    userDao.saveUser(user);
                }
            }
        }
    }

    private static void insertWithoutPool(UserGenerator userGenerator) throws SQLException {
        for (int i = 1; i <= 1000; i++) {
            User user = userGenerator.generateUsers(1).getFirst();
            try (Connection connection = DatabaseManagerWithoutPool.getConnection()) {
                var userDao = new UserDaoJdbc(connection);
                userDao.saveUser(user);
            }
        }
    }

    private static void openConnections() throws SQLException {
        try (DatabaseManager databaseManager = new DatabaseManager(2)) {
            for (int i = 1; i <= 20; i++) {
                Connection connection = databaseManager.getConnection();
                logger.info("Connection {} opened", i);
            }
        }
    }
}
