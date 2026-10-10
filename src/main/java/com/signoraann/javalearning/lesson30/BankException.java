package com.signoraann.javalearning.lesson30;

public class BankException extends RuntimeException {
    public BankException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankException(String message) {
        super(message);
    }
}

