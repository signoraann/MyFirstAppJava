package com.signoraann.javalearning.lesson23;

import java.util.List;

public record User(String name, Integer age, List<String> hobbies, Address address) {
}
