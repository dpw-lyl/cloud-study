package com.dpw.lyl.join.good.job.interceptor;

import com.alipay.api.AlipayApiException;
import com.dpw.lyl.join.good.job.controller.AbstractForGoodJobController;
import com.ijpay.alipay.AliPayApiConfigKit;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: dengpw
 * @createTime: 2022年08月18 16:41:22
 * @version: 1.0.0
 * @Description: ali支付配置全局
 */
public class AliPayInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws AlipayApiException {
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (!(controller instanceof AbstractForGoodJobController)) {
                throw new RuntimeException("控制器需要继承 AbstractForGoodJobController");
            }
            AliPayApiConfigKit.setThreadLocalAliPayApiConfig(((AbstractForGoodJobController) controller).getApiConfig());
            return true;
        }
        return false;
    }

}
