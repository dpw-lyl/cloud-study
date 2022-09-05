package com.dpw.lyl.join.good.job.strategy;

import com.dpw.lyl.join.good.job.entity.PayChannelRequestParam;
import com.dpw.lyl.join.good.job.entity.PayChannelResponseParam;

/**
 * @Author: dengpw
 * @createTime: 2022年08月16日 14:43:07
 * @version: 1.0.0
 * @Description: 渠道支付抽象类
 */
public abstract class AbstractChannelPayStrategy<T extends PayChannelRequestParam,R extends PayChannelResponseParam> implements PayStrategy<T,R>{

    /**
     * @author: dengpw
     * @createTime: 2022年08月16 15:02:05
     * @description:  统一支付调用
     * @param: param - [T]
     * @return: R
     */
    @Override
    public R execute(T param) {
        checkAliPayCommonParam(param);
        return invoke(param);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月16 15:03:54
     * @description: 参数校验
     * @param: param - [T]
     * @return: void
     */
    protected abstract void checkAliPayCommonParam(T param);

    /**
     * @author: dengpw
     * @createTime: 2022年08月18 14:59:36
     * @description: 反射调用实际方法
     * @param: param - [T] 入参
     * @return: R 返回
     */
    protected abstract R invoke(T param);

}
