package com.signoraann.javalearning.lesson30;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class BankService {
    private final BankDao bankDao;
    private static final Logger logger = LoggerFactory.getLogger(BankService.class);

    public BankService(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public void transferMoney(Connection connection, double amount) {
        try {
            connection.setAutoCommit(false);
            bankDao.withdraw(connection, amount);
            bankDao.deposit(connection, amount);
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
}

