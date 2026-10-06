package com.signoraann.javalearning.lesson30;

import java.sql.Connection;

public interface BankDao {
    void deposit(Connection connection, double amount);

    void withdraw(Connection connection, double amount);

    double getBalance(Connection connection, String accountName);
}

