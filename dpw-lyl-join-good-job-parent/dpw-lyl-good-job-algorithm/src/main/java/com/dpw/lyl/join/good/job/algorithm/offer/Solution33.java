package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 *给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 * @Description: 剑指 Offer II 033. 变位词组（中等）
 */
public class Solution33 {



    public static void main(String[] args) {


        System.out.println(new Solution33().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

    }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> dataMap = new HashMap<>();
        for (String str:strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key =new String(charArray);
            List<String> stringList =dataMap.getOrDefault(key,new ArrayList<>());
            stringList.add(str);
            dataMap.put(key,stringList);
        }

        return new ArrayList<>(dataMap.values());
    }




}
