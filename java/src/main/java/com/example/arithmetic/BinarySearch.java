package com.example.arithmetic;

/**
 * 二分查找,数组必须有序
 * Created by Longwj on 2017/9/27.
 */

public class BinarySearch {
    public int rank(int key,int[] a){
        int lo = 0;
        int hi = a.length - 1;

        while(lo  <=  hi){
            int mid = lo + (hi - lo) / 2;
            if(key < a[mid]){
                hi = mid - 1;
            }else if(key > a[mid]){
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
