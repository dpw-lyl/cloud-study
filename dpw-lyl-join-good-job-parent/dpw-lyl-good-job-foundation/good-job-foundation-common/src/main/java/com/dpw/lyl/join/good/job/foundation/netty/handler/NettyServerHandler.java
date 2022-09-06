package com.dpw.lyl.join.good.job.foundation.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.dpw.lyl.join.good.job.foundation.netty.constant.NettyCodeEnum;
import com.dpw.lyl.join.good.job.foundation.netty.handler.base.NettyServerBaseHandler;
import com.dpw.lyl.join.good.job.foundation.netty.template.NettyServerTemplate;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 09:46:47
 * @version: 1.0.0
 * @Description: netty服务端业务网处理器
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends NettyServerBaseHandler {
    @Resource
    private NettyServerTemplate nettyServerTemplate;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("netty服务端[" + ctx.channel().id().asLongText() + "]建立链接成功");
        // 把channel上线问放进全局模板中
        nettyServerTemplate.saveServerChannel(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("当前线程名:{},当前ChannelId={}，netty服务端接收到消息：{}，",Thread.currentThread().getName(), ctx.channel().id().asLongText(), msg);
        //心跳检测逻辑
        JSONObject heartJson = JSONObject.parseObject((String) msg);
        String nettyCode = heartJson.getString("nettyCode");
        if (NettyCodeEnum.NETTY_CLIENT_HEART_MSG.getNettyCode().equals(nettyCode)) {
            // TODO: 2022/8/23 回复客户端心跳检测
            log.info("客户端发送心跳检测数据：{}", msg);
            JSONObject heartMsgJson = new JSONObject();
            heartMsgJson.put("nettyCode", NettyCodeEnum.NETTY_SERVER_HEART_MSG.getNettyCode());
            heartMsgJson.put("nettyMsg", NettyCodeEnum.NETTY_SERVER_HEART_MSG.getNettyMsg());
            ctx.channel().writeAndFlush(heartMsgJson.toJSONString());
        } else {
            // TODO: 2022/8/23 业务逻辑 反射实现具体逻辑
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("returnCode", "000000");
            jsonObject.put("returnMsg", "接收成功");
            ctx.channel().writeAndFlush(jsonObject.toJSONString());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("当前ChannelId={}，服务端发生异常：{}---",ctx.channel().id().asLongText(), cause.toString());
        ctx.close();
        //  清空异常的Channel
        nettyServerTemplate.deleteServerChannel(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("链接已关闭，ChanelId={}", ctx.channel().id().asLongText());
        // 删除全局上下文（最好使用redis）（目前本地考虑并发使用ConcurrentHashMap）
        //  清空异常的Channel
        nettyServerTemplate.deleteServerChannel(ctx.channel());
    }
}
