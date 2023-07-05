package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定两个字符串 s 和t 。返回 s 中包含t的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/M1oyTv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * @Description: 剑指 Offer II 017. 含有所有字符的最短字符串 （困难）
 */
public class Solution17 {


    public static void main(String[] args) {
        System.out.println(new Solution17().minWindow("ADOBECODEBANC", "ABC"));
    }


    public String minWindow(String s, String t) {
        int[] count = new int[52];
        int sum = 0;
        for (int i = 0; i < t.length(); i++) {
            int index = index(t.charAt(i));
            --count[index];
            if (count[index] == -1) {
                sum++;
            }
        }
        int left = 0, ans = s.length() + 1, begin = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = index(s.charAt(i));
            ++count[index];
            if (count[index] == 0) {
                sum--;
            }
            while (left < i && count[index(s.charAt(left))] > 0) {
                count[index(s.charAt(left))]--;
                left++;
            }
            if (sum == 0 && i - left + 1 < ans) {
                ans = i - left + 1;
                begin = left;
                end = i;
            }
        }

        if (ans == s.length() + 1) {
            return "";
        }

        return s.substring(begin, end + 1);
    }

    private int index(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        } else {
            return c - 'A' + 26;
        }
    }

}
