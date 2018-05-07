package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 *给定一个整数数组，两个数字的返回指标，它们加到一个特定的目标上。
 您可能假设每个输入都有一个解决方案，并且您可能不会两次使用相同的元素。

 *https://leetcode.com/problems/two-sum/description/
 * Created by yangzteL on 2018/5/2 0002.
 */

public class C1 {
    public static void main(String[] args){
        fun3(new int[]{2,7,11,15},9);
    }

    /**
     * 方法一：暴力
     * @param nums
     * @param target
     * @return
     */
    private static int [] fun1(int[] nums, int target){
        for(int i = 0; i < nums.length;i++){
            for(int j = 0;j < nums.length;j++){
                if(nums[i] + nums[j] == target){
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法2：hash来实现
     * @param nums
     * @param target
     * @return
     */
    private static int [] fun2(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length;i++){
            map.put(nums[i],i);
        }

        for (int i = 0; i < nums.length; i++) {
            int hope = target - nums[i];
            if (map.containsKey(hope) && map.get(hope) != i) {
                return new int[] { i, map.get(hope) };
            }
        }
        return null;
    }
    /**
     * 方法2：hash来实现
     * @param nums
     * @param target
     * @return
     */
    private static int [] fun3(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int hope = target - nums[i];
            if (map.containsKey(hope)) {
                return new int[] { map.get(hope), i };
            }
            map.put(nums[i], i);
        }
        return null;
    }
}

