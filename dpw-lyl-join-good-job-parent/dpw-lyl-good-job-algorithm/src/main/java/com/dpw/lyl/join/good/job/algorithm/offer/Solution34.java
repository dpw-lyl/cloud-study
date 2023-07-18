package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/lwyVBB">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 在words[i]和order中的所有字符都是英文小写字母。
 * @Description: 剑指 Offer II 034. 外星语言是否排序（简单）
 */
public class Solution34 {


    public static void main(String[] args) {


        System.out.println(new Solution34().isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));

    }


    public boolean isAlienSorted(String[] words, String order) {
        int[] arr = new int[order.length()];
        for (int i = 0; i < order.length(); i++) {
            arr[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            boolean flag = false;
            for (int j = 0; j < words[i - 1].length() && j < words[i].length(); j++) {
                int prev = arr[words[i - 1].charAt(j) - 'a'];
                int curr = arr[words[i].charAt(j) - 'a'];
                if (prev < curr) {
                    flag = true;
                    break;
                } else if (prev > curr) {
                    return false;
                }
            }
            //满足["apple", "app"]情况
            if (!flag) {
                if (words[i - 1].length() > words[i].length()) {
                    return false;
                }
            }
        }
        return true;
    }


}
