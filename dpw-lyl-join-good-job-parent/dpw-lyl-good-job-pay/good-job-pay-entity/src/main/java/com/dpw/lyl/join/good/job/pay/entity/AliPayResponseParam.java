package com.dpw.lyl.join.good.job.pay.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: dengpw
 * @createTime: 2022年08月16日 16:19:45
 * @version: 1.0.0
 * @Description: 支付宝支付返回参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AliPayResponseParam extends PayChannelResponseParam {
}
