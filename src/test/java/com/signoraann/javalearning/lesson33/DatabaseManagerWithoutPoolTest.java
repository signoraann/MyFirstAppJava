package com.signoraann.javalearning.lesson33;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatabaseManagerWithoutPoolTest {
    @Test
    void testCheckEnvironmentVariablesThrowExceptionWhenVariablesAreNull() {
        assertThrows(
                IllegalStateException.class,
                () -> DatabaseManagerWithoutPool.checkEnvironmentVariables(null, "user", "password"));
        assertThrows(
                IllegalStateException.class,
                () -> DatabaseManagerWithoutPool.checkEnvironmentVariables("url", null, "password"));
        assertThrows(
                IllegalStateException.class,
                () -> DatabaseManagerWithoutPool.checkEnvironmentVariables("url", "user", null));
    }

    @Test
    void testCheckEnvironmentVariablesHappyPath() {
        assertDoesNotThrow(() -> DatabaseManagerWithoutPool.checkEnvironmentVariables("url", "user", "password"));
    }
}
