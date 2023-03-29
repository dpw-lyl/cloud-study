package com.dpw.lyl.join.good.job.iot.redis.netty.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dengpw
 * @createTime: 2023年03月15日 16:11:00
 * @version: 1.0.0
 * @Description: 基础事件
 */
@Data
public class BaseMessage implements Serializable {

    private static final long serialVersionUID = 381926522621349106L;

    private String eventType;
}
