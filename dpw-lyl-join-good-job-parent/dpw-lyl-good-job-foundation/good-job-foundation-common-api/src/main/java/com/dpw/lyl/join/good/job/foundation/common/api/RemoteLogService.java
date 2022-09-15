package com.dpw.lyl.join.good.job.foundation.common.api;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.common.api.factory.RemoteLogFallbackFactory;
import com.dpw.lyl.join.good.job.foundation.constant.SecurityConstants;
import com.dpw.lyl.join.good.job.foundation.constant.ServiceNameConstants;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysLoginInfo;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysOperationLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 日志服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    /**
     * 保存系统日志
     *
     * @param sysOperationLog 日志实体
     * @param source          请求来源
     * @return 结果
     */
    @PostMapping("/sysOperationLog")
    MsgResponse<Boolean> saveLog(@RequestBody SysOperationLog sysOperationLog, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 保存访问记录
     *
     * @param sysLoginInfo 访问实体
     * @param source       请求来源
     * @return 结果
     */
    @PostMapping("/sysLoginInfo")
    MsgResponse<Boolean> saveSysLoginInfo(@RequestBody SysLoginInfo sysLoginInfo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
