package com.signoraann.javalearning.lesson23;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class UserConverter {
    private final Gson gson = new Gson();
    private final Type userListType = new TypeToken<List<User>>() {
    }.getType();

    public List<User> fromJson(Reader reader) {
        return gson.fromJson(reader, userListType);
    }

    public String toJson(List<User> users) {
        return gson.toJson(users);
    }
}
