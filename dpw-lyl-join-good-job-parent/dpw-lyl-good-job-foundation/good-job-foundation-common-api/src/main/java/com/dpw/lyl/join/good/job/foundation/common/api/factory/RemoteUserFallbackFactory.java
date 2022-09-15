package com.dpw.lyl.join.good.job.foundation.common.api.factory;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.ResponseCodeEnum;
import com.dpw.lyl.join.good.job.foundation.biz.BizException;
import com.dpw.lyl.join.good.job.foundation.common.api.RemoteUserService;
import com.dpw.lyl.join.good.job.foundation.domain.login.LoginUser;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author ruoyi
 */
@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {


    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public MsgResponse<LoginUser> getUserInfo(String username, String source) {
                return MsgResponse.buildBizExceptionNoData(new BizException(ResponseCodeEnum.SYSTEM_ERROR.getReturnCode(),"获取用户失败:" + throwable.getMessage()));
            }

            @Override
            public MsgResponse<Boolean> registerUserInfo(SysUser sysUser, String source) {
                return MsgResponse.buildBizExceptionNoData(new BizException(ResponseCodeEnum.SYSTEM_ERROR.getReturnCode(),"注册用户失败:" + throwable.getMessage()));
            }
        };
    }
}
