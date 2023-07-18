package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * 来源：力扣（LeetCode）
 * 链接：h<a href="ttps://leetcode.cn/problems/XagZNi">...</a> * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * @Description: 剑指 Offer II 037. 小行星碰撞（中等）
 */
public class Solution37 {


    public static void main(String[] args) {

        System.out.println(new Solution37().asteroidCollision(new int[]{5,10,-5}));

    }


    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> data = new LinkedList<>();
        for (int asteroid:asteroids) {
            boolean alive=true;
            while (alive && asteroid<0 && !data.isEmpty() && data.peek() > 0){
                //判断是否爆炸
                alive=data.peek()<-asteroid;
                if(data.peek() <= -asteroid){
                    data.pop();
                }
            }
            if(alive){
                //没满足爆炸条件，压入栈
                data.push(asteroid);
            }
        }
        int size = data.size();
        int[] ints = new int[size];
        for (int i = size-1; i >= 0 ; i--) {
            ints[i]=data.pop();
        }
        return ints;
    }

}
