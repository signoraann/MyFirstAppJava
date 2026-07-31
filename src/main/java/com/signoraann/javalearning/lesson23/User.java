package com.signoraann.javalearning.lesson23;

import java.util.List;

public record User(String name, int age, List<String> hobbies, Address address) {
}
