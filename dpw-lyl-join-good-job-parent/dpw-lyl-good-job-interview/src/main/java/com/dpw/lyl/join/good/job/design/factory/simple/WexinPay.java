package com.dpw.lyl.join.good.job.design.factory.simple;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;

/**
 * @description: 微信支付
 * @author: dengpw$
 * @create: 2023-09-04 15:22
 * @version: 1.0.0$
 **/
public class WexinPay implements OpenPay {
    @Override
    public MsgResponse<Object> pay(OnlinePay onlinePay) {
        return null;
    }
}