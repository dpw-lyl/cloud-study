package com.dpw.lyl.join.good.job.iot.redis.netty.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.octv.cloud.tour.travel.config.NettyHeartBeat;
import com.octv.cloud.tour.travel.netty.constant.NettyEventType;
import com.octv.cloud.tour.travel.netty.handler.base.NettyServerBaseHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
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

    @Resource
    private TravelPermissionWebSocketHandler travelPermissionWebSocketHandler;

    @Resource
    private NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new HttpServerCodec());
        ch.pipeline().addLast(new ObjectEncoder());
        // 分块写入, 支持异步写大型数据流，而又不会导致大量的内存消耗
        ch.pipeline().addLast(new ChunkedWriteHandler());
        //将多段信息聚合起来, 最大支持1024*1024
        ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
        // WebSocket 数据压缩 ,压缩来自 client
        ch.pipeline().addLast(new WebSocketServerCompressionHandler());
        //鉴权登录信息
        ch.pipeline().addLast(travelPermissionWebSocketHandler);
        ch.pipeline().addLast(new WebSocketServerProtocolHandler(NettyEventType.WEBSOCKET_PATH));
        ch.pipeline().addLast(nettyServerHandler);
        ch.pipeline().addLast(new IdleStateHandler(nettyHeartBeat.getServerReaderIdleTime(),nettyHeartBeat.getServerWriterIdleTime(),nettyHeartBeat.getServerAllIdleTime(), TimeUnit.SECONDS));
        if (!CollectionUtil.isEmpty(channelHandlers)){
            channelHandlers.forEach( channelHandler -> ch.pipeline().addLast(channelHandler));
        }
    }
}
