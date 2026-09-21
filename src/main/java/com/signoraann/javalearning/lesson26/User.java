package com.signoraann.javalearning.lesson26;

import java.time.OffsetDateTime;

public record User(Long id, String username, String email, Integer age, OffsetDateTime created_at) {}
