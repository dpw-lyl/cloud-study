package com.dpw.lyl.join.good.job.design.factory.simple;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;

/**
 * @description: 银行支付
 * @author: dengpw$
 * @create: 2023-09-04 15:22
 * @version: 1.0.0$
 **/

public class BankPay implements OpenPay {
    @Override
    public MsgResponse<Object> pay(OnlinePay onlinePay) {
        return null;
    }
}