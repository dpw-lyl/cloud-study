package com.dpw.lyl.join.good.job.foundation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: dengpw
 * @createTime: 2022年08月25日 10:54:41
 * @version: 1.0.0
 * @Description: 全局码值枚举类
 */
@AllArgsConstructor
@Getter
public enum ResponseCodeEnum {

    SYSTEM_ERROR("999999", "系统错误"),
    SYSTEM_SUCCESS("000000", "请求成功");;

    /**
     * 定义错误码值
     */
    private final String returnCode;

    /**
     * 定义错误信息
     */
    private final String returnMsg;

}
