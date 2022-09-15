package com.dpw.lyl.join.good.job.sso.auth.controller;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.domain.login.LoginUser;
import com.dpw.lyl.join.good.job.foundation.security.auth.AuthUtil;
import com.dpw.lyl.join.good.job.foundation.security.service.TokenService;
import com.dpw.lyl.join.good.job.foundation.security.utils.SecurityUtils;
import com.dpw.lyl.join.good.job.foundation.utils.JwtUtils;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import com.dpw.lyl.join.good.job.sso.auth.form.LoginBody;
import com.dpw.lyl.join.good.job.sso.auth.form.RegisterBody;
import com.dpw.lyl.join.good.job.sso.auth.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token 控制
 *
 * @author ruoyi
 */
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/login")
    public MsgResponse<?> login(@RequestBody LoginBody form) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return MsgResponse.buildSuccess(tokenService.createToken(userInfo));
    }

    @DeleteMapping("/logout")
    public MsgResponse<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return MsgResponse.buildSuccess();
    }

    @PostMapping("/refresh")
    public MsgResponse<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return MsgResponse.buildSuccess();
        }
        return MsgResponse.buildSuccess();
    }

    @PostMapping("/register")
    public MsgResponse<?> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return  MsgResponse.buildSuccess();
    }
}
