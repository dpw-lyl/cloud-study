package com.dpw.lyl.join.good.job.foundation.netty.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.dpw.lyl.join.good.job.foundation.config.NettyHeartBeat;
import com.dpw.lyl.join.good.job.foundation.netty.handler.base.NettyServerBaseHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
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
 * @createTime: 2022年08月22日 09:45:16
 * @version: 1.0.0
 * @Description: netty服务端Channel初始化器
 */
@Slf4j
@Component
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private List<NettyServerBaseHandler> channelHandlers;

    @Resource
    private NettyHeartBeat nettyHeartBeat;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
       // ch.pipeline().addLast(new MessagePackDecoder());
        //ch.pipeline().addLast(new MessagePackEncoder());
        ch.pipeline().addLast("decoder", new StringDecoder());
        ch.pipeline().addLast("encoder", new StringEncoder());
        ch.pipeline().addLast(new IdleStateHandler(nettyHeartBeat.getServerReaderIdleTime(),nettyHeartBeat.getServerWriterIdleTime(),nettyHeartBeat.getServerAllIdleTime(), TimeUnit.SECONDS));
        if (!CollectionUtil.isEmpty(channelHandlers)){
            channelHandlers.forEach( channelHandler -> ch.pipeline().addLast(channelHandler));
        }
    }
}
