package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定非负整数数组 heights，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/0ynMMM">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 * @Description: 剑指 Offer II 039. 直方图最大矩形面积（困难）
 */
public class Solution39 {


    public static void main(String[] args) {

        System.out.println(new Solution39().largestRectangleAreaStack(new int[]{2, 1, 5, 6, 2, 3}));

    }


    /**
     * @author: dengpw
     * @createTime: 2023年07月19 10:00:05
     * @description: 双指针
     * @param: heights - [int]
     * @return: int
     */
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int left, right;
        for (int i = 0; i < heights.length; i++) {
            left = i - 1;
            right = i + 1;
            if (heights.length * heights[i] > max) {
                while (left > 0 && heights[left] >=  heights[i]) {
                    left--;
                }
                while (right < heights.length - 1 && heights[right]  >= heights[i]) {
                    right++ ;
                }
            }
            max = Math.max(max, (right - left - 1) * heights[i]);

        }
        return max;
    }


    /**
     * @author: dengpw
     * @createTime: 2023年07月19 09:59:59
     * @description: 单调栈
     * @param: heights - [int]
     * @return: int
     */
    public int largestRectangleAreaStack(int[] heights) {
        int length = heights.length;
        int max= 0 ;
        Deque<Integer> data = new ArrayDeque<>();
        data.push(-1);
        for (int i = 0; i < length; i++) {
            while (data.peek() != -1 && heights[data.peek()]>=heights[i]){
                max=Math.max(max,heights[data.pop()] * (i - data.peek()  - 1));
            }
            data.push(i);
        }
        while (data.peek() != -1){
            int i1 = heights[data.pop()] * (length - data.peek() - 1);
            max=Math.max(max,i1);
        }
        return  max;
    }

}
