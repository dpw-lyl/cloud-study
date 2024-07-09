package com.dpw.lyl.join.good.job.design.factory.simple;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 支付管理器
 * @author: dengpw$
 * @create: 2023-09-04 15:31
 * @version: 1.0.0$
 **/
public class GlobalPayContext implements ApplicationContextAware {

    /**
     *内存（redis需要注意序列化）
     */
    private static final Map<String, OpenPay> GLOBAL_PAY_CONTEXT = new ConcurrentHashMap<>(16);

    @Resource
    private LoadPayWayConfig loadPayWayConfig;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        loadPayWayConfig.getPayWays().forEach((k,v)-> Objects.requireNonNull(GLOBAL_PAY_CONTEXT.put(v, (OpenPay) applicationContext.getBean(v))));
    }


    /**
     * @description:
     * @author: dengpw
     * @date: 2023/9/4 0004 17:23
     * @param: payWay
     * @return: com.dpw.lyl.join.good.job.design.factory.simple.OpenPay
     **/
    public  OpenPay getGlobalPa(String payWay) {
        return GLOBAL_PAY_CONTEXT.get(payWay);

    }
}