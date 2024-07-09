package com.dpw.lyl.join.good.job.foundation.base;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class BaseResult<T> {
    private String errorCode;
    private String errorMsg;
    private T data;
}
