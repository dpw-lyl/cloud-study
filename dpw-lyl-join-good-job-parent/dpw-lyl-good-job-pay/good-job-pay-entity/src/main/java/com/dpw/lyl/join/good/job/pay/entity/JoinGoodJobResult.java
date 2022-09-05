package com.dpw.lyl.join.good.job.pay.entity;

import com.dpw.lyl.join.good.job.foundation.base.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGoodJobResult extends BaseResult {
    private String job;
}
