package com.dpw.lyl.join.good.job.task.service.impl;

import com.dpw.lyl.join.good.job.task.service.MqMangerService;
import com.dpw.lyl.join.good.job.task.service.mq.MqCommonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
  * @description:
  * @author: Administrator
  * @create: 2024/7/16 0016 18:51
  * @version:
 */
@Service
public class MqMangerServiceImpl implements MqMangerService {


    private final MqCommonManager mqCommonManager;

    @Autowired
    public MqMangerServiceImpl(MqCommonManager mqCommonManager) {
        this.mqCommonManager = mqCommonManager;
    }


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
}
