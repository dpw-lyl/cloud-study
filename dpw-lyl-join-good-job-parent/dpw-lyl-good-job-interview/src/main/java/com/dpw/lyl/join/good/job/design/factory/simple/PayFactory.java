package com.dpw.lyl.join.good.job.design.factory.simple;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 支付工厂
 * @author: dengpw$
 * @create: 2023-09-04 15:11
 * @version: 1.0.0$
 **/
@AllArgsConstructor
public class PayFactory {


    private GlobalPayContext globalPayContext;

    public OpenPay getOpenPayWay(String payWay){

        return onlinePay -> (MsgResponse<Object>) globalPayContext.getGlobalPa(payWay);
    }

}