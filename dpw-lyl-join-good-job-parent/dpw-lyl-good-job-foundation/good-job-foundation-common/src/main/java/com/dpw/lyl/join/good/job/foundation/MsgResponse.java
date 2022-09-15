package com.dpw.lyl.join.good.job.foundation;

import com.dpw.lyl.join.good.job.foundation.biz.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dengpw
 * @createTime: 2022年08月25日 10:51:14
 * @version: 1.0.0
 * @Description: 全局返回处理器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgResponse<T> {
    /**
     * 状态码
     */
    private String returnCode;
    /**
     * 码值信息
     */
    private String returnMsg;

    /**
     * 返回数据
     */
    private T data;


    /**
     * @author: dengpw
     * @createTime: 2022年08月25 11:23:22
     * @description: 不需要data，返回成功
     * @param:
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> buildSuccess() {
        return buildSuccess(ResponseCodeEnum.SYSTEM_SUCCESS);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 11:23:58
     * @description: 返回有data数据成功
     * @param: data - [T]
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> buildSuccess(T data) {
        return buildSuccess(ResponseCodeEnum.SYSTEM_SUCCESS,data);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 11:24:31
     * @description: 返回data，以及码值和信息
     * @param: data - [T]
     * @param: responseCodeEnum - [ResponseCodeEnum]
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> buildSuccess(ResponseCodeEnum responseCodeEnum,T data) {
        return build(responseCodeEnum.getReturnCode(), responseCodeEnum.getReturnMsg(), data);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 11:25:12
     * @description: 返回状态码值
     * @param: responseCodeEnum - [ResponseCodeEnum]
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> buildSuccess(ResponseCodeEnum responseCodeEnum) {
        return buildSuccess(responseCodeEnum,null);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 11:35:47
     * @description: 发生异常时构建返回值
     * @param: bizException - [BizException]
     * @param: data - [T]
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> buildBizException(BizException bizException, T data) {
        return build(bizException.getErrorCode(), bizException.getErrorMsg(), data);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 11:43:58
     * @description: 根据业务异常构建返回值
     * @param: bizException - [BizException]
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> buildBizExceptionNoData(BizException bizException) {
        return build(bizException.getErrorCode(), bizException.getErrorMsg(), null);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 11:41:15
     * @description: 构建返回值
     * @param: returnCode - [String]
     * @param: returnMsg - [String]
     * @param: data - [T]
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> build(String returnCode, String returnMsg, T data) {
        MsgResponse<T> msgResponse = new MsgResponse<>();
        msgResponse.setReturnCode(returnCode);
        msgResponse.setReturnMsg(returnMsg);
        msgResponse.setData(data);
        return msgResponse;
    }

    /**
     * @author: dengpw
     * @createTime: 2022年09月15 17:37:09
     * @description: 组装错误信息
     * @param:
     * @return: com.dpw.lyl.join.good.job.foundation.MsgResponse<T>
     */
    public static <T> MsgResponse<T> buildFail(String msg){
        MsgResponse<T> msgResponse = new MsgResponse<>();
        msgResponse.setReturnCode(ResponseCodeEnum.SYSTEM_ERROR.getReturnCode());
        msgResponse.setReturnMsg(msg);
        msgResponse.setData(null);
        return msgResponse;
    }

}
