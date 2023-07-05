package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串
 * 提示:
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 * @Description: 剑指 Offer II 019. 最多删除一个字符得到回文 （简单）
 */
public class Solution19 {


    public static void main(String[] args) {
        System.out.println(new Solution19().validPalindrome("abcaaaa"));
    }


    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return delOne(s, left + 1, right) || delOne(s, left, right - 1);
            }
        }
        return true;
    }

    private boolean delOne(String s, int left, int right) {
        for (int i = left, j = right; i < right; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


}
