package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * 提示:
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * @Description: 和为 k 的子数组 （中等）
 */
public class Solution10 {

    public static void main(String[] args) {
        System.out.println(new Solution10().subarraySumEnum(new int[]{1, 1, 1}, 2));
    }


    /**
     * @author: dengpw
     * @createTime: 2023年06月29 09:13:21
     * @description: 前缀和+hash表
     * @param: nums - [int]
     * @param: k - [int]
     * @return: int
     */
    public int subarraySum(int[] nums, int k) {
        int res = 0,sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    /**
     * @author: dengpw
     * @createTime: 2023年06月29 09:13:02
     * @description: 枚举
     * @param: nums - [int]
     * @param: k - [int]
     * @return: int
     */
    public int subarraySumEnum(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }


}
