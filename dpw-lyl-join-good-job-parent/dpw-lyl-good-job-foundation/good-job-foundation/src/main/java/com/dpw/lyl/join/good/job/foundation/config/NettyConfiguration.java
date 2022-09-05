package com.dpw.lyl.join.good.job.foundation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 11:38:34
 * @version: 1.0.0
 * @Description: Netty全局配置
 */
@Configuration
@RefreshScope
public class NettyConfiguration {

    @Value("${netty.server.port:19001}")
    private int nettyServerPort;

    @Value("${netty.client.port:19000}")
    private int nettyClientPort;

    @Value("${netty.server.ip:127.0.0.1}")
    private String nettyServerIp;

    @Value("${netty.client.ip:127.0.0.1}")
    private String nettyClientIp;

    @Bean("serverInetSocketAddress")
    public InetSocketAddress serverInetSocketAddress(){
        return new InetSocketAddress(nettyServerIp,nettyServerPort);
    }

    @Bean("clientInetSocketAddress")
    public InetSocketAddress clientInetSocketAddress(){
        return new InetSocketAddress(nettyClientIp,nettyClientPort);
    }
}
