package com.dpw.lyl.join.good.job.foundation.security.manager.factory;

import com.dpw.lyl.join.good.job.foundation.utils.ServletUtils;
import com.dpw.lyl.join.good.job.foundation.utils.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @Author: dengpw
 * @createTime: 2022年09月21 15:50:19
 * @version: 1.0.0
 */
public class AsyncFactory {
    private static final Logger sys_user_log = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args) {
      //  final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                // TODO: 2022/11/7 登录日志逻辑
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final String operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                // TODO: 2022/11/7
            }
        };
    }
}
