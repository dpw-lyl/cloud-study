package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 来源：力扣（LeetCode）
 * 链接：ht<a href="tps://leetcode.cn/problems/2VG8Kg"></a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description: 和大于等于 target 的最短子数组 （中等）
 */
public class Solution8 {

    public static void main(String[] args) {
        System.out.println(new Solution8().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }


    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int begin = 0, end = 0, sum = 0;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                res = Math.min(res, end - begin + 1);
                sum -= nums[begin];
                begin++;
            }
            end++;
        }


        return res == Integer.MAX_VALUE ? 0 : res;

    }


}
