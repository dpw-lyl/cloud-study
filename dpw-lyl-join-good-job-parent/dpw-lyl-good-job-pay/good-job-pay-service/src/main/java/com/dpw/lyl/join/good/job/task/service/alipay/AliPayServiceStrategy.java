package com.dpw.lyl.join.good.job.task.service.alipay;



import com.dpw.lyl.join.good.job.pay.constant.PayServiceStrategyConstant;
import com.dpw.lyl.join.good.job.pay.entity.AliPayRequestParam;
import com.dpw.lyl.join.good.job.pay.entity.AliPayResponseParam;
import com.dpw.lyl.join.good.job.pay.entity.PayChannelResponseParam;
import com.dpw.lyl.join.good.job.foundation.utils.SpringUtils;
import com.dpw.lyl.join.good.job.task.strategy.AbstractChannelPayStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: dengpw
 * @createTime: 2022年08月16日 15:47:29
 * @version: 1.0.0
 * @Description: 支付宝支付服务
 */
@Slf4j
@Component(PayServiceStrategyConstant.ALI_PAY)
public class AliPayServiceStrategy extends AbstractChannelPayStrategy<AliPayRequestParam, AliPayResponseParam> {

    private static final ObjectMapper objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();

    private final TypeFactory typeFactory = objectMapper.getTypeFactory();

    /**
     * @author: dengpw
     * @createTime: 2022年08月17 15:31:50
     * @description: 校验参数
     * @param: param - [AliPayRequestParam]
     * @return: void
     */
    @Override
    protected void checkAliPayCommonParam(AliPayRequestParam param) {
        // TODO: 2022/8/17  公共参数校验

    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月18 15:00:50
     * @description: 实际业务调用方法
     * @param: param - [AliPayRequestParam]
     * @return: com.dpw.lyl.join.good.job.entity.AliPayResponseParam
     */
    @Override
    protected AliPayResponseParam invoke(AliPayRequestParam param) {
        Class<?> aClass = SpringUtils.getBean(param.getServiceName()).getClass();
        PayChannelResponseParam responseParam = null;
        try {
            Method method = aClass.getMethod(param.getMethod(), param.getRequestParam().getClass());
            responseParam = (PayChannelResponseParam) method.invoke( SpringUtils.getBean(param.getServiceName()), param.getRequestParam());
        } catch (NoSuchMethodException  | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return (AliPayResponseParam) responseParam;
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月17 15:32:39
     * @description:
     * @param: payRequestParam - [AliPayRequestParam]
     * @return: com.dpw.lyl.join.good.job.entity.AliPayResponseParam
     */
    @Override
    public AliPayResponseParam execute(AliPayRequestParam payRequestParam) {
        checkAliPayCommonParam(payRequestParam);
        return invoke(payRequestParam);
    }
}
