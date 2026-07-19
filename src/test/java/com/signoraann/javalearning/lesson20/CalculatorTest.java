package com.signoraann.javalearning.lesson20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @Test
    void testCheckAdd() {
        assertEquals(4, Calculator.add(2, 2));
    }

    @Test
    void testAddOverflow() {
        assertEquals(2147483648L, Calculator.add(Integer.MAX_VALUE, 1));
    }

    @Test
    void testAddNegative() {
        assertEquals(-6, Calculator.add(-3, -3));
    }

    @Test
    void testAddZero() {
        assertEquals(0, Calculator.add(0, 0));
    }

    @Test
    void testDivisionException() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> Calculator.divide(10, 0));
        assertEquals("Division by zero isn't allowed!", exception.getMessage());
    }

    @Test
    void testDivisionHappyPath() {
        assertEquals(10.0, Calculator.divide(20, 2));
    }

    @Test
    void testDivisionFractionalResult() {
        assertEquals(0.5, Calculator.divide(1, 2));
    }

    @Test
    void testDivisionWithNegative() {
        assertEquals(-10, Calculator.divide(20, -2));
    }

}

