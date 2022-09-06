package com.dpw.lyl.join.good.job.foundation.netty.message;

import lombok.*;

/**
 * @Author: dengpw
 * @createTime: 2022年08月25日 17:53:44
 * @version: 1.0.0
 * @Description: 自定义消息类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class NettyMessage<T> {

    /**
     * 请求头
     */
    private NettyHeader header;

    /**
     * 请求数据
     */
    private T body;
}
