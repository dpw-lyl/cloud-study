package com.dpw.lyl.join.good.job.foundation.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.dpw.lyl.join.good.job.foundation.netty.constant.NettyCodeEnum;
import com.dpw.lyl.join.good.job.foundation.netty.handler.base.NettyClientBaseHandler;
import com.dpw.lyl.join.good.job.foundation.netty.template.NettyClientTemplate;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
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
public class NettyClientHandler extends NettyClientBaseHandler {

    @Resource
    private NettyClientTemplate nettyClientTemplate;

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
        nettyClientTemplate.saveClientChannel(ctx.channel());
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
        log.info("客户端ChannelId={}"+ctx.channel().remoteAddress() + " 服务器返回的数据 ：{}，",ctx.channel().id().asLongText(),msg);
        //心跳检测逻辑
        JSONObject heartJson = JSONObject.parseObject(msg.toString());
        String nettyCode = heartJson.getString("nettyCode");
        if (NettyCodeEnum.NETTY_SERVER_HEART_MSG_CLOSE.getNettyCode().equals(nettyCode)) {
            //服务端存活正常处理业务
            log.info("接收服务端消息data：{}", msg);
            // TODO: 2022/8/23 业务逻辑
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("returnCode", "000000");
            jsonObject.put("returnMsg", "接收成功");
            ctx.channel().writeAndFlush(jsonObject.toJSONString());
        } else {
          //服务端关闭链接了
            // TODO: 2022/8/24 处理控制 通过反射实现具体逻辑
        }
        // ReferenceCountUtil.retain(msg);
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
        log.info("开始关闭通道，客户端ChannelId={},通道异常原因{}, " ,ctx.channel().id().asLongText(), cause.toString());
        //如果出现异常, 就关闭该通道
        ctx.close();
        nettyClientTemplate.deleteClientChannel(ctx.channel());
        log.info("关闭通道结束，客户端ChannelId={},通道异常原因{}, " ,ctx.channel().id().asLongText(), cause.toString());
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
        nettyClientTemplate.deleteClientChannel(ctx.channel());
    }

}
