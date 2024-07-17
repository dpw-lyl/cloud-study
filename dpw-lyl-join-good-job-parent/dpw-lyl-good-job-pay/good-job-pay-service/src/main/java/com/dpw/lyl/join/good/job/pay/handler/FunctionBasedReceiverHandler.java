package com.dpw.lyl.join.good.job.pay.handler;


import com.dpw.lyl.join.good.job.pay.service.mq.MqCommonManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Administrator
 * @create: 2024/7/16 0016 18:45
 * @version:
 */
@Service
@Slf4j
public class FunctionBasedReceiverHandler {
    private final MqCommonManager mqCommonManager;

    @Autowired
    public FunctionBasedReceiverHandler(MqCommonManager mqCommonManager) {
        this.mqCommonManager = mqCommonManager;
    }


    public void receiveMessages() {
        // 为 RabbitMQ 接收器查找函数绑定
        mqCommonManager.processReceivedMessages("myRabbitInputQueue");


    }
}
