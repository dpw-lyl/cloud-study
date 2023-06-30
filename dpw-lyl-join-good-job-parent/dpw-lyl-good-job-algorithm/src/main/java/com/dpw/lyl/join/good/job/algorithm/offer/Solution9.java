package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 * 提示:
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 * @Description: 乘积小于 K 的子数组 （中等）
 */
public class Solution9 {

    public static void main(String[] args) {
        System.out.println(new Solution9().numSubarrayProductLessThanK(new int[]{10,5,2,6},100));
    }


    public int numSubarrayProductLessThanK(int[] nums, int target) {
        if (target < 2) {
            return 0;
        }
        int res = 0;
        int begin = 0, end = 0, sum = 1;
        while (end < nums.length) {
            sum *= nums[end];
            while (begin <= end && sum >= target) {
                sum /= nums[begin];
                begin++;
            }
            res += end - begin + 1;
            end++;
        }

        return res;

    }


}
