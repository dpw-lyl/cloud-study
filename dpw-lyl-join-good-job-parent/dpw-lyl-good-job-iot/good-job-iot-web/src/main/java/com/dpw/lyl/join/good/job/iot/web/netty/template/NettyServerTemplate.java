package com.dpw.lyl.join.good.job.iot.web.netty.template;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 11:34:23
 * @version: 1.0.0
 * @Description: Netty服务端上下文模板处理器
 */
@Slf4j
@Component
public class NettyServerTemplate {
    /**
     * 全局存储netty上下文容器（分布式系统用zk或者redis）
     */
    public static Map<String, Channel> serverChannelMap = new ConcurrentHashMap<>(16);

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:02:42
     * @description: 根据channelId作为key进行存储
     * @param:
     * @return: void
     */
    public void saveServerChannel(Channel channel){
        serverChannelMap.put(channel.id().asLongText(),channel);
    };

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:05:17
     * @description: 根据channelId获取容器中的channel实例
     * @param: channelId - [String]
     * @return: java.lang.Object
     */
    public Object getServerChannelByChannelId(String channelId){
        return serverChannelMap.get(channelId);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:09:13
     * @description: 删除容器中的channel实例
     * @param: channel - [Channel]
     * @return: void
     */
    public void deleteServerChannel(Channel channel){
        serverChannelMap.remove(channel.id().asLongText());
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:11:07
     * @description: 返回在线用户列表信息
     * @param:
     * @return: java.util.List<java.lang.String>
     */
    public List<String> getOnlineList(){
        return new ArrayList<>(serverChannelMap.keySet());
    };

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:12:35
     * @description: 返回在线数
     * @param:
     * @return: int
     */
    public int serverChannelSize(){
        return serverChannelMap.size();
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 12:13:36
     * @description: 指定channel发送信息
     * @param:
     * @return: java.lang.String
     */
    public static String sendMsgByChannel(String msg,Channel channel){
        String returnMsg;
        try{
            if(channel.isActive()){
                channel.writeAndFlush(msg);
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
