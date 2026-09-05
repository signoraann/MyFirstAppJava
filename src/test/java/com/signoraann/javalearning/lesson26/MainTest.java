package com.signoraann.javalearning.lesson26;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void testPrintAllUsernamesFromDatabaseHappyPath() {
        boolean result = Main.printAllUsernamesFromDatabase(List.of("Ann", "Olga"));
        assertTrue(result);
    }

    @Test
    void testPrintAllUsernamesFromDatabaseReturnsFalseWhenDatabaseIsEmpty() {
        boolean result = Main.printAllUsernamesFromDatabase(List.of());
        assertFalse(result);
    }

    @Test
    void testPrintUserSearchingByUsernameHappyPath() {
        User testUser = new User(1L, "Ann", "Ann@mail.ru", 19);
        boolean result = Main.printUserSearchingByUsername(testUser, "Ann");
        assertTrue(result);
    }

    @Test
    void testPrintUserSearchingByUsernameReturnsFalseWhenUserDoesNotFound() {
        boolean result = Main.printUserSearchingByUsername(null, "Ann");
        assertFalse(result);
    }
}
