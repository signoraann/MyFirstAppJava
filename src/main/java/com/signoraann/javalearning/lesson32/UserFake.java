package com.signoraann.javalearning.lesson32;

import java.util.Objects;

public class UserFake {
    long id;
    String username;

    public UserFake(String username, long id) {
        this.id = id;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserFake userFake = (UserFake) o;
        return id == userFake.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
