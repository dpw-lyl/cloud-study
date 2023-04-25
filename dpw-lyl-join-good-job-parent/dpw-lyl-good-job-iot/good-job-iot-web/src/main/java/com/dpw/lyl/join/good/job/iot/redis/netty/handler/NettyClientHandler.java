package com.dpw.lyl.join.good.job.iot.redis.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.dpw.lyl.join.good.job.foundation.redis.service.RedisService;
import com.dpw.lyl.join.good.job.iot.redis.netty.constant.NettyCodeEnum;
import com.dpw.lyl.join.good.job.iot.redis.netty.handler.base.NettyClientBaseHandler;
import com.dpw.lyl.join.good.job.iot.redis.netty.pool.ChannelHandlerPool;
import com.dpw.lyl.join.good.job.iot.redis.netty.service.CommMessageService;
import com.dpw.lyl.join.good.job.iot.redis.netty.template.NettyClientTemplate;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: dengpw
 * @createTime: 2022年08月19日 10:02:52
 * @version: 1.0.0
 * @Description: netty客户端处理器
 */
@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
@Order(1)
public class NettyClientHandler extends NettyClientBaseHandler {

    @Resource
    private NettyClientTemplate nettyClientTemplate;

    private final CommMessageService commMessageService;

    private final RedisService redisService;



    /**
     * @author: dengpw
     * @createTime: 2022年08月19 10:10:40
     * @description: 通道就绪后触发该方法
     * @param: ctx - [ChannelHandlerContext]
     * @return: void
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("客戶端与服务端链接成功，客户端ChannelId={}，当前线程：{}", ctx.channel().id(), Thread.currentThread());
        ChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月19 10:10:56
     * @description: 读取数据 : 在服务器端读取客户端发送的数据
     * @param: ctx - [ChannelHandlerContext]
     * 通道处理者上下文对象 : 封装了 管道 ( Pipeline ) , 通道 ( Channel ), 客户端地址信息l
     * 管道 ( Pipeline ) : 注重业务逻辑处理 , 可以关联很多 Handler
     * 通道 ( Channel ) : 注重数据读写
     * @param: msg - [Object] 服务器返回的数据
     * @return: void
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端ChannelId={}" + ctx.channel().remoteAddress() + " 服务器返回的数据 ：{}，", ctx.channel().id().asLongText(), msg);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", NettyCodeEnum.NETTY_CLIENT_HEART_MSG_CLOSE.getNettyCode());
        jsonObject.put("data", NettyCodeEnum.NETTY_CLIENT_HEART_MSG_CLOSE.getNettyMsg());
        ctx.channel().writeAndFlush(new TextWebSocketFrame(jsonObject.toJSONString()));
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月19 10:11:58
     * @description: 异常处理 , 上面的方法中都抛出了 Exception 异常, 在该方法中进行异常处理
     * @param: ctx - [ChannelHandlerContext]
     * @param: cause - [Throwable] 异常
     * @return: void
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("开始关闭通道，客户端ChannelId={},通道异常原因{}, ", ctx.channel().id().asLongText(), cause.toString());
        //如果出现异常, 就关闭该通道
        ctx.close();
        ChannelHandlerPool.channelGroup.remove(ctx.channel());
        nettyClientTemplate.deleteServerChannel(redisService.getCacheObject(ctx.channel().id().asLongText()));
        redisService.deleteObject(ctx.channel().id().asLongText());
        log.info("关闭通道结束，客户端ChannelId={},通道异常原因{}, ", ctx.channel().id().asLongText(), cause.toString());
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 14:07:09
     * @description: 删除容器中的Channel
     * @param: ctx - [ChannelHandlerContext]
     * @return: void
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        log.info("链接已关闭，ChanelId={}", ctx.channel().id().asLongText());
        // 删除全局上下文
        //  清空异常的Channel
        ChannelHandlerPool.channelGroup.remove(ctx.channel());
        Object cacheObject = redisService.getCacheObject(ctx.channel().id().asLongText());
        if (cacheObject instanceof String) {
            nettyClientTemplate.deleteServerChannel((String) cacheObject);
        }
        redisService.deleteObject(ctx.channel().id().asLongText());
    }

}
