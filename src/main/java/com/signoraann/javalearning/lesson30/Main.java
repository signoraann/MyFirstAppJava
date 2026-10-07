package com.signoraann.javalearning.lesson30;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (DatabaseManager databaseManager = new DatabaseManager();
             Connection connection = databaseManager.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            logger.info("Connected to database!");
            BankDao bankDao = new BankDaoJdbc();
            BankService bankService = new BankService(bankDao);
            logger.info("Enter account name to withdraw: ");
            String from = scanner.next();
            logger.info("Enter account name to deposit: ");
            String to = scanner.next();
            logger.info("Enter the amount to transfer money from {} account to {} account: ", from, to);
            while (!scanner.hasNextBigDecimal()) {
                logger.warn("Amount should be a number! Try again:");
                scanner.next();
            }
            BigDecimal amount = scanner.nextBigDecimal();
            bankService.transferMoney(connection, from, to, amount);
        } catch (SQLException | IllegalStateException e) {
            logger.error("Operation failed.", e);
        } catch (BankException e) {
            logger.error("Transfer failed.", e);
        }
    }
}

