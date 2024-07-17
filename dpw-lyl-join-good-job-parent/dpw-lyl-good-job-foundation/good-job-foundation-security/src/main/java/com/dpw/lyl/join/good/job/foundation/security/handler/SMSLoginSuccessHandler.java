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
 *//*

package com.dpw.lyl.join.good.job.foundation.security.handler;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSONObject;
import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.constant.Constants;
import com.dpw.lyl.join.good.job.foundation.domain.login.MemberLoginUser;
import com.dpw.lyl.join.good.job.foundation.domain.login.SMSLoginParam;
import com.dpw.lyl.join.good.job.foundation.domain.login.SMSLoginResponse;
import com.dpw.lyl.join.good.job.foundation.security.manager.AsyncManager;
import com.dpw.lyl.join.good.job.foundation.security.manager.factory.AsyncFactory;
import com.dpw.lyl.join.good.job.foundation.security.service.TokenService;
import com.dpw.lyl.join.good.job.foundation.utils.MessageUtils;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

*/
/**
 * @author lengleng
 * @date 2018/1/8
 * 手机号登录成功，返回oauth token
 *//*

@Slf4j
@Component("smsLoginSuccessHandler")
public class SMSLoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final ObjectMapper objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();
    private final TypeFactory typeFactory = objectMapper.getTypeFactory();

    public static final String BASIC_ = "Basic ";

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private TokenService tokenService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SMSLoginParam h5LoginParam = obtainSMS(request);
        String clientId =h5LoginParam.getClientId();
        if (StringUtils.isEmpty(clientId)) {
            // TODO: 2022/10/18 测试完成复原
            throw new UnapprovedClientAuthenticationException("client信息为空");
        }
        String phoneNumber = h5LoginParam.getPhoneNumber();
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = new TokenRequest(MapUtil.newHashMap(), clientId, clientDetails.getScope(), "SMS");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        log.info("获取token 成功：{}", oAuth2AccessToken.getValue());
        // TODO: 2022/10/14 分布式会话need
        MemberLoginUser memberLoginUser = tokenService.saveMemberLoginUser(request, authentication, oAuth2AccessToken, clientId);
        SMSLoginResponse h5LoginResponse = new SMSLoginResponse();
        h5LoginResponse.setToken(oAuth2AccessToken.getValue());
        h5LoginResponse.setExpiration(oAuth2AccessToken.getExpiration());
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        //登录后增加余额信息
        additionalInformation.put("memberCardAmount",String.valueOf(memberLoginUser.getMemberCardAmount()));
        h5LoginResponse.setAdditionalInformation(additionalInformation);
        h5LoginResponse.setTokenType(oAuth2AccessToken.getTokenType());
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        log.info("登录成功,返回信息：" + JSONObject.toJSONString(MsgResponse.buildSuccess(h5LoginResponse)));
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(phoneNumber, Constants.SUCCESS, MessageUtils.message("user.login.success")));
        response.getWriter().write(JSONObject.toJSONString(MsgResponse.buildSuccess(h5LoginResponse)));

    }
    protected SMSLoginParam obtainSMS(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getReader(), typeFactory.constructType(SMSLoginParam.class));
    }

}
*/
