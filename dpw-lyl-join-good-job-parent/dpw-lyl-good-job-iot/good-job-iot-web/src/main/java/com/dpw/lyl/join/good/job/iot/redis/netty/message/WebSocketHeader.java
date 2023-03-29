package com.dpw.lyl.join.good.job.iot.redis.netty.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dengpw
 * @createTime: 2023年03月14日 10:53:13
 * @version: 1.0.0
 * @Description: websocket鉴权实体
 */
@Data
public class WebSocketHeader implements Serializable {

    /**
     * 登录token
     */
    private String token;

    /**
     * userId
     */
    private String userId;
}
