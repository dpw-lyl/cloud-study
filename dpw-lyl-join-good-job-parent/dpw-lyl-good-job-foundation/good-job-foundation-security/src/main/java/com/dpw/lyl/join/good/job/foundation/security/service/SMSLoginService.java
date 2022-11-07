package com.dpw.lyl.join.good.job.foundation.security.service;

import com.dpw.lyl.join.good.job.foundation.biz.BizException;
import com.dpw.lyl.join.good.job.foundation.domain.login.MemberLoginUser;
import com.dpw.lyl.join.good.job.foundation.domain.login.SMSLoginParam;

/**
 * @Author: dengpw
 * @createTime: 2022年09月22日 11:31:44
 * @version: 1.0.0.
 * @Description: 移动端(H5)登录相关接口
 */
public interface SMSLoginService {

    /**
     * @author: dengpw
     * @createTime: 2022年10月28 09:24:02
     * @description: 微信公众号短信登录入口
     * @param: h5LoginParam - [SMSLoginParam]
     * @return: com.octv.member.common.core.domain.h5.MemberLoginUser
     */
    MemberLoginUser smsLogin(SMSLoginParam h5LoginParam) throws BizException;
}
