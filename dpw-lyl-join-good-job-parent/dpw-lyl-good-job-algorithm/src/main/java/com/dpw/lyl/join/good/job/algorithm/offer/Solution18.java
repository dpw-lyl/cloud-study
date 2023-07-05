package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 * 本题中，将空字符串定义为有效的 回文串 。
 * 提示：
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 * @Description: 剑指 Offer II 018. 有效的回文 （简单）
 */
public class Solution18 {


    public static void main(String[] args) {
        System.out.println(new Solution18().isPalindrome("A man, a plan, a canal: Panama"));
    }


    public boolean isPalindrome(String s) {
        if("".equals(s)){
            return true;
        }
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(Character.isLetterOrDigit(s.charAt(i))){
                stringBuffer.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        return stringBuffer.toString().contentEquals(stringBuffer.reverse());
    }


}
