package com.signoraann.javalearning.lesson30;

import java.math.BigDecimal;
import java.sql.Connection;

public interface BankDao {
    void deposit(Connection connection, String accountName, BigDecimal amount);

    void withdraw(Connection connection, String accountName, BigDecimal amount);

    BigDecimal getBalance(Connection connection, String accountName);
}
