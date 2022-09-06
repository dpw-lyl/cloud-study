package com.dpw.lyl.join.good.job.foundation.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: dengpw
 * @createTime: 2022年08月18日 18:32:25
 * @version: 1.0.0
 * @Description: 统一日志切面
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月24 12:15:18
     * @description: 配置入口package路径
     * @param:
     * @return: void
     */
    @Pointcut("within(com.dpw.lyl.join.good.job..*)")
    public void applicationLogPointCut() {
    }


    /**
     * @author: dengpw
     * @createTime: 2022年08月24 12:15:51
     * @description: 异常处理
     * @param: joinPoint - [JoinPoint]
     * @param: throwable - [Throwable]
     * @return: void
     */
    @AfterThrowing(pointcut = "applicationLogPointCut() && springBeanPointcut()",throwing = "throwable")
    public void logAfterException(JoinPoint joinPoint, Throwable throwable) {
        log.error("系统发生异常,异常方法:{}.{}() ,异常原因： cause = '{}' and exception = '{}'", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), throwable.getCause() != null ? throwable.getCause() : "NULL", throwable.getMessage(), throwable);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月24 12:16:18
     * @description: 具体入参
     * @param: joinPoint - [ProceedingJoinPoint]
     * @return: java.lang.Object
     */
    @Around("applicationLogPointCut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }

    }


}
