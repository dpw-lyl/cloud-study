package com.dpw.lyl.join.good.job.iot.web.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.dpw.lyl.join.good.job.iot.web.netty.constant.NettyCodeEnum;
import com.dpw.lyl.join.good.job.iot.web.netty.handler.base.NettyServerBaseHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 16:24:16
 * @version: 1.0.0
 * @Description: Netty服务建立心跳检测机制
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyServerHeartBeatHandler extends NettyServerBaseHandler {

    private static final LongAdder timer = new LongAdder();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String eventType = null;
            JSONObject jsonObject = new JSONObject();
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                    timer.increment();
                    //读空闲发送服务端存活心跳
                    log.info(ctx.channel().remoteAddress() + "--发生超时事件：{},读空闲次数：{}", eventType,timer.intValue());
                    jsonObject.put("nettyCode", NettyCodeEnum.NETTY_SERVER_HEART_MSG.getNettyCode());
                    jsonObject.put("nettyMsg",NettyCodeEnum.NETTY_SERVER_HEART_MSG.getNettyMsg());
                    ctx.channel().writeAndFlush(jsonObject);
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            log.info(ctx.channel().remoteAddress() + "--发生事件--读空闲次数：{}",timer.intValue());
            if (timer.intValue() > 3) {
                //当服务端的读空闲到达阈值，关闭服务。并且发送关闭信息通知客户端服务端已经关闭
                log.info("Netty服务端读空闲次数："+timer.intValue()+"次，关闭链接开始----");
                jsonObject.put("nettyCode", NettyCodeEnum.NETTY_SERVER_HEART_MSG_CLOSE.getNettyCode());
                jsonObject.put("nettyMsg",NettyCodeEnum.NETTY_SERVER_HEART_MSG_CLOSE.getNettyMsg());
                ctx.channel().writeAndFlush(jsonObject.toJSONString());
                ctx.channel().close();
                //关闭链接后，重置读空闲阈值
                timer.reset();
                log.info("Netty服务端读重置空闲为："+timer.intValue()+"次，关闭链接结束----");
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
