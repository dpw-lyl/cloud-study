package com.dpw.lyl.join.good.job.foundation.log.service;

import com.dpw.lyl.join.good.job.foundation.common.api.RemoteLogService;
import com.dpw.lyl.join.good.job.foundation.constant.SecurityConstants;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 *
 * @author ruoyi
 */
@Service
public class AsyncLogService {
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperationLog sysOperationLog) {
        remoteLogService.saveLog(sysOperationLog, SecurityConstants.INNER);
    }
}
