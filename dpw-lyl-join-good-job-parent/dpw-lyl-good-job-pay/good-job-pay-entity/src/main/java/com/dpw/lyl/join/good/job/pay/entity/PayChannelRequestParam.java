package com.dpw.lyl.join.good.job.pay.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dengpw
 * @createTime: 2022年08月16日 14:17:48
 * @version: 1.0.0
 * @Description: 渠道支付基础入参
 */
@Data
public class PayChannelRequestParam<T>  implements Serializable {

    /**
     * 请求参数
     */
    private T requestParam;
    /**
     * 支付渠道
     */
    private String payChannelCode;

    /**
     * 接口名称
     */
    private String method;

    /**
     * 具体服务名称
     */
    private String serviceName;
}
