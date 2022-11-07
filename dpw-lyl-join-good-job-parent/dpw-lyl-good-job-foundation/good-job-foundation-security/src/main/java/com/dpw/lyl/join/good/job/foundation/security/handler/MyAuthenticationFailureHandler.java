package com.dpw.lyl.join.good.job.foundation.security.handler;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: dengpw
 * @createTime: 2022年09月21 15:50:19
 * @version: 1.0.0
 * @date 2021年06月16日10:48
 * @desc
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Resource
    private ObjectMapper mapper;

    private String obtainbYName(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {

        String userName = obtainbYName(httpServletRequest, "username");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
       /* AjaxResult restResponse = new AjaxResult();
        if (e instanceof AccessDeniedHandler) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", ResultCodeEnum.ERROR.getMessage());
        } else if (e instanceof AuthenticationEntryPoint) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", "登录已过期或者未登录");
        } else if (e instanceof AccountExpiredException) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", "账户已过期");
        } else if (e instanceof BadCredentialsException) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", "用户名或密码错误");
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, e.getMessage()));
        } else if (e instanceof DisabledException) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", "账户已禁用");
        } else if (e instanceof CredentialsExpiredException) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", "证书已过期");
        } else if (e instanceof LockedException) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", "用户账户已被锁定");
        } *//*else if (e instanceof InternalAuthenticationServiceException) {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", "内部身份验证服务异常");
        } *//* else {
            restResponse.put("code", ResultCodeEnum.ERROR.getCode());
            restResponse.put("msg", e.getMessage());
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, e.getMessage()));
        }*/

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(""));
    }

}
