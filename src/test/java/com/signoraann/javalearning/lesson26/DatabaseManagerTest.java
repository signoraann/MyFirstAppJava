package com.signoraann.javalearning.lesson26;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {
    @Test
    void testCheckEnvironmentVariablesThrowExceptionWhenVariablesAreNull() {
        assertThrows(
                IllegalStateException.class, () -> DatabaseManager.checkEnvironmentVariables(null, "user", "password"));
        assertThrows(
                IllegalStateException.class, () -> DatabaseManager.checkEnvironmentVariables("url", null, "password"));
        assertThrows(IllegalStateException.class, () -> DatabaseManager.checkEnvironmentVariables("url", "user", null));
    }

    @Test
    void testCheckEnvironmentVariablesHappyPath() {
        assertDoesNotThrow(() -> DatabaseManager.checkEnvironmentVariables("url", "user", "password"));
    }
}
