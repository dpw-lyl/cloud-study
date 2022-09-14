package com.dpw.lyl.join.good.job.foundation.base;

import lombok.NonNull;

/**
 * @Author: dengpw
 * @createTime: 2022年09月08 17:52:15
 * @version: 1.0.0
 * @Description: 外部接口基础接口
 */
public interface BaseHandler<T extends BaseParam,B extends BaseResult> {


    @NonNull
    String handlerName();


    B execute(T param);

}
