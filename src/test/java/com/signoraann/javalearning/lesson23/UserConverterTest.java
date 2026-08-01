package com.signoraann.javalearning.lesson23;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserConverterTest {
    private final UserConverter converter = new UserConverter();

    @Test
    void fromJsonHappyPath() {
        StringReader reader = new StringReader("""
                [
                  {
                    "name": "Ann",
                    "age": 19,
                    "hobbies": ["dancing", "reading", "photography"],
                    "address": {
                      "country": "Belarus",
                      "city": "Brest",
                      "street": "Lenina",
                      "house": "20"
                    }
                  }
                ]
                """);
        List<User> users = converter.fromJson(reader);
        assertNotNull(users);
        assertEquals("Ann", users.getFirst().name());
        assertEquals(19, users.getFirst().age());
        assertTrue(users.getFirst().hobbies().contains("reading"));
        assertEquals("Lenina", users.getFirst().address().street());
    }

    @Test
    void fromJsonEmpty() {
        StringReader reader = new StringReader("[]");
        List<User> users = converter.fromJson(reader);
        assertTrue(users.isEmpty());
    }

    @Test
    void fromJsonEmptyFile() {
        StringReader reader = new StringReader("");
        List<User> users = converter.fromJson(reader);
        assertNull(users);
    }


    @Test
    void testException() {
        StringReader reader = new StringReader("\"string\"");
        Exception exception = assertThrows(Exception.class, () -> {
            converter.fromJson(reader);
        });
        String messagePart = "Expected BEGIN_ARRAY but was STRING";
        assertTrue(exception.getMessage().contains(messagePart), "Expected BEGIN_ARRAY but was STRING");
    }
}
