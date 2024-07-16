package com.dpw.lyl.join.good.job.task.handler;


import com.dpw.lyl.join.good.job.task.service.mq.MqCommonManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

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
        // 为 Kafka 接收器查找函数绑定
        Function<Flux<Message<String>>, Flux<String>> kafkaReceiver = mqCommonManager.receiveMessagesFunction("kafkaInput", String.class);
        Flux<String> kafkaMessages = kafkaReceiver.apply(Flux.empty()); // Flux.empty() 只是为了调用函数，实际中不需要传入

        // 为 RabbitMQ 接收器查找函数绑定
        Function<Flux<Message<String>>, Flux<String>> rabbitReceiver = mqCommonManager.receiveMessagesFunction("rabbitInput", String.class);
        Flux<String> rabbitMessages = rabbitReceiver.apply(Flux.empty());

        // 为 RocketMQ 接收器查找函数绑定
        Function<Flux<Message<String>>, Flux<String>> rocketReceiver = mqCommonManager.receiveMessagesFunction("rocketInput", String.class);
        Flux<String> rocketMessages = rocketReceiver.apply(Flux.empty());

        // 订阅 Flux 并处理接收到的消息
        kafkaMessages.subscribe(log::info);
        rabbitMessages.subscribe(log::info);
        rocketMessages.subscribe(log::info);
    }
}
