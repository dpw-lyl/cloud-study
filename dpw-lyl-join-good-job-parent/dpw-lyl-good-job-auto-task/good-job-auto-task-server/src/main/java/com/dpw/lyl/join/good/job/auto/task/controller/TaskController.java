package com.dpw.lyl.join.good.job.auto.task.controller;

import com.dpw.lyl.join.good.job.task.entity.domain.TestMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dengpw
 * @createTime: 2022年09月09日 14:58:07
 * @version: 1.0.0
 * @Description: 物联网测试入口
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private RocketMQTemplate rocketMqTemplate;

    @GetMapping("getTask")
    public String getTask(){
        return "test";
    }

    @GetMapping("sendMsg")
    public String sendMsg(){
        TestMsg testMsg = new TestMsg();
        testMsg.setLove("123");
        testMsg.setName("张三");

        rocketMqTemplate.syncSend("test", testMsg);
        return "test";
    }

}
