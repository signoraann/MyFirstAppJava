package com.signoraann.javalearning.lesson23;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("user.json")), StandardCharsets.UTF_8)) {
            User user = gson.fromJson(reader, User.class);
            System.out.println("Object from file " + user);
            String backToJson = gson.toJson(user);
            System.out.println("Back to json " + backToJson);
        } catch (Exception e) {
            throw new RuntimeException("Error processing the user.json file", e);
        }

    }
}
