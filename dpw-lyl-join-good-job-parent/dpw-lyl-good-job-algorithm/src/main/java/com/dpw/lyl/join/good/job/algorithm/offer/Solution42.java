package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 写一个RecentCounter类来计算特定时间范围内最近的请求。
 * 请实现 RecentCounter 类：
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，
 * 并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/H8086Q">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * <p>
 * 1 <= t <= 109
 * 保证每次对 ping 调用所使用的 t 值都 严格递增
 * 至多调用 ping 方法 104 次
 * @Description: 剑指 Offer II 042. 最近请求次数（简单）
 */
public class Solution42 {

    private Deque<Integer> deque;


    public static void main(String[] args) {

        Solution42 instance = new Solution42();
        List<Integer> data = new ArrayList<>();
        data.add(instance.ping(1));
        data.add(instance.ping(100));
        data.add(instance.ping(3001));
        data.add(instance.ping(3002));
        System.out.println(data);


    }

    public Solution42() {
        this.deque = new LinkedList<>();

    }

    public int ping(int t) {
        deque.offer(t);
        while (deque.peek() < t - 3000) {
            deque.pop();
        }
        return deque.size();
    }

}
