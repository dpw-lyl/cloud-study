package com.dpw.lyl.join.good.job.iot.web.netty.handler.constant;

/**
 * @Author: dengpw
 * @createTime: 2022年08月24日 16:43:11
 * @version: 1.0.0
 * @Description: Netty心跳检测事件
 */
public interface NettyEventType {
    /**
     * 读空闲
     */
    String READER_IDLE = "READER_IDLE";
    /**
     * 读写空闲
     */
    String ALL_IDLE = "ALL_IDLE";
    /**
     * 写空闲
     */
    String WRITER_IDLE = "WRITER_IDLE";
}
