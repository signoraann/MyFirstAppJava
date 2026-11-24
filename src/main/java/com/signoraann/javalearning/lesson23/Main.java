package com.signoraann.javalearning.lesson23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        UserConverter converter = new UserConverter();
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(
                        Main.class.getClassLoader().getResourceAsStream("user.json"), "user.json not found!"),
                StandardCharsets.UTF_8)) {
            List<User> users = converter.fromJson(reader);
            if (users != null && !users.isEmpty()) {
                LOGGER.info("Object from file:");
                users.forEach(user -> LOGGER.info("User: {}", user));
                String backToJson = converter.toJson(users);
                LOGGER.info("Back to Json: {}", backToJson);
            } else {
                LOGGER.warn("File user.json is empty!");
            }
        }
    }
}
