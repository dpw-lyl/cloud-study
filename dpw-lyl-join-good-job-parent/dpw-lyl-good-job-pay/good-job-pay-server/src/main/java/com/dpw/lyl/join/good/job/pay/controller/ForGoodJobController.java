package com.dpw.lyl.join.good.job.pay.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.dpw.lyl.join.good.job.pay.constant.PayServiceConstant;
import com.dpw.lyl.join.good.job.pay.constant.PayServiceStrategyConstant;
import com.dpw.lyl.join.good.job.pay.entity.AliPayRequestParam;
import com.dpw.lyl.join.good.job.pay.entity.PayChannelResponseParam;
import com.dpw.lyl.join.good.job.pay.config.AliCommon;
import com.dpw.lyl.join.good.job.foundation.utils.SpringUtils;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import com.dpw.lyl.join.good.job.pay.handler.FunctionBasedReceiverHandler;
import com.dpw.lyl.join.good.job.pay.service.MqMangerService;
import com.dpw.lyl.join.good.job.pay.service.mq.MqCommonManager;
import com.dpw.lyl.join.good.job.pay.strategy.PayStrategy;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


@RestController
@RefreshScope
@RequestMapping("/pay-service")
@AllArgsConstructor
public class ForGoodJobController extends AbstractForGoodJobController {



    private final AliCommon aliCommon;

    private final MqCommonManager mqCommonManager;

    private final MqMangerService mqMangerService;

    private final FunctionBasedReceiverHandler functionBasedReceiverHandler;

    @PostMapping("/aliPay/{method}")
    public PayChannelResponseParam aliPay(@PathVariable("method") String method) {
        AliPayRequestParam aliPayRequestParam = new AliPayRequestParam();
        aliPayRequestParam.setMethod(method);
        aliPayRequestParam.setServiceName(PayServiceConstant.ALI_PAY_SERVICE);
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据-By Javen");
        model.setSubject("App支付测试-By Javen");
        model.setOutTradeNo(StringUtils.getOutTradeNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setPassbackParams("callback params");
        model.setProductCode("QUICK_MSECURITY_PAY");
        aliPayRequestParam.setRequestParam(model);
        aliPayRequestParam.setPayChannelCode("aliPay");
        return (PayChannelResponseParam) SpringUtils.getBean(PayServiceStrategyConstant.ALI_PAY, PayStrategy.class).execute(aliPayRequestParam);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月18 17:05:39
     * @description: 支付宝配置
     * @param:
     * @return: com.ijpay.alipay.AliPayApiConfig
     */
    @Override
    public AliPayApiConfig getApiConfig() throws AlipayApiException {
        AliPayApiConfig aliPayApiConfig;
        try {
            aliPayApiConfig = AliPayApiConfigKit.getApiConfig(aliCommon.getAppId());
        } catch (Exception e) {
            aliPayApiConfig = AliPayApiConfig.builder()
                    .setAppId(aliCommon.getAppId())
                    .setAliPayPublicKey(aliCommon.getPublicKey())
                    .setAppCertPath(aliCommon.getAppCertPath())
                    .setAliPayCertPath(aliCommon.getAliPayCertPath())
                    .setAliPayRootCertPath(aliCommon.getAliPayRootCertPath())
                    .setCharset("UTF-8")
                    .setPrivateKey(aliCommon.getPrivateKey())
                    .setServiceUrl(aliCommon.getServerUrl())
                    .setSignType("RSA2")
                    // 普通公钥方式
                    //.build();
                    // 证书模式
                    .buildByCert();

        }
        return aliPayApiConfig;
    }


    @GetMapping("/test/{method}")
    @SentinelResource(value = "pay-service")
    public PayChannelResponseParam aliPayTest(@PathVariable("method") String method) {

        mqMangerService.sendMqMessage(method,method);
        return new PayChannelResponseParam();
    }

    @GetMapping("/mq/{method}")
    @SentinelResource(value = "pay-service")
    public PayChannelResponseParam mqTest(@PathVariable("method") String method) {

        mqCommonManager.processReceivedMessages(method);
        return new PayChannelResponseParam();
    }




}
