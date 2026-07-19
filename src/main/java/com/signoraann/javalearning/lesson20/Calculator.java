package com.signoraann.javalearning.lesson20;

public class Calculator {
    public static long add(int num1, int num2) {
        return (long) num1 + num2;
    }

    public static double divide(int num1, int num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero isn't allowed!");
        }
        return (double) num1 / num2;
    }
}