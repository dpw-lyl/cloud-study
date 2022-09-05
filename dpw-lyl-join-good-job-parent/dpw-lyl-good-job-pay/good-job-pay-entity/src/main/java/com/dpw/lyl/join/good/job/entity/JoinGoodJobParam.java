package com.dpw.lyl.join.good.job.entity;

import com.dpw.lyl.join.good.job.foundation.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGoodJobParam extends BaseParam {
    private String goodJobType;
}
