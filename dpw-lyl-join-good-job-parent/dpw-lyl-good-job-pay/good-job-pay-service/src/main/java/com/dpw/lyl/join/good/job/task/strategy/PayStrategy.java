package com.dpw.lyl.join.good.job.task.strategy;

/**
 * @Author: dengpw
 * @createTime: 2022年08月15日 18:39:15
 * @version: 1.0.0
 * @Description: 支付策略
 */
public interface PayStrategy<T,R> {
    /**
     * @author: dengpw
     * @createTime: 2022年08月15 18:40:52
     * @description: 支付入口
     * @param: t - [T] 请求参数
     * @return: R 返回参数
     */
   R execute(T t);
}
