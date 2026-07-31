package com.signoraann.javalearning.lesson23;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("user.json")), StandardCharsets.UTF_8)) {
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(reader, userListType);
            System.out.println("Object from file:");
            users.forEach(System.out::println);
            String backToJson = gson.toJson(users);
            System.out.println("\nBack to json:\n" + backToJson);
        } catch (Exception e) {
            throw new RuntimeException("Error processing the user.json file", e);
        }
    }
}
