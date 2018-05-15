package com.example.leetcode.hard;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Created by yangzteL on 2018/5/14 0014.
 */

public class C4 {
    public static void main(String[] args) {
//        int[] nums1 = {1,2,3};
//        int[] nums2 = {2,3,4};

        int[] nums1 = {3,4,5};
        int[] nums2 = {1,2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        double min = 0;

        if(len1 == 0){
            if (len2 % 2 == 0) {
                min = (double) (nums2[len2 / 2 - 1] + nums2[len2 / 2]) / 2;
            } else {
                min = (double) nums2[(len2 - 1) / 2];
            }
            return  min;
        }
        if(len2 == 0){
            if (len1 % 2 == 0) {
                min = (double) (nums1[len1 / 2 - 1] + nums1[len1 / 2]) / 2;
            } else {
                min = (double) nums1[(len1 - 1) / 2];
            }
            return  min;
        }


        int[] num = new int[len1 + len2];
        int max = len1 > len2 ? len1 : len2;

        int index1 = 0;
        int index2 = 1;
        int index = 0;
        for (int i = 0; i < max; i++) {
            //num1
            if (i < len1) {
                if (i == 0) {
                    num[0] = nums1[0];
                } else if (num[index] <= nums1[i]) {
                    num[++index] = nums1[i];
                } else {
                    int temp = num[index];
                    num[index] = nums1[i];
                    num[++index] = temp;
                }
            }

            if (i < len2) {
                if (num[index] <= nums2[i]) {
                    num[++index] = nums2[i];
                } else {
                    int temp = num[index];
                    num[index] = nums2[i];
                    num[++index] = temp;
                }
            }
        }
        if (((len1 + len2) % 2) == 0) {
            min = (double) (num[(len1 + len2) / 2 - 1] + num[(len1 + len2) / 2]) / 2;
        } else {
            min = (double) num[(len1 + len2 - 1) / 2];
        }
        return min;
    }

    /**
     * 官方解法
     * @param A
     * @param B
     * @return
     */
    public static double getMid(int[] A, int[] B){
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
