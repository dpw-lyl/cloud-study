package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
 * 注意：此题 matrix 输入格式为一维 01 字符串数组。
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/PLYXKQ">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description: 剑指 Offer II 040. 矩阵中最大的矩形（困难）
 */
public class Solution40 {


    public static void main(String[] args) {

        System.out.println(new Solution40().maximalRectangleByArrayStack(new String[]{"10100", "10111", "11111", "10010"}));

    }


    public int maximalRectangle(String[] matrix) {
        if (null == matrix || matrix.length == 0) {
            return 0;
        }
        int[][] arr = new int[matrix.length][matrix[0].length()];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = matrix[i].charAt(j) - '0';
            }
        }

        return 0;
    }

    public int maximalRectangleByArrayStack(String[] matrix) {
        int res = 0;
        if (null == matrix || matrix.length == 0) {
            return res;
        }
        int[] height = new int[matrix[0].length()];
        for (String s : matrix) {
            for (int j = 0, l = height.length; j < l; j++) {
                height[j] = s.charAt(j) == '0' ? 0 : height[j] + 1;
            }
            res = Math.max(res, countMaxArea(height));
        }
        return res;
    }

    private int countMaxArea(int[] height) {
        int l = height.length;
        int[] stack = new int[l + 1];
        stack[0] = -1;
        int index = 1;
        int area = 0;
        for (int i = 0; i < l; i++) {
            while (index > 0 && height[stack[index]] >= height[i]) {
                area = Math.max(area, height[stack[index--]] * (i - stack[index] - 1));
            }
            stack[++index] = i;
        }
        while (index > 0) {
            area = Math.max(area, height[stack[index--]] * (l - stack[index] - 1));
        }

        return area;
    }

    private int maximalRectangle(int[] heights) {
        int length = heights.length;
        // 因为 length 的范围 [0,200]，采用数组模拟栈即可
        int[] stack = new int[length + 1];
        stack[0] = -1;
        int index = 1;
        int area = 0;
        for (int i = 0; i < length; i++) {
            while (index > 0 && heights[stack[index]] >= heights[i]) {
                area = Math.max(area, heights[stack[index--]] * (i - stack[index] - 1));
            }
            stack[++index] = i;
        }
        while (index > 0) {
            area = Math.max(area, heights[stack[index--]] * (length - stack[index] - 1));
        }
        return area;
    }


}
