package com.dpw.lyl.join.good.job.algorithm.od;

import java.util.Scanner;

/**
 * @Author: dengpw
 * @createTime: 2023年07月19日 15:40:09
 * @version: 1.0.0
 *
 * @Description: 最长公共字串
 */
public class Main01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text1 = scanner.nextLine();
        String text2 = scanner.nextLine();
        // String paramShort = text1.length() <= text2.length() ? text1 : text2;
        //  String paramLong = text1.length() <= text2.length() ? text2 : text1;
        System.out.println(new Main01().longestCommonSubsequenceDp(text1, text2));
    }

    /**
     * @author: dengpw
     * @createTime: 2023年07月20 10:10:55
     * @description: 双指针
     * @param: text1 - [String]
     * @param: text2 - [String]
     * @return: java.lang.String
     */
    public String longestCommonSubsequence(String text1, String text2) {
        int start = 0, end = 1;
        String res = "";
        while (end < text2.length()) {
            String substring = text2.substring(start, end);
            if (text1.contains(substring)) {
                res = substring;
            } else {
                start++;
            }
            end++;
        }
        return res;
    }


    /**
     * @author: dengpw
     * @createTime: 2023年07月20 10:11:26
     * @description:
     * @param: txt1 - [String]
     * @param: txt2 - [String]
     * @return: java.lang.String
     */
    public String longestCommonSubsequence2(String txt1, String txt2) {
        String result = "";
        for (int i = 0; i < txt1.length() - 1; ++i) {
            for (int j = i + 1; j <= txt1.length(); ++j) {
                String tmp = txt1.substring(i, j);
                if (tmp.length() > result.length() && txt2.contains(tmp)) {
                    result = tmp;
                }
            }
        }
        return result;
    }

    /**
     * @author: dengpw
     * @createTime: 2023年07月20 10:10:30
     * @description: 动态规划
     * @param: txt1 - [String]
     * @param: txt2 - [String]
     * @return: java.lang.String
     */
    public String longestCommonSubsequenceDp(String txt1, String txt2) {
        String result = "";
        int[][] dp = new int[txt1.length() + 1][txt2.length() + 1];
        int max = 0, index = 0;
        for (int i = 0; i < txt1.length(); i++) {
            for (int j = 0; j < txt2.length(); j++) {
                if (txt1.charAt(i) == txt2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1] > max) {
                        index = i + 1;
                        max = dp[i + 1][j + 1];
                    }
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        if (index != -1) {
            result = txt1.substring(index - max, index);
        }
        return result;
    }
}


