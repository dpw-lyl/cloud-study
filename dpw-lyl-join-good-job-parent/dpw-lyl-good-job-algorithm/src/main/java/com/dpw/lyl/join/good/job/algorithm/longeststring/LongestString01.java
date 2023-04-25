package com.dpw.lyl.join.good.job.algorithm.longeststring;

import static java.lang.Math.max;

/**
 * @Author: dengpw
 * @createTime: 2023年04月19日 10:12:55
 * @version: 1.0.0
 * @Description: 最长字串（字符串）
 * Question:无重复字符的最长子串:给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。
 */
public class LongestString01 {
    public int getLongestString01(String str){
        int longest=0;
        if(null == str || str.length() == 0){
            return longest;
        }
        int[] am  =new int[256];
        char[] charArray = str.toCharArray();
        int a = 0, b= -1;
        int length = charArray.length;
        while ( a<length ) {
            if (b  < length -1 && am[charArray[b+1]] == 0) {
                b++;
                am[charArray[b]]++;
            } else {
                am[charArray[a]]--;
                a++;
            }
            longest = Math.max(longest, b - a + 1);
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestString01 longestString01 = new LongestString01();
        System.out.println(longestString01.getLongestString01("qaqnvc1234252525252"));
    }

}
