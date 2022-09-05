package com.dpw.lyl.join.good.job.pay.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: dengpw
 * @createTime: 2022年08月17日 16:39:44
 * @version: 1.0.0
 * @Description: 支付宝支付接口公共参数配置
 */
@Data
@Component
@PropertySource("classpath:/alipay.properties")
@ConfigurationProperties(prefix = "ali.pay")
public class AliCommon {

    /**
     * 应用编号
     */
    @Value("${ali.pay.appId:1212121}")
    private String appId;

    /**
     * 应用私钥
     */
    @Value("${ali.pay.privateKey:1212212}")
    private String privateKey;

    /**
     * 支付宝公钥，通过应用公钥上传到支付宝开放平台换取支付宝公钥(如果是证书模式，公钥与私钥在CSR目录)。
     */
    @Value("${ali.pay.publicKey:11111}")
    private String publicKey;

    /**
     * 应用公钥证书 (证书模式必须)
     */
    @Value("${ali.pay.appCertPath:1111}")
    private String appCertPath;

    /**
     * 支付宝公钥证书 (证书模式必须)
     */
    @Value("${ali.pay.aliPayCertPath:11111}")
    private String aliPayCertPath;

    /**
     * 支付宝根证书 (证书模式必须)
     */
    @Value("${ali.pay.aliPayRootCertPath:1111}")
    private String aliPayRootCertPath;

    /**
     * 支付宝支付网关
     */
    @Value("${ali.pay.serverUrl:1111}")
    private String serverUrl;

    /**
     * 外网访问项目的域名，支付通知中会使用
     */
    @Value("${ali.pay.domain:1111}")
    private String domain;
}
