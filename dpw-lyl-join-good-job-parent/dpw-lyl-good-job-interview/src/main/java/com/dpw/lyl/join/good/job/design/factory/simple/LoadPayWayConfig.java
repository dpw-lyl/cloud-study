package com.dpw.lyl.join.good.job.design.factory.simple;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @description: 加载支付配置
 * @author: dengpw$
 * @create: 2023-09-04 17:35
 * @version: 1.0.0$
 **/
@Data
@Configuration
@ConfigurationProperties("online-pay")
public class LoadPayWayConfig {
    private Map<String,String> payWays;
}