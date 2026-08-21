package com.signoraann.javalearning.lesson26;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    @Test
    void testFindUserByUsernameThatDoesNotExistInDatabaseReturnsNull() throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            UserRepository repository = new UserRepository(connection);
            String result = repository.findUserByUsername("Nastya");
            assertNull(result);
        }
    }

    @Test
    void testFindUserByUsernameReturnsUsername() throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            UserRepository repository = new UserRepository(connection);
            String result = repository.findUserByUsername("Ann");
            assertEquals("Ann", result);
        }
    }

    @Test
    void testFindAllUsersReturnsAllUsernames() throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            UserRepository repository = new UserRepository(connection);
            List<String> result = repository.findAllUsers();
            assertNotNull(result);
            assertEquals(List.of("Ann", "Olga", "someuser"), result);
        }
    }

    @Test
    void testFindAllUsersThrowsSQLException() throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            UserRepository repository = new UserRepository(connection);
            connection.close();
            assertThrows(SQLException.class, repository::findAllUsers);
        }
    }
}
