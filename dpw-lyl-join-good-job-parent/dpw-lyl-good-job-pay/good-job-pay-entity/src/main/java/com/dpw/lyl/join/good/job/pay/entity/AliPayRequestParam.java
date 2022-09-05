package com.dpw.lyl.join.good.job.pay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: dengpw
 * @createTime: 2022年08月16日 16:17:01
 * @version: 1.0.0
 * @Description: 支付宝支付入参
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AliPayRequestParam extends PayChannelRequestParam {

}
