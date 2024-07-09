package com.dpw.lyl.join.good.job.design.factory.simple;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;

/**
 * @description: 支付接口
 * @author: dengpw
 * @create: 2023-09-04 15:09
 * @version: 1.0.0
 **/
public interface OpenPay {

    MsgResponse<Object> pay(OnlinePay onlinePay);

}
