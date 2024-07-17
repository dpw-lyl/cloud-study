package com.dpw.lyl.join.good.job.pay.service.impl;

import com.dpw.lyl.join.good.job.pay.service.mq.MqCommonManager;
import com.dpw.lyl.join.good.job.pay.service.MqMangerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
/**
  * @description:
  * @author: Administrator
  * @create: 2024/7/16 0016 18:51
  * @version:
 */
@Service
@AllArgsConstructor
public class MqMangerServiceImpl implements MqMangerService {


    private final MqCommonManager mqCommonManager;




    @Override
    public void sendKafkaMessage(String destination, Object payload) {
        mqCommonManager.sendMessage(destination, payload);
    }

    @Override
    public void sendRabbitmqMessage(String destination, Object payload) {
        mqCommonManager.sendMessage(destination, payload);
    }

    @Override
    public void sendRocketMqMessage(String destination, Object payload) {
        mqCommonManager.sendMessage(destination, payload);
    }

    @Override
    public void sendMqMessage(String destination, Object payload) {
        mqCommonManager.sendMessage(destination, payload);
    }
}
