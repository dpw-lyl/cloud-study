package com.dpw.lyl.join.good.job.iot.redis.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.redis.service.RedisService;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import com.dpw.lyl.join.good.job.iot.redis.netty.handler.base.NettyServerBaseHandler;
import com.dpw.lyl.join.good.job.iot.redis.netty.message.WebSocketHeader;
import com.dpw.lyl.join.good.job.iot.redis.netty.pool.ChannelHandlerPool;
import com.dpw.lyl.join.good.job.iot.redis.netty.service.CommMessageService;
import com.dpw.lyl.join.good.job.iot.redis.netty.template.ImNettyServerTemplate;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 09:46:47
 * @version: 1.0.0
 * @Description: netty服务端业务网处理器
 */
@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
@Order(1)
public class ImNettyServerHandler extends NettyServerBaseHandler {

    private final ImNettyServerTemplate nettyServerTemplate;

    private final CommMessageService commMessageService;

    private final RedisService redisService;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("netty服务端[" + ctx.channel().id().asLongText() + "]建立链接成功");
        ChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("当前线程名:{},当前ChannelId={}，netty服务端接收到消息：{}，", Thread.currentThread().getName(), ctx.channel().id().asLongText(), msg);
        //心跳检测逻辑
        if (msg instanceof FullHttpRequest) {
            AttributeKey<WebSocketHeader> key = AttributeKey.valueOf("permission");
            WebSocketHeader webSocketPermissionVerify = ctx.channel().attr(key).get();
            String token = webSocketPermissionVerify.getToken();
            String userId = webSocketPermissionVerify.getUserId();
            if(StringUtils.isEmpty(token)){
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(MsgResponse.buildFail("token为空"))));
            }
            // TODO: 2023/4/25
            //第一次连接鉴权

        }else if(msg instanceof TextWebSocketFrame) {
            // 业务处理
            AttributeKey<WebSocketHeader> key = AttributeKey.valueOf("permission");
            WebSocketHeader webSocketPermissionVerify = ctx.channel().attr(key).get();
            String token = webSocketPermissionVerify.getToken();
            String userId = webSocketPermissionVerify.getUserId();
            //校验登录信息
            nettyServerTemplate.saveServerChannel(userId,ctx.channel());
            redisService.setCacheObject(ctx.channel().id().asLongText(),userId);
            log.info("客户端发送数据：{}", msg);
            TextWebSocketFrame data= (TextWebSocketFrame)msg;
            String text = data.text();
            JSONObject req = JSONObject.parseObject(text);
           // SpringUtils.getBean(req.getString("eventType"));
            // TODO: 2023/3/14 前端主动抓取数据
            log.info("接收数据：{}",req);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(MsgResponse.buildSuccess(null))));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("当前ChannelId={}，服务端发生异常：{}---", ctx.channel().id().asLongText(), cause.toString());
        ctx.close();
        //  清空异常的Channel
        ChannelHandlerPool.channelGroup.remove(ctx.channel());
        nettyServerTemplate.deleteServerChannel(redisService.getCacheObject(ctx.channel().id().asLongText()));
        redisService.deleteObject(ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("链接已关闭，ChanelId={}", ctx.channel().id().asLongText());
        // 删除全局上下文
        //  清空异常的Channel
        ChannelHandlerPool.channelGroup.remove(ctx.channel());
        Object cacheObject = redisService.getCacheObject(ctx.channel().id().asLongText());
        if(cacheObject instanceof String){
            nettyServerTemplate.deleteServerChannel((String) cacheObject);
        }
        redisService.deleteObject(ctx.channel().id().asLongText());
    }
}
