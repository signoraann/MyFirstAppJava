package com.signoraann.javalearning.lesson30;

import com.signoraann.javalearning.lesson26.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            logger.info("Enter the amount to add to or to substract from the account: ");
            while (!scanner.hasNextDouble()) {
                logger.warn("Amount should be a number! Try again:");
                scanner.next();
            }
            double amount = scanner.nextDouble();
            bankService.transferMoney(connection, amount);
        } catch (SQLException | IllegalStateException e) {
            logger.error("Operation failed", e);
        }
    }
}
