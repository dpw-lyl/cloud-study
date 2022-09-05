package com.dpw.lyl.join.good.job.pay.entity;

import lombok.Data;

/**
 * @Author: dengpw
 * @createTime: 2022年08月16日 14:23:18
 * @version: 1.0.0
 * @Description: 渠道支付基础返回值
 */
@Data
public class PayChannelResponseParam <T>{
    private String errorCode;
    private String errorMsg;
    private T data;
}
