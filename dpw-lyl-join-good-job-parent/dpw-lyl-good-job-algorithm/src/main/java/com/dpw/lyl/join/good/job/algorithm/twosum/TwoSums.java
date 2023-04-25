package com.dpw.lyl.join.good.job.algorithm.twosum;

import java.util.Arrays;

/**
 * @Author: dengpw
 * @createTime: 2023年04月18日 17:29:20
 * @version: 1.0.0
 * @Description: (两数之和 01)给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那 **两个** 整数，并返回他们的数组下标。
 */
public class  TwoSums{


    public static void main(String[] args) {
        TwoSums towSums = new TwoSums();

        int[] a={1,3,5,9,2,4,6,8,10};
        int[] ints = towSums.towSumsIndex(a, 9);
        System.out.println(Arrays.toString(Arrays.stream(ints).toArray()));
    }

    public int[] towSumsIndex(int[] nums, int target) {
        int[] index = new int[2];
        if (null == nums || nums.length == 0) {
            return index;
        }
        int length = nums.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }
}
