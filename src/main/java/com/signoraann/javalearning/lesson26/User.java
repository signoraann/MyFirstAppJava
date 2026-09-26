package com.signoraann.javalearning.lesson26;

import java.time.OffsetDateTime;

public record User(Long id, String username, String email, Integer age, OffsetDateTime createdAt) {
    public User(Long id, String username, String email, Integer age) {
        this(id, username, email, age, OffsetDateTime.now());
    }
}
