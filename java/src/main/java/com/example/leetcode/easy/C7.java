package com.example.leetcode.easy;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Created by yangzteL on 2018/5/7 0007.
 */

public class C7 {
    public static void main(String[] args) {
        System.out.println(reverse(-123) + "");
    }


    public static int reverse(int x) {
        String str = String.valueOf(Math.abs(x));
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        int b = 0;
        try {
            if (x < 0) {
                b = -Integer.parseInt(sb.toString());
            }else{
                b = Integer.parseInt(sb.toString());
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return b;
    }
}
