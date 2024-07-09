package com.dpw.lyl.join.good.job.iot.redis.netty.handler;


import com.dpw.lyl.join.good.job.iot.redis.netty.constant.ImNettyEventType;
import com.dpw.lyl.join.good.job.iot.redis.netty.message.WebSocketHeader;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: dengpw
 * @createTime: 2023年03月14日 10:32:45
 * @version: 1.0.0
 * @Description: websocket鉴权处理器
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ImTravelPermissionWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            if (null != request.headers()) {
                // 获取用户token
                String token = request.headers().get("token");
                String userId = request.headers().get("userId");
                AttributeKey<WebSocketHeader> key = AttributeKey.valueOf("permission");
                WebSocketHeader webSocketPermissionVerify = ctx.channel().attr(key).get();
                if (null == webSocketPermissionVerify) {
                    webSocketPermissionVerify = new WebSocketHeader();
                    webSocketPermissionVerify.setToken(token);
                    webSocketPermissionVerify.setUserId(userId);
                }
                ctx.channel().attr(key).setIfAbsent(webSocketPermissionVerify);
                request.setUri(ImNettyEventType.WEBSOCKET_PATH);
                ctx.fireChannelRead(request.retain());
            }
        }
        ctx.fireChannelRead(msg);
    }

    /**
     * 工程出现异常的时候调用: <br>
     *
     * @param ctx   处理上下文
     * @param cause 异常
     * @throws Exception <br>
     * @author yangxiaodong<br>
     * @taskId <br>
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("websocket exception", cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        ctx.fireChannelRead(msg);
    }

}
