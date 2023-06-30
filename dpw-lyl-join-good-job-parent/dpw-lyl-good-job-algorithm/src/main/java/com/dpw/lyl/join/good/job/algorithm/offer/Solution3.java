package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组
 * 说明 :
 * 0 <= n <= 105
 * @Description: 前 n 个数字二进制中 1 的个数（简单）
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().countBits(25));
    }

    public int[] countBits(int n) {
        int[] result = new int[n+1];
        for (int i = 1; i <= n; i++) {
            //i >> 1 == i/2  i & 1 == i%2   result[i] = result[i/2] + i%2;
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }


}
