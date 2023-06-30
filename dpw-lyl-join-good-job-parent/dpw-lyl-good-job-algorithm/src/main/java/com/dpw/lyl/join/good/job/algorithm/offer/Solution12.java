package com.dpw.lyl.join.good.job.algorithm.offer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给你一个整数数组nums ，请计算数组的 中心下标 。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/tvdfij
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *提示：
 * 1 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 * @Description:  左右两边子数组的和相等 （简单）
 */
public class Solution12 {

    public static void main(String[] args) {
        System.out.println(new Solution12().pivotIndex(new int[]{1,7,3,6,5,1,7,3,4,6,5,6}));
    }



    /**
     * @author: dengpw
     * @createTime: 2023年06月29 16:21:07
     * @description: 前缀和
     * @param: nums - [int]
     * @return: int
     */
    public int pivotIndex(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 2 * sum 就是左边和右边和相等 然后加上中间坐标的值等于总和
            if (totalSum - nums[i] == 2 * sum) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }


}
