package com.dpw.lyl.join.good.job.sso.auth.service;

import com.dpw.lyl.join.good.job.foundation.common.api.RemoteLogService;
import com.dpw.lyl.join.good.job.foundation.constant.Constants;
import com.dpw.lyl.join.good.job.foundation.constant.SecurityConstants;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysLoginInfo;
import com.dpw.lyl.join.good.job.foundation.utils.ServletUtils;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import com.dpw.lyl.join.good.job.foundation.utils.ip.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 记录日志方法
 *
 * @author ruoyi
 */
@Component
public class SysRecordLogService {
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     * @return
     */
    public void recordSysLoginInfo(String username, String status, String message) {
        SysLoginInfo sysLoginInfo = new SysLoginInfo();
        sysLoginInfo.setUserName(username);
        sysLoginInfo.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysLoginInfo.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            sysLoginInfo.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            sysLoginInfo.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveSysLoginInfo(sysLoginInfo, SecurityConstants.INNER);
    }
}
