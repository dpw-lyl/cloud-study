package com.dpw.lyl.join.good.job.controller;

import com.dpw.lyl.join.good.job.domian.PayOrder;
import com.dpw.lyl.join.good.job.queue.OrderProcessor;
import com.dpw.lyl.join.good.job.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class UserController {

    @DubboReference(version = "1.0.0",interfaceClass = UserService.class)
    private UserService userService;

    @GetMapping("/user/{userId}")
    public String getUserName(@PathVariable String userId) {
        return userService.getUserName(userId);
    }


    @GetMapping("/user/{userId}")
    public String batchPayOrders(@PathVariable String userId) {
        OrderProcessor processor =new OrderProcessor();


        processor.addOrder(new PayOrder(System.currentTimeMillis(),  PayOrder.Type.NORMAL,1));

        while (!processor.orderQueue.isEmpty()) {
        log.info("{}",processor.getNextOrder());
        }

        return "";
    }
}
