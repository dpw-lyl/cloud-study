package com.dpw.lyl.join.good.job.iot.redis.netty.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: dengpw
 * @createTime: 2022年08月23日 09:22:59
 * @version: 1.0.0
 * @Description: Netty相关码值信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum NettyCodeEnum {
    ClENT_DATA_IS_NULL("N00001","客户端数据为空"),
    SERVER_DATA_IS_NULL("N00002","服务端数据为空"),
    NETTY_CLIENT_HEART_MSG("220000","--->客户端发送心跳检测,服务是否存活？"),
    NETTY_SERVER_HEART_MSG("N00004","<---回复客户端发送心跳检测，服务端存活"),
    NETTY_CLIENT_HEART_MSG_CLOSE("N00005","客户端发送心跳检测---->客户端关闭链接"),
    NETTY_SERVER_HEART_MSG_CLOSE("N00006","回复客户端发送心跳检测<----服务端存活");
    private  String nettyCode;
    private  String nettyMsg;

}
