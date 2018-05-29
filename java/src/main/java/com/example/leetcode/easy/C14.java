package com.example.leetcode.easy;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.

 If there is no common prefix, return an empty string "".
 *
 * Created by yangzteL on 2018/5/16 0016.
 */

public class C14 {
    public static void main(String[] args) {
        String [] strs = {"flower","flow","flight"};

        System.out.println();
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length ==0){
            return "";
        }
        if(strs.length ==1){
            return strs[0];
        }

        return "";
    }
}
