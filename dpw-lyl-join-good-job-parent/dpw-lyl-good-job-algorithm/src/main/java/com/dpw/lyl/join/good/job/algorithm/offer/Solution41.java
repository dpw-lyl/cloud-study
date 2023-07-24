package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 * 实现 MovingAverage 类：
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val)成员函数 next每次调用的时候都会往滑动窗口增加一个整数，
 * 请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/qIsx9U">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * 最多调用 next 方法 104 次
 * @Description: 剑指 Offer II 041. 滑动窗口的平均值（简单）
 */
public class Solution41 {

    private int size;

    private Deque<Integer> deque;

    private double sum;

    public static void main(String[] args) {

    }

    public Solution41(int size) {
        this.size = size;
        this.deque = new LinkedList<>();
        sum = 0;
    }

    public double next(int val) {
        if (deque.size() == size) {
            sum -= deque.poll();
        }
        deque.offer(val);
        sum += val;
        return sum / deque.size();

    }


}
