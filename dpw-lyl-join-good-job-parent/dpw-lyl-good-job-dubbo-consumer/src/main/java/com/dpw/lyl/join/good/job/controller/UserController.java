package com.dpw.lyl.join.good.job.controller;

import com.dpw.lyl.join.good.job.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @DubboReference(version = "1.0.0",interfaceClass = UserService.class)
    private UserService userService;

    @GetMapping("/user/{userId}")
    public String getUserName(@PathVariable String userId) {
        return userService.getUserName(userId);
    }

}
