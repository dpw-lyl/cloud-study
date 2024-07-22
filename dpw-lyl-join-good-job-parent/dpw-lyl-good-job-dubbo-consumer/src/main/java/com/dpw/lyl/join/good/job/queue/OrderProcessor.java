package com.dpw.lyl.join.good.job.queue;

import com.dpw.lyl.join.good.job.domian.PayOrder;
import lombok.Data;

import java.util.PriorityQueue;

/**
 * 订单处理器、优先级队列处理
 * @author Administrator
 */
@Data
public class OrderProcessor {

    public final PriorityQueue<PayOrder> orderQueue = new PriorityQueue<>();


    public void addOrder(PayOrder order) {
        orderQueue.offer(order);
    }

    public PayOrder getNextOrder() {
        return orderQueue.poll();
    }
}
