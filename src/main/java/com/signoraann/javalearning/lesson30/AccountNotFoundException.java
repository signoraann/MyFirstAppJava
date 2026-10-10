package com.signoraann.javalearning.lesson30;

public class AccountNotFoundException extends BankException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
