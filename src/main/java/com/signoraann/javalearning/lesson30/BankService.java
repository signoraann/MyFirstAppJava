package com.signoraann.javalearning.lesson30;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class BankService {
    private final BankDao bankDao;
    private static final Logger logger = LoggerFactory.getLogger(BankService.class);

    public BankService(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public void transferMoney(Connection connection, String from, String to, BigDecimal amount) {
        checkTransferParameters(from, to, amount);
        try {
            connection.setAutoCommit(false);
            bankDao.withdraw(connection, from, amount);
            bankDao.deposit(connection, to, amount);
            connection.commit();
            logger.info("Transfer successful!");
        } catch (Exception e) {
            try {
                logger.warn("Transaction failed! Rollback.", e);
                connection.rollback();
            } catch (SQLException exception) {
                logger.error("Failed to rollback", exception);
            }
            throw new BankException("Transaction failed", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Failed to reset autoCommit!");
            }
        }
    }

    private void checkTransferParameters(String from, String to, BigDecimal amount) {
        if (from == null || from.isBlank()) {
            throw new BankException("Account must be specified!");
        }
        if (to == null || to.isBlank()) {
            throw new BankException("Account must be specified!");
        }
        if (from.equals(to)) {
            throw new BankException("Accounts must be different!");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new BankException("Amount must be > 0!");
        }
    }
}

