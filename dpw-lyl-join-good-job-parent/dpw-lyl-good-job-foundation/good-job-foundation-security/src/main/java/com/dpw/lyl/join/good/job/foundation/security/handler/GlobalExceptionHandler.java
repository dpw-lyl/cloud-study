package com.dpw.lyl.join.good.job.foundation.security.handler;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.biz.BizException;
import com.dpw.lyl.join.good.job.foundation.constant.HttpStatus;
import com.dpw.lyl.join.good.job.foundation.exception.DemoModeException;
import com.dpw.lyl.join.good.job.foundation.exception.InnerAuthException;
import com.dpw.lyl.join.good.job.foundation.exception.ServiceException;
import com.dpw.lyl.join.good.job.foundation.exception.auth.NotPermissionException;
import com.dpw.lyl.join.good.job.foundation.exception.auth.NotRoleException;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 权限码异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public MsgResponse handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return MsgResponse.buildBizExceptionNoData(new BizException(HttpStatus.FORBIDDEN + "", "没有访问权限，请联系管理员授权"));
    }

    /**
     * 角色权限异常
     */
    @ExceptionHandler(NotRoleException.class)
    public MsgResponse handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',角色权限校验失败'{}'", requestURI, e.getMessage());
        return MsgResponse.buildBizExceptionNoData(new BizException(HttpStatus.FORBIDDEN + "", "没有访问权限，请联系管理员授权"));
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public MsgResponse handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                           HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return MsgResponse.buildBizExceptionNoData(new BizException(e.getMessage()));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public MsgResponse handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        return StringUtils.isNotNull(code) ? MsgResponse.buildBizExceptionNoData(new BizException(String.valueOf(code), e.getMessage())) : MsgResponse.buildBizExceptionNoData(new BizException(e.getMessage()));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public MsgResponse handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return MsgResponse.buildBizExceptionNoData(new BizException(e.getMessage()));
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public MsgResponse handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return MsgResponse.buildBizExceptionNoData(new BizException(e.getMessage()));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public MsgResponse handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return MsgResponse.buildBizExceptionNoData(new BizException(message));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return MsgResponse.buildBizExceptionNoData(new BizException(message));
    }

    /**
     * 内部认证异常
     */
    @ExceptionHandler(InnerAuthException.class)
    public MsgResponse handleInnerAuthException(InnerAuthException e) {
        return MsgResponse.buildBizExceptionNoData(new BizException(e.getMessage()));
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public MsgResponse handleDemoModeException(DemoModeException e) {
        return MsgResponse.buildBizExceptionNoData(new BizException("演示模式，不允许操作"));
    }
}
