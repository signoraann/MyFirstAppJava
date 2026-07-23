package com.signoraann.javalearning.lesson21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        String username = "Alice";
        logger.debug("sum = 10");
        logger.info("User {} logged in", username);
        logger.warn("Low disk space");
        logger.error("Database connection error");
    }
}
