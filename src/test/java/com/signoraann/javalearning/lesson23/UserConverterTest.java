package com.signoraann.javalearning.lesson23;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void fromJsonInvalidFormatThrowsJsonSyntaxException() {
        StringReader reader = new StringReader("\"string\"");
        JsonSyntaxException exception = assertThrows(JsonSyntaxException.class, () -> converter.fromJson(reader));
        assertInstanceOf(IllegalStateException.class, exception.getCause());
    }

    @Test
    void fromJsonWithoutPostcodeReturnsNull() {
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
        assertNull(users.getFirst().address().postcode());
    }

    @Test
    void fromJsonCaseMismatchReturnNullField() {
        String json = "[{\"name\": \"Ann\", \"Age\": 19}]";
        List<User> users = converter.fromJson(new StringReader(json));
        assertNull(users.getFirst().age());
    }

    @Test
    void toJsonHappyPath() {
        Address address = new Address("Belarus", "Brest", "Lenina", "20", 123456);
        User user = new User("Ann", 19, List.of("reading", "dancing"), address);
        List<User> users = List.of(user);
        String json = converter.toJson(users);
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"Ann\""));
        assertTrue(json.contains("\"age\":19"));
        assertTrue(json.contains("\"hobbies\":[\"reading\",\"dancing\"]"));
        assertTrue(json.contains("\"postcode\":123456"));
    }

    @Test
    void toJsonEmpty() {
        String json = converter.toJson(null);
        assertEquals("null", json);
    }

    @Test
    void toJsonIncludesNullFields() {
        User user = new User("Ann", null, null, null);
        List<User> users = List.of(user);
        String json = converter.toJson(users);
        assertTrue(json.contains("\"age\":null"));
        assertTrue(json.contains("\"hobbies\":null"));
        assertTrue(json.contains("\"address\":null"));
    }
}
