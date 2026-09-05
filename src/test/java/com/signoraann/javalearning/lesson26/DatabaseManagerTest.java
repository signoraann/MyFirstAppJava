package com.signoraann.javalearning.lesson26;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {
    @Test
    void testGetConnectionHappyPath() throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            assertTrue(connection.isValid(2));
            assertFalse(connection.isClosed());
        }
    }
}
