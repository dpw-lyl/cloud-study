package com.dpw.lyl.join.good.job.controller;

import com.alipay.api.AlipayApiException;
import com.ijpay.alipay.AliPayApiConfig;

/**
 * @Author: dengpw
 * @createTime: 2022年08月18日 17:03:16
 * @version: 1.0.0
 * @Description: 支付宝配置配置
 */
public abstract class AbstractForGoodJobController {
    /**
     * @author: dengpw
     * @createTime: 2022年08月18 17:05:00
     * @description: 支付宝配置配置
     * @param:
     * @return: com.ijpay.alipay.AliPayApiConfig
     */
    public abstract AliPayApiConfig getApiConfig() throws AlipayApiException;
}
