package com.dpw.lyl.join.good.job.foundation.common.api.factory;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.common.api.RemoteLogService;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysLoginInfo;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysOperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 日志服务降级处理
 *
 * @author ruoyi
 */
@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {

    @Override
    public RemoteLogService create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService() {
            @Override
            public MsgResponse<Boolean> saveLog(SysOperationLog sysOperationLog, String source) {
                return null;
            }

            @Override
            public MsgResponse<Boolean> saveSysLoginInfo(SysLoginInfo sysLoginInfo, String source) {
                return null;
            }
        };

    }
}
