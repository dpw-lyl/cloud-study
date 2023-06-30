package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * @Description: 0 和 1 个数相同的子数组 （中等）
 */
public class Solution11 {

    public static void main(String[] args) {
        System.out.println(new Solution11().findMaxLength(new int[]{0, 0, 0, 0, 0,1,1,1}));
    }


    /**
     * @author: dengpw
     * @createTime: 2023年06月29 14:59:22
     * @description: 前缀和+hash
     * @param: nums - [int]
     * @return: int
     */
    public int findMaxLength(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }


}
