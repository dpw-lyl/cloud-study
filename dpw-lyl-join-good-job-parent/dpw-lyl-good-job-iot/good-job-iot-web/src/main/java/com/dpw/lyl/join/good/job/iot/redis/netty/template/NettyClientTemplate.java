package com.dpw.lyl.join.good.job.iot.redis.netty.template;

import com.dpw.lyl.join.good.job.foundation.redis.service.RedisService;
import com.dpw.lyl.join.good.job.iot.redis.netty.pool.ChannelHandlerPool;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 11:33:14
 * @version: 1.0.0
 * @Description: Netty客户端全局上下文模板处理器
 */
@Slf4j
@Component
public class NettyClientTemplate {
    /**
     * 分布式系统redis
     */
    @Autowired
    private RedisService redisService;


    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:02:42
     * @description: 存储通道ID
     * @param:
     * @return: void
     */
    public void saveServerChannel(String userId,Channel channel){
        redisService.setCacheMapValue(ChannelHandlerPool.channelUserKey,userId,channel.id());
    };

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:05:17
     * @description: 根据channelId获取容器中的channel实例
     * @param: channelId - [String]
     * @return: java.lang.Object
     */
    public Object getServerChannelByChannelId(String userId){
        return redisService.getCacheMapValue(ChannelHandlerPool.channelUserKey,userId);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:09:13
     * @description: 删除容器中的channel实例
     * @param: channel - [Channel]
     * @return: void
     */
    public void deleteServerChannel(String userId){
        redisService.deleteCacheMapValue(ChannelHandlerPool.channelUserKey,userId);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:11:07
     * @description: 返回在线用户列表信息
     * @param:
     * @return: java.util.List<java.lang.String>
     */
    public List<String> getOnlineList(){
        return new ArrayList<>(Objects.requireNonNull(redisService.getCacheMap(ChannelHandlerPool.channelUserKey).keySet()));
    };

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:12:35
     * @description: 返回在线数
     * @param:
     * @return: int
     */
    public int serverChannelSize(){
        return Objects.requireNonNull(redisService.getCacheMap(ChannelHandlerPool.channelUserKey).keySet()).size();
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:13:36
     * @description: 指定channel发送信息
     * @param:
     * @return: java.lang.String
     */
    public String sendMsgByChannel(String msg, String userId){
        String returnMsg;
        try{
            ChannelId serverChannelByChannelId = (ChannelId)this.getServerChannelByChannelId(userId);
            Channel channel = ChannelHandlerPool.channelGroup.find(serverChannelByChannelId);
            if(channel.isActive()){
                channel.writeAndFlush(new TextWebSocketFrame(msg));
                returnMsg="success";
            }else {
                returnMsg="连接关闭";
            }
        }catch (Exception exception){
            log.error("netty服务端发送异常:{}",exception.getMessage());
            returnMsg="netty服务端发送异常";
        }
        return returnMsg;
    }
}
