package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 设计一个支持在平均时间复杂度 O(1)下，执行以下操作的数据结构：
 * insert(val)：当元素 val 不存在时返回 true，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true，并从集合中移除该项，否则返回 false。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/FortPu">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * -2^31 <= val <= 2^31 - 1
 * 最多进行 2 * 105 次insert ， remove 和 getRandom 方法调用
 * 当调用getRandom 方法时，集合中至少有一个元素
 * @Description: 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器（中等）
 */
public class Solution30 {


    private final Map<Integer,Integer> map;

    private final List<Integer> list;

    private final Random random;

    private int size;



    public static void main(String[] args) {

        Solution30 solution30 = new Solution30();
        for (int i = 0; i < 5; i++) {
            solution30.insert(i);
            for (int j = 0; j < 5; j++) {
                solution30.insert(j);
            }
        }
        System.out.println(solution30.map);
        solution30.remove(2);
        System.out.println(solution30.map);
        System.out.println(solution30.list);
    }


    /** Initialize your data structure here. */
    public Solution30() {
        this.list=new ArrayList<>();
        this.map=new HashMap<>();
        this.random=new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean b = map.containsKey(val);
        if(b){
            return false;
        }
        list.add(val);
        map.put(val,size++);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int index = map.get(val);
        int lastNum = list.get(--size);
        list.set(index,lastNum);
        map.put(lastNum,index);
        map.remove(val);
        list.remove(size);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(size));
    }

}
