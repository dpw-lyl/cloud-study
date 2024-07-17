package com.dpw.lyl.join.good.job.pay.service;

/**
  * @description:
  * @author: Administrator
  * @create: 2024/7/16 0016 18:46
  * @version: 
 */
public interface MqMangerService {

    void sendKafkaMessage(String destination, Object payload);

    void sendRabbitmqMessage(String destination, Object payload);

    void sendRocketMqMessage(String destination, Object payload);
    void sendMqMessage(String destination, Object payload);

}
