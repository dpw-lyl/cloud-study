package com.dpw.lyl.join.good.job.auto.task.mq;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
  * @description: 消息消费者
  * @author: Administrator
  * @create: 2023/9/23 0023 20:38
  * @version: 1.0.0
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "test",consumerGroup = "test")
public class MqConsumer implements RocketMQListener<Object> {
    @Override
    public void onMessage(Object data) {
        log.info(JSON.toJSONString(data));
    }
}
