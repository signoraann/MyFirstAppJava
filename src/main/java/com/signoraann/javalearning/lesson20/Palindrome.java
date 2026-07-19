package com.signoraann.javalearning.lesson20;


public class Palindrome {
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        s = s.toLowerCase();
        char[] line = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (line[left] != line[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
