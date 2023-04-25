package com.dpw.lyl.join.good.job.algorithm.serpentinematrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年04月20日 10:37:34
 * @version: 1.0.0
 * @Description: 蛇形矩阵(N)
 * Question：给定一个字符串，和一个整数n，将它排列成一个n行的蛇形返回。
 * ## 示例
 * <p>
 * ```
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * ```
 * <p>
 * 给定字符串和蛇形排列占据的行数，要求返回重新排列后的串
 */
public class SerpentineMatrix {

    public String serpentineMatrixString(String param, int num) {
        if (num < 2) return param;
        List<StringBuilder> lines = new ArrayList<>();
        for (int i = 0; i < num; i++) lines.add(new StringBuilder());
        int row = 0, flag = -1;//行号, 标记方向
        for (char c : param.toCharArray()) {
            lines.get(row).append(c); //将每个字符加到对应行字符串后面
            if (row == 0 || row == num - 1) flag = -flag;  //反转方向
            row += flag;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : lines) {  //连接每一行
            ans.append(sb);
        }
        return ans.toString();
    }


    public String serpentineString(String str, int num) {
        if (num < 2) {
            return str;
        }
        List<StringBuilder> lines = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            lines.add(new StringBuilder());
        }
        int index = 0, forward = -1;

        for (char c : str.toCharArray()) {
            lines.get(index).append(c);
            if (index == 0 || index == num - 1) {
                forward = -forward;
            }
            index += forward;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder s : lines) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }


    public String serpentineStr(String string, int num) {
        if (num < 2) {
            return string;
        }
        List<StringBuilder> lines = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            lines.add(new StringBuilder());
        }
        int index = 0, forward = -1;
        for (char c : string.toCharArray()) {
            lines.get(index).append(c);
            if (index == 0 || index == num - 1) forward = -forward;
            index += forward;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder s : lines) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }


    public String longestStr(String str) {
        if (null == str || str.length() < 2) {
            return str;
        }
        int length = str.length();
        boolean[][] dp = new boolean[length][length];
        int left = 0, right = 0, maxLen = 1;
        //x=y    x= y+1
        dp[length - 1][length - 1] = true;
        for (int i = 0; i < length - 1; i++) {
            //x=y
            dp[i][i] = true;
            //x=y+1
            dp[i][i + 1] = str.charAt(i) == str.charAt(i + 1);
            if (dp[i][i + 1]) {
                maxLen = 2;
                left = i;
                right = i + 1;
            }
        }

        for (int j = 2; j < length; j++) {
            for (int i = 0; i <= j - 2; i++) {
                if (str.charAt(j) == str.charAt(i)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j] && maxLen < j - i + 1) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return str.substring(left, right + 1);
    }


    public static void main(String[] args) {

        SerpentineMatrix serpentineMatrix = new SerpentineMatrix();
        String s = serpentineMatrix.serpentineStr("abcdefg3131", 5);
        String s1 = serpentineMatrix.longestStr("abcdefg3131");
        System.out.println(s + "---------" + s1);
    }


}
