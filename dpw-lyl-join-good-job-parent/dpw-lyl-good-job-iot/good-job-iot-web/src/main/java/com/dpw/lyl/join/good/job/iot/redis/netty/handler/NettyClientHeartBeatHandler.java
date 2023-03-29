package com.dpw.lyl.join.good.job.iot.redis.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.octv.cloud.tour.travel.config.NettyHeartBeat;
import com.octv.cloud.tour.travel.netty.client.NettyClient;
import com.octv.cloud.tour.travel.netty.constant.NettyCodeEnum;
import com.octv.cloud.tour.travel.netty.constant.NettyEventType;
import com.octv.cloud.tour.travel.netty.handler.base.NettyClientHeartBeatBaseHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 16:24:16
 * @version: 1.0.0
 * @Description: Netty客户端建立心跳检测机制
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyClientHeartBeatHandler extends NettyClientHeartBeatBaseHandler {

    private static final LongAdder timer = new LongAdder();

    @Resource
    private NettyClient nettyClient;

    @Resource
    private NettyHeartBeat nettyHeartBeat;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String eventType;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                case ALL_IDLE:
                    break;
                case WRITER_IDLE:
                    eventType = NettyEventType.WRITER_IDLE;
                    timer.increment();
                    sendHeartBeat(ctx,eventType);
                    break;
            }

            if (timer.intValue() > nettyHeartBeat.getClientWriterThreshold()) {
                //客户端设置的写空闲大于阈值，关闭链接
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", NettyCodeEnum.NETTY_CLIENT_HEART_MSG.getNettyCode());
                jsonObject.put("data", NettyCodeEnum.NETTY_CLIENT_HEART_MSG_CLOSE.getNettyMsg());
                ctx.channel().writeAndFlush( ctx.channel().writeAndFlush(new TextWebSocketFrame(jsonObject.toJSONString())));
                log.info("客户端ChannelId={},当前Netty客户端写空闲次数：" + timer.intValue() + "次，发送关闭信息，data:{}",ctx.channel().id().asLongText(), jsonObject.toJSONString());
                ctx.channel().close();
                timer.reset();
            }
        }
        super.userEventTriggered(ctx, evt);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Netty客户端断开链接，ChannelId={}", ctx.channel().id().asLongText());
        //自动重连机制
        final EventLoop eventExecutors = ctx.channel().eventLoop();
        eventExecutors.schedule(() -> nettyClient.initClient(), nettyHeartBeat.getClientDelay(), TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月24 16:36:39
     * @description: 发送心跳信息
     * @param: ctx - [ChannelHandlerContext]
     * @return: void
     */
    private void sendHeartBeat(ChannelHandlerContext ctx,String eventType){
        log.info("当前客户端ChannelId={},"+ctx.channel().remoteAddress()+"发生事件：{}，客户端触发写计数器计数={}",ctx.channel().id().asLongText(),eventType,timer.intValue());
        //发送心跳检测服务端是否存活
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", NettyCodeEnum.NETTY_CLIENT_HEART_MSG.getNettyCode());
        jsonObject.put("data", NettyCodeEnum.NETTY_CLIENT_HEART_MSG_CLOSE.getNettyMsg());
        ctx.channel().writeAndFlush( ctx.channel().writeAndFlush(new TextWebSocketFrame(jsonObject.toJSONString())));
    }

}
