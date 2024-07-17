/*
package com.dpw.lyl.join.good.job.foundation.security.handler;


import com.alibaba.fastjson2.JSONObject;
import com.dpw.lyl.join.good.job.foundation.constant.Constants;
import com.dpw.lyl.join.good.job.foundation.security.manager.AsyncManager;
import com.dpw.lyl.join.good.job.foundation.security.manager.factory.AsyncFactory;
import com.dpw.lyl.join.good.job.foundation.security.service.TokenService;
import com.dpw.lyl.join.good.job.foundation.utils.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

*/
/**
 * @Author: dengpw
 * @createTime: 2022年09月21 15:50:19
 * @version: 1.0.0
 * @date 2021年06月11日15:44
 * @desc
 *//*

@Slf4j
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TokenService tokenService;

    private String obtainbYName(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String clientId = obtainbYName(request, "client_id");
        String userName = obtainbYName(request, "username");
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId:" + clientId + "对应的信息不存在");
        }

        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "password");
        // 5. 通过 TokenRequest的 createOAuth2Request方法获取 OAuth2Request
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        // 6. 通过 Authentication和 OAuth2Request构造出 OAuth2Authentication
        OAuth2Authentication auth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        // 7. 通过 AuthorizationServerTokenServices 生成 OAuth2AccessToken
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(auth2Authentication);
        //TODO 用户信息抽离
        // 不影响原本逻辑，此处将token和loginUser存入redis
        tokenService.saveLoginUser(request, token.getValue(), clientId);
        // 8. 返回 Token
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token.getValue());
        data.put("additionalInformation", token.getAdditionalInformation());
        data.put("tokenType", token.getTokenType());
        data.put("expiration", token.getExpiration());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
       */
/* AjaxResult restResponse = new AjaxResult();
        restResponse.put("code", ResultCodeEnum.SUCCESS.getCode());
        restResponse.put("msg", "登录成功");
        restResponse.put("data", data);*//*

        response.setContentType("application/json;charset=UTF-8");
        log.info("登录成功,返回信息：" + JSONObject.toJSONString(""));
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.SUCCESS, MessageUtils.message("user.login.success")));
        response.getWriter().write(JSONObject.toJSONString(""));
    }

}
*/
