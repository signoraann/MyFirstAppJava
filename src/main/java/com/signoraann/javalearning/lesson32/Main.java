package com.signoraann.javalearning.lesson32;

// import com.signoraann.javalearning.lesson26.User;
import com.signoraann.javalearning.lesson26.DatabaseManager;
import com.signoraann.javalearning.lesson28.UserDao;
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
                Connection connection = databaseManager.getConnection()) {
            logger.info("Connected to database!");
            UserDao userDao = new UserDaoJdbc(connection);
            userDao.findAllUsernames();
            userDao.findUserById(1L);
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
