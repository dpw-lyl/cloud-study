package com.dpw.lyl.join.good.job.algorithm.longestpalindromicsubstring;

/**
 * @Author: dengpw
 * @createTime: 2023年04月19日 17:56:26
 * @version: 1.0.0
 * @Description: 最长回文串(动态规划实现)
 * Question
 * 给定一个字符串，要求这个字符串当中最长的回文串。
 * ## 示例
 * ```
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Input: "cbbd"
 * Output: "bb"
 * ```
 */
public class LongestPalindromicSubstring {


    /**
     * @author: dengpw
     * @createTime: 2023年04月20 09:15:04
     * @description: 动态规划---<a href="https://blog.csdn.net/qq_35374224/article/details/128233019">...</a>
     * @param: s - [String]
     * @return: java.lang.String
     */
    public String longestPalindromeByDP(String s) {

        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        //当前回文子串的最大长度
        int maxLen = 1;
        //最长回文子串的左角标
        int left = 0;
        //最长回文子串的右角标
        int right = 0;
        //先处理边界值
        dp[length - 1][length - 1] = true;
        for (int i = 0; i < length - 1; i++) {
            //对角线
            dp[i][i] = true;
            //次对角线
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            //判断次对角线中是否含有最终答案
            if (dp[i][i + 1]) {
                maxLen = 2;
                left = i;
                right = i + 1;
            }
        }
        for (int j = 2; j < length; j++) {
            for (int i = 0; i <= j - 2; i++) {
                //当前两个字符是否相等
                if (s.charAt(i) == s.charAt(j)) {
                    //如果相等只需要取子串对应的值
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    //如果不相等，那么当前肯定不是回文子串
                    dp[i][j] = false;
                }
                //判断是否最长
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }


    public String longestString(String param) {
        if (null == param || param.length() <= 1) {
            return param;
        }
        //字符串长度
        int length = param.length();
        //创建二维节点判断节点是否对称
        boolean dp[][] = new boolean[length][length];

        //当前回文子串的左坐标
        int left = 0;
        //当前回文子串的有坐标
        int right = 0;
        //假设当前字串长度为1
        int currentStrLength = 1;
        //边界值
        dp[length - 1][length - 1] = true;
        //对称节点x=y(param=aa||BB);x=y+1(param=aba,ava);
        for (int i = 0; i < length - 1; i++) {
            //x=y,i=x
            dp[i][i] = true;
            //x=y+1
            dp[i][i + 1] = (param.charAt(i) == param.charAt(i + 1));
            if (dp[i][i + 1]) {
                currentStrLength = 2;
                left = i;
                right = i + 1;
            }
        }
        for (int j = 2; j < length; j++) {
            for (int i = 0; i <= j - 2; i++) {
                if (param.charAt(i) == param.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j] && j - i + 1 > currentStrLength) {
                    currentStrLength = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return param.substring(left, right + 1);
    }


    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String s = longestPalindromicSubstring.longestString("abcdefg3131");
        System.out.println(s);
    }

}
