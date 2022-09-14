package com.dpw.lyl.join.good.job.foundation.exception.user;

import com.dpw.lyl.join.good.job.foundation.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author ruoyi
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
