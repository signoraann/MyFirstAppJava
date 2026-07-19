package com.signoraann.javalearning.lesson20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeTest {

    @Test
    void testPalindromeNull() {
        assertFalse(Palindrome.isPalindrome(null));
    }

    @Test
    void testPalindrome() {
        assertTrue(Palindrome.isPalindrome("Anna"));
    }

    @Test
    void testPalindromeEmpty() {
        assertTrue(Palindrome.isPalindrome(""));
    }

    @Test
    void testNoPalindrome() {
        assertFalse(Palindrome.isPalindrome("hello"));
    }
}

