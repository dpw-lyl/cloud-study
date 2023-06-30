package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/JFETK5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description: 二进制加法（简单）
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().add("101", "101"));
    }

    public String add(String a, String b) {
        StringBuilder str = new StringBuilder();
        int index = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < index; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            str.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            str.append('1');
        }
        return str.reverse().toString();
    }


}
