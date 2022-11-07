package com.dpw.lyl.join.good.job.foundation.security.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一返回
 *
 * @Author: dengpw
 * @createTime: 2022年09月21 15:50:19
 * @version: 1.0.0
 * @date 2021年05月19日15:05
 * @desc
 */
//@Configuration
public class ResponseBodyWrapFactoryBean implements InitializingBean {
    private final RequestMappingHandlerAdapter adapter;

    @Autowired
    public ResponseBodyWrapFactoryBean(RequestMappingHandlerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        if (returnValueHandlers.size() > 0) {
            // 将内置的返回值处理器进行替换
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
            decorateHandlers(handlers);
            adapter.setReturnValueHandlers(handlers);
        }
    }

    /**
     * 将所有RequestResponseBodyMethodProcessor返回值处理器替换为自定义的返回值处理器
     *
     * @author tianxincode@163.com
     * @since 2020/10/12
     */
    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                // 替换为自定义返回值处理器
                ResultReturnVauleHandler decorator = new ResultReturnVauleHandler(handler);
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }
    }

}
