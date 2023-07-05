package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * 提示：
 * <p>
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 * @Description: 剑指 Offer II 014. 字符串中的变位词 （中等）
 */
public class Solution14 {


    public static void main(String[] args) {
        System.out.println(new Solution14().checkInclusion("fgo", "eidbaooogf"));
    }


    public boolean checkInclusion(String s1, String s2) {
        boolean include = false;
        if (s1.length() > s2.length()) {
            return include;
        }
        int m = s1.length(), n = s2.length();

        int[] as = new int[26];
        for (int i = 0; i < m; i++) {
            --as[s1.charAt(i) - 'a'];
        }
        int a = 0;

        for (int i = 0; i < n; i++) {
            int p = s2.charAt(i) - 'a';
            ++as[p];
            while (as[p] > 0) {
                --as[s2.charAt(a) - 'a'];
                a++;
            }
            if (i - a + 1 == m) {
                return true;
            }
        }
        return include;
    }


}
