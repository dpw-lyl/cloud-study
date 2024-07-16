package com.dpw.lyl.join.good.job.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public interface LoginService {

    String login(String userName, String password);
}
