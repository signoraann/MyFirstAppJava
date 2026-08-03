package com.signoraann.javalearning.lesson23;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {
    private final UserConverter converter = new UserConverter();

    @Test
    void toJsonRoundTrip() {
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
    void fromJsonEmptyArrayReturnsEmptyList() {
        StringReader reader = new StringReader("[]");
        List<User> users = converter.fromJson(reader);
        assertTrue(users.isEmpty());
    }

    @Test
    void fromJsonEmptyInputReturnsNull() {
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
    void fromJsonSetsNullPostcodeWhenItMissingInJson() {
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
    void fromJsonCaseMismatchReturnsNullField() {
        String json = "[{\"name\": \"Ann\", \"Age\": 19}]";
        List<User> users = converter.fromJson(new StringReader(json));
        assertNull(users.getFirst().age());
    }

    @Test
    void fromJsonAgeIsNullReturnsNull() {
        String json = "[{\"name\": \"Ann\", \"age\": null}]";
        List<User> users = converter.fromJson(new StringReader(json));
        assertNull(users.getFirst().age());
    }

    @Test
    void toJsonHappyPath() {
        Address address = new Address("Belarus", "Brest", "Lenina", "20", 123456);
        User user = new User("Ann", 19, List.of("reading", "dancing"), address);
        List<User> users = List.of(user);
        String json = converter.toJson(users);
        List<User> backToObject = converter.fromJson(new StringReader(json));
        assertEquals(users, backToObject);
    }

    @Test
    void toJsonNullListReturnsNullLiteral() {
        String json = converter.toJson(null);
        assertEquals("null", json);
    }

    @Test
    void toJsonIncludesNullFields() {
        User user = new User("Ann", null, null, null);
        List<User> users = List.of(user);
        String json = converter.toJson(users);
        assertFalse(json.contains("\"age\":null"));
    }
}
