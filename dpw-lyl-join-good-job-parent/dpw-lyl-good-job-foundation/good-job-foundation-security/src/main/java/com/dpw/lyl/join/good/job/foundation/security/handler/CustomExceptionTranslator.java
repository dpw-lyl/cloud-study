/*
package com.dpw.lyl.join.good.job.foundation.security.handler;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.ResponseCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

*/
/**
 * @Author: dengpw
 * @createTime: 2022年10月31 18:25:53
 * @version: 登录超时返回
 * @Description:
 *//*

public class CustomExceptionTranslator extends DefaultWebResponseExceptionTranslator {


    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity translate = super.translate(e);
        OAuth2Exception body = (OAuth2Exception) translate.getBody();
        return new ResponseEntity<>(MsgResponse.buildSuccess(ResponseCodeEnum.LOGIN_OUT_EXP,body), translate.getHeaders(), HttpStatus.OK);
    }
}
*/
