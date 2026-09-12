package com.signoraann.javalearning.lesson27;

import com.signoraann.javalearning.lesson26.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratorTest {
    private final UserGenerator userGenerator = new UserGenerator();

    @Test
    void testGenerateUsersGeneratedRequestedNumberOfUsers() {
        int count = 20;
        List<User> users = userGenerator.generateUsers(count);
        assertEquals(count, users.size());
    }

    @Test
    void testGenerateUsersReturnsEmptyListWhenCountIsZero() {
        List<User> users = userGenerator.generateUsers(0);
        assertTrue(users.isEmpty());
    }

    @Test
    void testGenerateUsers() {
        List<User> users = userGenerator.generateUsers(5);
        for (User user : users) {
            assertNotNull(user.username());
            assertNotNull(user.email());
            assertTrue(user.age() >= 18);
            assertNotNull(user.age());
            assertTrue(user.age() <= 25);
        }
    }
}
