package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version:
 * 给定一个已按照升序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0开始计数 ，所以答案数组应当满足 0<= answer[0] < answer[1] <numbers.length。
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 * 提示：
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kLl5u1
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description:  排序数组中两个数字之和 （简单）
 */
public class Solution6 {

    public static void main(String[] args) {
        System.out.println(new Solution6().toSum1(new int[]{1,2,4,6,10},8));
    }


    /**
     * @author: dengpw
     * @createTime: 2023年06月28 11:47:55
     * @description: 暴力解超时
     * @param: numbers - [int]
     * @param: target - [int]
     * @return: int[]
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = i+1; j <  numbers.length; j++) {
                if(target == numbers[i]+numbers[j]){
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        return result;
    }


    /**
     * @author: dengpw
     * @createTime: 2023年06月28 11:53:24
     * @description: 双指针移动
     * @param: numbers - [int]
     * @param: target - [int]
     * @return: int[]
     */
    public int[] toSum1(int[] numbers, int target) {
        int begin = 0, end = numbers.length-1;
        while (begin<end){
            int i = numbers[begin] + numbers[end];
            if(i==target){
                return new int[]{begin,end};
            }else if(i<target){
                begin++;
            }else {
                end--;
            }
        }
        return new int[]{-1,-1};
    }

}
