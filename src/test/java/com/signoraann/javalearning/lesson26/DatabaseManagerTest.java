package com.signoraann.javalearning.lesson26;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {
    @Test
    void testCheckEnvironmentVariablesThrowExceptionWhenUrlIsNull() {
        assertThrows(
                IllegalStateException.class,
                () -> DatabaseManager.checkEnvironmentVariables(null, "DB_USER", "DB_PASSWORD"));
    }

    @Test
    void testCheckEnvironmentVariablesThrowExceptionWhenUserIsNull() {
        assertThrows(
                IllegalStateException.class,
                () -> DatabaseManager.checkEnvironmentVariables(
                        "jdbc:postgresql://localhost:5432/test", null, "DB_PASSWORD"));
    }

    @Test
    void testCheckEnvironmentVariablesThrowExceptionWhenPasswordIsNull() {
        assertThrows(
                IllegalStateException.class,
                () -> DatabaseManager.checkEnvironmentVariables(
                        "\"jdbc:postgresql://localhost:5432/test\"", "DB_USER", null));
    }

    @Test
    void testCheckEnvironmentVariablesHappyPath() {
        DatabaseManager.checkEnvironmentVariables(
                "\"jdbc:postgresql://localhost:5432/test\"", "DB_USER", "DB_PASSWORD");
    }
}
