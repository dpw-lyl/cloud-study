package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
 * 注意：若s 和 t中每个字符出现的次数都相同且字符顺序不完全相同，则称s 和 t互为变位词（字母异位词）。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/dKk3P7">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示:
 * 1 <= s.length, t.length <= 5 * 104
 * s and t 仅包含小写字母
 * @Description: 剑指 Offer II 032. 有效的变位词（简单）
 */
public class Solution32 {



    public static void main(String[] args) {


        System.out.println(new Solution32().isAnagram("asasadd","sasadda"));

    }


    public boolean isAnagram(String s, String t) {
        if(s.equals(t) || s.length() != t.length() ){
            return false;
        }
        int[] arr= new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++arr[s.charAt(i)-'a'];
        }

        for (int i = 0; i < t.length(); i++) {
            --arr[t.charAt(i)-'a'];
            if(arr[t.charAt(i) - 'a'] < 0 ){
                return false;
            }
        }
        return true;
    }

    public boolean isAnagramUnicode(String s, String t) {
        if(s.equals(t) || s.length() != t.length()){
            return false;
        }
        Map<Character, Integer> dataMap = new HashMap<>();
        for (int i = 0; i < s.length();i++) {
            char c = s.charAt(i);
            dataMap.put(c,dataMap.getOrDefault(c,0)+1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            dataMap.put(c,dataMap.getOrDefault(c,0)-1);
            if(dataMap.get(c)<0){
                return false;
            }
        }
        return true;
    }


}
