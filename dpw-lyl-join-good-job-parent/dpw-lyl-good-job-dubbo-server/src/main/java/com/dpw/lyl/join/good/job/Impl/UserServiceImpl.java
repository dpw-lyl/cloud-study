package com.dpw.lyl.join.good.job.Impl;

import com.dpw.lyl.join.good.job.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
@DubboService(version = "1.0.0",interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Override
    public String getUserName(String userId) {
        return "user"+userId;
    }
}
