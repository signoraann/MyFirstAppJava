package com.signoraann.javalearning.lesson27;

import com.signoraann.javalearning.lesson26.User;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class UserGenerator {
    private final Faker faker = new Faker();

    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String username = faker.credentials().username();
            String email = faker.internet().emailAddress();
            Integer age = faker.number().numberBetween(18, 25);
            users.add(new User(null, username, email, age));
        }
        return users;
    }
}
