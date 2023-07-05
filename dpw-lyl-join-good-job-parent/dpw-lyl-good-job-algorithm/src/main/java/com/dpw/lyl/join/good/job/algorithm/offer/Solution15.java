package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定两个字符串s和p，找到s中所有 p 的变位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 变位词 指字母相同，但排列不同的字符串。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/VabMRr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * @Description: 剑指 Offer II 015. 字符串中的所有变位词 （中等）
 */
public class Solution15 {


    public static void main(String[] args) {
        System.out.println(new Solution15().findAnagrams("cbaebabacd", "abc"));
    }


    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int m = s.length(), n = p.length();
        if (m < n) {
            return res;
        }
        int[] as = new int[26];
        for (int i = 0; i < n; i++) {
            //给对应位置赋值为-1
            --as[p.charAt(i) - 'a'];
        }

        int index = 0;

        for (int i = 0; i < m; i++) {
            int value = s.charAt(i) - 'a';
            //给对应字符位置+1，如果相同-1+1=0
            ++as[value];
            //多个相同字符。使得对应值大于1
            while (as[value] > 0) {
                --as[s.charAt(index) - 'a'];
                index++;
            }
            //当区间等于字串长度时满足（变位词）条件
            if (i - index + 1 == n) {
                res.add(index);
            }
        }
        return res;
    }


}
