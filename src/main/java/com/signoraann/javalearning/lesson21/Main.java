package com.signoraann.javalearning.lesson21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static void main() {
        String username = "Alice";
        LOGGER.debug("sum = 10");
        LOGGER.info("User {} logged in", username);
        LOGGER.warn("Low disk space");
        LOGGER.error("Database connection error");
    }
}
