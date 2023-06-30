package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0 给定一个字符串数组words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 * 提示：
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description: 单词长度的最大乘积 （中等）
 */
public class Solution5 {

    public static void main(String[] args) {
        System.out.println(new Solution5().official(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"}));
    }

    public int maxProduct(String[] words) {
        int amass;
        List<Integer> integers = new ArrayList<>(16);
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                amass = this.sum(words[i], words[j]);
                integers.add(amass);
            }
        }
        Optional<Integer> max = integers.stream().max(Integer::compareTo);
        amass = max.orElse(0);
        return amass;
    }

    private int sum(String word, String word1) {
        int sum = 0;
        int length = word.length();
        int length1 = word1.length();
        if (length > length1) {
            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                int i1 = word1.indexOf(c);
                if (i1 >= 0) {
                    sum = 0;
                    break;
                }
                sum = length * length1;
            }
        } else {
            for (int i = 0; i < length1; i++) {
                char c = word1.charAt(i);
                int i1 = word.indexOf(c);
                if (i1 >= 0) {
                    sum = 0;
                    break;
                }
                sum = length * length1;
            }
        }

        return sum;
    }



    /**
     * @author: dengpw
     * @createTime: 2023年06月28 10:55:03
     * @description: 官方题解
     * @param: words - [String]
     * @return: int
     *
     *
     */
    public int official(String[] words) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String str : words) {
            int key = 0;
            for (int i = 0; i <str.length() ; i++) {
                key|=1<<(str.charAt(i)-'a');
            }
            if(str.length()>map.getOrDefault(key,0)){
                map.put(key,str.length());
            }
        }
        int maxProd = 0;
        Set<Integer> keySet = map.keySet();
        for (Integer key1 : keySet) {
            Integer integer1 = map.get(key1);
            for (Integer key2 : keySet) {
                if ((key1&key2)==0){
                    Integer integer2 = map.get(key2);
                    maxProd=Math.max(maxProd,integer2*integer1);
                }
            }
        }
        return maxProd;
    }

}
