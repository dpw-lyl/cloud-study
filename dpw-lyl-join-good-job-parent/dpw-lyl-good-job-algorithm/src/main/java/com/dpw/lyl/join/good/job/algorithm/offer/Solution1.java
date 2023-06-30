package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 * @Description: 整数除法（简单）
 */
public class Solution1 {

    public static void main(String[] args) {
        System.out.println(new Solution1().divide(2, 2));
    }

    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        int result = 0;
        boolean flag = ((a > 0 && b > 0) || (a < 0 && b < 0));
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        while (a <= b) {
            int k = 1;
            int temp = b;
            while (a - temp < temp) {
                temp += temp;
                k += k;
            }
            result += k;
            a -= temp;
        }
        return flag ? result : -result;
    }


}
