package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * @Description: 剑指 Offer II 016. 不含重复字符的最长子字符串 （中等）
 */
public class Solution16 {


    public static void main(String[] args) {
        System.out.println(new Solution16().lengthOfLongestSubstring("pwwkew"));
    }


    public int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        int length = 0, left = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            while (set.contains(s.charAt(i))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(i));
            length = Math.max(length, i - left + 1);
        }
        return length;
    }


}
