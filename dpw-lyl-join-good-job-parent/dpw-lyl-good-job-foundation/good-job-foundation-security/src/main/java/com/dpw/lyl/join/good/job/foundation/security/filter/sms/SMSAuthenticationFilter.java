/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.dpw.lyl.join.good.job.foundation.security.filter.sms;


import com.dpw.lyl.join.good.job.foundation.domain.login.SMSLoginParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: dengpw
 * @createTime: 2022年10月14 14:20:03
 * @version: 1.0.0
 * @Description: 手机号短信登录验证过滤器
 */
@Slf4j
public class SMSAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "SMS";
    private static final ObjectMapper objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();
    private final TypeFactory typeFactory = objectMapper.getTypeFactory();
    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    private boolean postOnly = true;
    private final HandlerExceptionResolver resolver;

    public SMSAuthenticationFilter(HandlerExceptionResolver resolver) {
        super(new AntPathRequestMatcher("", "POST"));
        this.resolver = resolver;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException {
        //判断请求方式
        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        SMSLoginParam h5LoginParam = obtainSMS(request);
        SMSAuthenticationToken mobileAuthenticationToken = new SMSAuthenticationToken(h5LoginParam);
        setDetails(request, mobileAuthenticationToken);
        Authentication authenticate = null;
        try {
            authenticate = this.getAuthenticationManager().authenticate(mobileAuthenticationToken);
        } catch (Exception e) {
            //处理自定义异常
            log.error("短信验证码登录错误：{}", e.toString());
            e.printStackTrace();
            resolver.resolveException(request, response, null, e);
        }
        return authenticate;
    }

    protected SMSLoginParam obtainSMS(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getReader(), typeFactory.constructType(SMSLoginParam.class));
    }

    protected void setDetails(HttpServletRequest request,
                              SMSAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getMobileParameter() {
        return mobileParameter;
    }

    public void setMobileParameter(String mobileParameter) {
        this.mobileParameter = mobileParameter;
    }

    public boolean isPostOnly() {
        return postOnly;
    }
}

