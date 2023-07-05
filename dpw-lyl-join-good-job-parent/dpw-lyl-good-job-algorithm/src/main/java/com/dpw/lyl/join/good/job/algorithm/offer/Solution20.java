package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 * @Description: 剑指 Offer II 020. 回文子字符串的个数 （中等）
 */
public class Solution20 {

    private int count = 0;

    public static void main(String[] args) {
        System.out.println(new Solution20().countSubstrings("aabc"));
    }


    public int countSubstrings(String s) {
        //中心扩展，默认每个字符为回文字段中心
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i);
            count(s, i, i + 1);
        }
        return count;
    }

    private void count(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            right++;
            left--;
        }

    }
}
