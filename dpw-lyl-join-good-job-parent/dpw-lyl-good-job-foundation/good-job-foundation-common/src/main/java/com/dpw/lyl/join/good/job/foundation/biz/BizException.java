package com.dpw.lyl.join.good.job.foundation.biz;

import com.dpw.lyl.join.good.job.foundation.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: dengpw
 * @createTime: 2022年08月25日 10:46:25
 * @version: 1.0.0
 * @Description: 全局业务异常处理器
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BizException extends RuntimeException {
    /**
     * 异常码值
     */
    private String errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 12:02:05
     * @description: 默认返回系统异常
     * @param:
     * @return: null
     */
    public BizException(){
        this(ResponseCodeEnum.SYSTEM_ERROR);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 12:02:29
     * @description: 根据状态码值枚举抛出业务异常
     * @param: responseCodeEnum - [ResponseCodeEnum]
     * @return: null
     */
    public BizException (ResponseCodeEnum responseCodeEnum){
      this(responseCodeEnum.getReturnCode(),responseCodeEnum.getReturnMsg());
    }
}
