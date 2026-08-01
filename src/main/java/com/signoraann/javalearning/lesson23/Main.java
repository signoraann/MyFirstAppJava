package com.signoraann.javalearning.lesson23;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        UserConverter converter = new UserConverter();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("user.json")), StandardCharsets.UTF_8)) {
            List<User> users = converter.fromJson(reader);
            if (users != null && !users.isEmpty()) {
                System.out.println("Object from file:");
                users.forEach(System.out::println);
                String backToJson = converter.toJson(users);
                System.out.println("\nBack to json:\n" + backToJson);
            } else {
                System.out.println("File user.json is empty!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing the user.json file", e);
        }
    }
}
