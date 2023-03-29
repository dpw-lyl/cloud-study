package com.dpw.lyl.join.good.job.iot.redis.netty.message;

import lombok.Data;

/**
 * @Author:
 * @createTime: 2022年08月25日 17:57:26
 * @version:
 * @Description:
 */
@Data
public class OtaCarRequestBody {

    /**
     * 平台打车订单号
     */
    private String orderNo;

    /**
     * 业务类型
     */
    private String bizType;
}
