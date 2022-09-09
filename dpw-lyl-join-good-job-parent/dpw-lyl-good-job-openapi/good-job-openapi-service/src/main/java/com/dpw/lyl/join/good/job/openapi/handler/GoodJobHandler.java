package com.dpw.lyl.join.good.job.openapi.handler;


import com.dpw.lyl.join.good.job.foundation.base.BaseHandler;
import com.dpw.lyl.join.good.job.openapi.entity.JoinGoodJobParam;
import com.dpw.lyl.join.good.job.openapi.entity.JoinGoodJobResult;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * @Author: dengpw
 * @createTime: 2022年09月09 09:25:16
 * @version: 1.0.0
 * @Description: 业务处理器
 */
@Component(value = "goodJob")
public class GoodJobHandler implements BaseHandler<JoinGoodJobParam, JoinGoodJobResult> {
    @Override
    public @NonNull String handlerName() {
        return "goodJob";
    }

    @Override
    public JoinGoodJobResult execute(JoinGoodJobParam param) {
        JoinGoodJobResult joinGoodJobResult = new JoinGoodJobResult();
        joinGoodJobResult.setJob("good job");
        return joinGoodJobResult;
    }

}
