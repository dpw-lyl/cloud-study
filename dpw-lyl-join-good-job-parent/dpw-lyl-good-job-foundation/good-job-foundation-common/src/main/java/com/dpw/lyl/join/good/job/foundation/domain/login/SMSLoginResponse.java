package com.dpw.lyl.join.good.job.foundation.domain.login;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author: dengwp
 * @createTime: 2022年10月13日 11:35:51
 * @version: 1.0.0
 * @Description: H5登录返回数据
 */
@Data
public class SMSLoginResponse {

    /**
     * 登录令牌
     */
    private String token;

    /**
     * 其它信息
     */
    private Map<String, Object> additionalInformation;

    /**
     * 令牌类型
     */
    private String tokenType;

    /**
     * 有效
     */
    private Date expiration;


}
