package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.Arrays;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1,col1) ，右下角为 (row2,col2) 。
 * 实现 NumMatrix 类：
 * NumMatrix(int[][] matrix)给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2)返回左上角 (row1,col1)、右下角(row2,col2)的子矩阵的元素总和。
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m,n <=200
 * -105<= matrix[i][j] <= 105
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * 最多调用 104 次sumRegion 方法
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/O4NDxx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description: 二维子矩阵的和 （中等）
 */
public class Solution13 {

    protected int[][] sum;

    public static void main(String[] args) {
        System.out.println(new Solution13(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}}).sumRegion(2, 1, 4, 3));
    }


    public Solution13(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
           // sum = new int[m][n + 1]; 一维前缀和
            sum=new int[m+1][n+1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    //sum[i][j + 1] = sum[i][j] + matrix[i][j]; 一维前缀和
                    //二维前缀和
                    sum[i+1][j+1]=sum[i][j+1]-sum[i][j] +sum[i+1][j] +matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int area = 0;

        //一维前缀和
        /*for (int i = row1; i <= row2; i++) {
            area += sum[i][col2 + 1] - sum[i][col1];
        }*/

        //二维前缀和

        area=sum[row2+1][col2 + 1]-sum[row2+1][col1]-sum[row1][col2 + 1]+sum[row1][col1];
        System.out.println(sum);


        return area;
    }


}
