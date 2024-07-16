package com.dpw.lyl.join.good.job.Impl;

import com.dpw.lyl.join.good.job.service.LoginService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@DubboService(version = "1.0.0",interfaceClass = LoginService.class)
public class LoginServiceImpl  implements LoginService {
    @Override
    public String login(String userName, String password) {
        return null;
    }
}
