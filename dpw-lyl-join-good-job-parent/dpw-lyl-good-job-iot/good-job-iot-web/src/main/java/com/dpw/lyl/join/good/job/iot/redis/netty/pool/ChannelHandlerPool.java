package com.dpw.lyl.join.good.job.iot.redis.netty.pool;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author: dengpw
 * @createTime: 2023年03月14日 15:32:09
 * @version: 1.0.0
 * @Description: 通道池
 */
public class ChannelHandlerPool {

    //channelGroup通道组
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //可以存储userId与ChannelId的映射表
    public static String channelUserKey = "travel:userId:channelId";
}