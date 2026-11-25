package com.signoraann.javalearning.lesson30;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static void main() {
        try (DatabaseManager databaseManager = new DatabaseManager();
                Connection connection = databaseManager.getConnection();
                Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Connected to database!");
            BankDao bankDao = new BankDaoJdbc();
            BankService bankService = new BankService(bankDao);
            LOGGER.info("Enter account name to withdraw: ");
            String from = scanner.next();
            LOGGER.info("Enter account name to deposit: ");
            String to = scanner.next();
            LOGGER.info("Enter the amount to transfer money from {} account to {} account: ", from, to);
            while (!scanner.hasNextBigDecimal()) {
                LOGGER.warn("Amount should be a number! Try again:");
                scanner.next();
            }
            BigDecimal amount = scanner.nextBigDecimal();
            bankService.transferMoney(connection, from, to, amount);
        } catch (SQLException | IllegalStateException e) {
            LOGGER.error("Operation failed.", e);
        } catch (BankException e) {
            LOGGER.error("Transfer failed.", e);
        }
    }
}
