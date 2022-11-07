


package com.dpw.lyl.join.good.job.foundation.security.filter.sms;


import com.dpw.lyl.join.good.job.foundation.domain.login.MemberLoginUser;
import com.dpw.lyl.join.good.job.foundation.domain.login.SMSLoginParam;
import com.dpw.lyl.join.good.job.foundation.security.service.SMSLoginService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


/**
 * @Author: dengpw
 * @createTime: 2022年10月14 15:58:48
 * @version: 1.0.0
 * @Description: 登录鉴权
 */
public class SMSAuthenticationProvider implements AuthenticationProvider {

    private final SMSLoginService h5LoginService;

    public SMSAuthenticationProvider(SMSLoginService h5LoginService ) {
        this.h5LoginService = h5LoginService;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SMSAuthenticationToken smsAuthenticationToken = (SMSAuthenticationToken) authentication;
        //实际鉴权业务
        MemberLoginUser memberLoginUser = h5LoginService.smsLogin((SMSLoginParam) smsAuthenticationToken.getPrincipal());
        SMSAuthenticationToken  authenticationToken = new SMSAuthenticationToken(memberLoginUser, memberLoginUser.getAuthorities());
        authenticationToken.setDetails(smsAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SMSAuthenticationToken.class.isAssignableFrom(authentication);
    }



}
