package com.dpw.lyl.join.good.job.pay.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dengpw
 * @createTime: 2022年08月23日 09:50:03
 * @version: 1.0.0
 * @Description: Netty序列化结果
 */
@Data
public class NettyResult implements Serializable {

    private String cmd;

    private String timestamp;

    private String  errcode;

    private String errmsg;


}
