package com.dpw.lyl.join.good.job.foundation.base;

import lombok.NonNull;

public interface BaseHandler<T extends BaseParam ,B extends BaseResult > {


    @NonNull
    String handlerName();


    B execute(T param);

}
