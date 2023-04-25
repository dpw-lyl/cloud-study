package com.dpw.lyl.join.good.job.iot.redis.netty.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.dpw.lyl.join.good.job.iot.redis.netty.handler.base.NettyClientBaseHandler;
import com.dpw.lyl.join.good.job.iot.web.config.NettyHeartBeat;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: dengpw
 * @createTime: 2022年08月19日 14:19:34
 * @version: 1.0.0
 * @Description: 绑定netty--handler
 */
@Slf4j
@Component
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private List<NettyClientBaseHandler> channelHandlers;

    @Resource
    private List<NettyClientHeartBeatHandler> heartBeatHandlers;

    @Resource
    private NettyHeartBeat nettyHeartBeat;

    /**
     * @author: dengpw
     * @createTime: 2022年08月19 14:22:01
     * @description:
     * @param: ch - [Channel]
     * @return: void
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //加入自定义包处理器
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast("decoder", new ByteArrayDecoder());
        ch.pipeline().addLast("encoder", new ByteArrayEncoder());
        if (!CollectionUtil.isEmpty(channelHandlers)) {
            channelHandlers.forEach(channelHandler -> ch.pipeline().addLast(channelHandler));
        }
        ch.pipeline().addLast(new IdleStateHandler(nettyHeartBeat.getClientReaderIdleTime(), nettyHeartBeat.getClientWriterIdleTime(), nettyHeartBeat.getClientAllIdleTime(), TimeUnit.SECONDS));
        if (!CollectionUtil.isEmpty(heartBeatHandlers)) {
            heartBeatHandlers.forEach(channelHandler -> ch.pipeline().addLast(channelHandler));
        }
    }
}
