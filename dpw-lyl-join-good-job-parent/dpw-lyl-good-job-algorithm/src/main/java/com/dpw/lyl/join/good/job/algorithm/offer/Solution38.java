package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 请根据每日 气温 列表 temperatures，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 * 如果气温在这之后都不会升高，请在该位置用0 来代替。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/iIQa4I">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * @Description: 剑指 Offer II 038. 每日温度（中等）
 */
public class Solution38 {


    public static void main(String[] args) {

        System.out.println(new Solution38().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));

    }


    /**
     * @author: dengpw
     * @createTime: 2023年07月18 11:12:52
     * @description:
     * 73,74,75,71,69,72,76,73
     *  0, 1, 2, 3, 4, 5, 6, 7
     * 1-0,2-1,6-2,5-2,5-4,6-5,0,0
     * @param: temperatures - [int]
     * @return: int[]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> data = new LinkedList<>();
        int[] ints = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int temperature = temperatures[i];
            while (!data.isEmpty() && temperature > temperatures[data.peek()]) {
                int pop = data.pop();
                ints[pop] = i - pop;
            }
            data.push(i);
        }
        return ints;
    }

}
