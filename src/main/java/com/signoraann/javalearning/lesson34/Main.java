package com.signoraann.javalearning.lesson34;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static void main() {
        Scanner scanner = new Scanner(System.in);
        String a = "Hello";
        LOGGER.info("Input 'Hello' ");
        String b = scanner.nextLine();
        while (!a.equals(b)) {
            LOGGER.warn("Wrong input! Enter word 'Hello' please!");
            b = scanner.nextLine();
        }
        LOGGER.info("a==b result: {}", a == b); // false
        LOGGER.info("a.equals(b) result: {}", a.equals(b)); // true

        String c = "Ann";
        String d = "Ann";
        LOGGER.info("c==d result: {}", c == d); // true
        LOGGER.info("c.equals(d) result: {}", c.equals(d)); // true

        scanner.close();
    }
}
