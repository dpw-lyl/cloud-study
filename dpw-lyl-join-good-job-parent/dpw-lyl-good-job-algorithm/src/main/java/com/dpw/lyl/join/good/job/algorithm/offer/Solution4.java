package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/WGki4K
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description: 只出现一次的数字(位运算) （中等）
 */
public class Solution4 {

    public static void main(String[] args) {
        System.out.println(new Solution4().singleNumber(new int[]{2,2,3,2}));
    }

    public int singleNumber(int[] nums) {
       Map<Integer, Integer> map = new HashMap<>();
        for (int i:nums) {
            map.put(i,map.getOrDefault(i,0)+1);
        }
        AtomicReference<Integer> result = new AtomicReference<>(0);
        map.forEach((key,value)->{
            if(value == 1){
                result.set(key);
            }
        });

        return result.get();
    }


}
