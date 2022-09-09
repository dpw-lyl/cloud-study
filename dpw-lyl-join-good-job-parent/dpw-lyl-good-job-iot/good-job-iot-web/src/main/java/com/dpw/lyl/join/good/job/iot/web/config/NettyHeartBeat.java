package com.dpw.lyl.join.good.job.iot.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: dengpw
 * @createTime: 2022年08月24日 14:30:47
 * @version: 1.0.0
 * @Description: Netty相关参数配置
 */
@Data
@Component
@RefreshScope
public class NettyHeartBeat {
    /**
     *服务端读事件心跳检测时间
     */
    @Value("${netty.server.heartbeat.serverReaderIdleTime:60}")
    private Long serverReaderIdleTime;

    /**
     * 服务端写时间心跳检测时间
     */
    @Value("${netty.server.heartbeat.serverWriterIdleTime:0}")
    private Long serverWriterIdleTime;

    /**
     * 服务端读写心跳检测时间
     */
    @Value("${netty.server.heartbeat.serverAllIdleTime:0}")
    private Long serverAllIdleTime;

    /**
     *客户端读事件心跳检测时间
     */
    @Value("${netty.client.heartbeat.clientReaderIdleTime:0}")
    private Long clientReaderIdleTime;

    /**
     * 客户端写时间心跳检测时间
     */
    @Value("${netty.client.heartbeat.clientWriterIdleTime:60}")
    private Long clientWriterIdleTime;

    /**
     *客户端读写心跳检测时间
     */
    @Value("${netty.client.heartbeat.clientAllIdleTime:0}")
    private Long clientAllIdleTime;

    /**
     * 延迟客户端重连时间间隔
     */
    @Value("${netty.client.heartbeat.clientDelay:1}")
    private Long clientDelay;

    /**
     * 空闲写时间断开阈值
     */
    @Value("${netty.client.heartbeat.clientWriterThreshold:10}")
    private Long clientWriterThreshold;


}
