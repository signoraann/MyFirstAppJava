package com.signoraann.javalearning.lesson30;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BankDaoJdbc implements BankDao {
    private final Connection connection;

    public BankDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void withdraw(Connection connection, double amount) {
        String withdrawSql = "UPDATE bank_account SET balance = balance - ? WHERE account_name = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(withdrawSql)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BankException("Unable to charge your account", e);
        }
    }

    @Override
    public void deposit(Connection connection, double amount) {
        String depositSql = "UPDATE bank_account SET broken_balance = balance + ? WHERE account_name = 'B'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(depositSql)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BankException("Unable to add money to your account", e);
        }
    }
}
