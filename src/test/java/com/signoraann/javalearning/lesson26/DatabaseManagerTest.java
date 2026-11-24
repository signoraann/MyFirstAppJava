package com.signoraann.javalearning.lesson26;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {
    private  static final String URL = "url";
    private  static final String USER = "user";
    private  static final String PASSWORD = "password";

    @Test
    void testCheckEnvironmentVariablesThrowExceptionWhenVariablesAreNull() {
        assertThrows(
                IllegalStateException.class, () -> DatabaseManager.checkEnvironmentVariables(null, USER, PASSWORD));
        assertThrows(
                IllegalStateException.class, () -> DatabaseManager.checkEnvironmentVariables(URL, null, PASSWORD));
        assertThrows(IllegalStateException.class, () -> DatabaseManager.checkEnvironmentVariables(URL, USER, null));
    }

    @Test
    void testCheckEnvironmentVariablesHappyPath() {
        assertDoesNotThrow(() -> DatabaseManager.checkEnvironmentVariables(URL, USER, PASSWORD));
    }
}
