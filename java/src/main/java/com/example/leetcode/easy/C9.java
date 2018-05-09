package com.example.leetcode.easy;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward
 * Created by yangzteL on 2018/5/9 0009.
 */

public class C9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1221));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int n = 0;
        while (x > n) {
            n = n * 10 + x % 10;
            x /= 10;
        }
        return x == n || x == n / 10;
    }
}
