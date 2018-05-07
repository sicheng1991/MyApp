package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长的子字符串，没有重复字符
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * Created by yangzteL on 2018/5/3 0003.
 */

public class C3 {

    public static void main(String[] args){
            lengthOfLongestSubstring("abcba");
    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), max = 0;
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        for (int j = 0; j < n; j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(map.get(c), i);
            }
            max = Math.max(max, j - i + 1);
            map.put(c, j + 1);
        }
        return max;
    }
}
