package com.example.leetcode.easy;

/**
 *
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Created by yangzteL on 2018/5/7 0007.
 */

public class C7 {
    public static void main(String[] args){
        System.out.println(reverse(9646324351L) + "");
        System.out.println(reverse(123) + "");
    }

    public static long reverse(long x) {
        String  str = String.valueOf(Math.abs(x));
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        if(x < 0) {
            return -Long.parseLong(sb.toString());
        }
        return Long.parseLong(sb.toString());
    }
}
