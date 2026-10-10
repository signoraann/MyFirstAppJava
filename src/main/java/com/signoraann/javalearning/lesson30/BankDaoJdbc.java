package com.signoraann.javalearning.lesson30;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDaoJdbc implements BankDao {

    @Override
    public void withdraw(Connection connection, String accountName, BigDecimal amount) {
        String withdrawSql = "UPDATE bank_account SET balance = balance - ? WHERE account_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(withdrawSql)) {
            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setString(2, accountName);
            if (preparedStatement.executeUpdate() == 0) {
                throw new BankException("Account not found! Withdraw failed!");
            }
        } catch (SQLException e) {
            throw new BankException("Unable to charge your account", e);
        }
    }

    @Override
    public void deposit(Connection connection, String accountName, BigDecimal amount) {
        String depositSql = "UPDATE bank_account SET balance = balance + ? WHERE account_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(depositSql)) {
            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setString(2, accountName);
            if (preparedStatement.executeUpdate() == 0) {
                throw new BankException("Account not found! Deposit failed.");
            }
        } catch (SQLException e) {
            throw new BankException("Unable to add money to your account", e);
        }
    }

    @Override
    public BigDecimal getBalance(Connection connection, String accountName) {
        String getBalanceSql = "Select balance FROM bank_account WHERE account_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getBalanceSql)) {
            preparedStatement.setString(1, accountName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBigDecimal("balance");
                }
            }
        } catch (SQLException e) {
            throw new BankException("Balance verification error for account " + accountName, e);
        }
        throw new BankException("Account not found: " + accountName);
    }
}
