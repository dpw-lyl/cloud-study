package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * @Description: 数组中和为 0 的三个数 （中等）
 */
public class Solution7 {

    public static void main(String[] args) {
        System.out.println(new Solution7().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                //去重重复值
                continue;
            }
            int a = i + 1, b = nums.length - 1;
            while (a < b) {
                if (nums[i] + nums[a] + nums[b] == 0) {
                    List<Integer> data = new ArrayList<>();
                    data.add(nums[a]);
                    data.add(nums[i]);
                    data.add(nums[b]);
                    result.add(data);
                    int num = nums[a];
                    while (nums[a] == num && a < b) {
                        //去重重复值
                        a++;
                    }
                } else if (nums[a] + nums[i] + nums[b] < 0) {
                    a++;
                } else {
                    b--;
                }
            }

        }

        return result;
    }


}
