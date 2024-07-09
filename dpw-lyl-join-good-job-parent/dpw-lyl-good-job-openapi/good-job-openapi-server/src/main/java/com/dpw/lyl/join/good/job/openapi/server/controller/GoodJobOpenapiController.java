package com.dpw.lyl.join.good.job.openapi.server.controller;

import com.alibaba.fastjson2.util.ParameterizedTypeImpl;
import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.ResponseCodeEnum;
import com.dpw.lyl.join.good.job.foundation.base.BaseHandler;
import com.dpw.lyl.join.good.job.foundation.base.BaseParam;
import com.dpw.lyl.join.good.job.foundation.utils.SpringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: dengpw
 * @createTime: 2022年09月08日 17:47:23
 * @version: 1.0.0
 * @Description: 外部接口接入管理应用
 */
@Slf4j
@RestController
@RequestMapping("/openApi")
public class GoodJobOpenapiController {
    private static final ObjectMapper OBJECT_MAPPER = new MappingJackson2HttpMessageConverter().getObjectMapper();

    private final TypeFactory typeFactory = OBJECT_MAPPER.getTypeFactory();


    private final ConcurrentHashMap<String, Optional<BaseHandler>> hasHandlerMap = new ConcurrentHashMap<>(16);

    @Resource
    private List<BaseHandler> handlers;


    @PostMapping(value = "/test/{handlerName}", produces = "application/json;charset=utf-8")
    public MsgResponse openapi(@PathVariable("handlerName") String handlerName, HttpServletRequest servletRequest) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Optional<BaseHandler> optional = hasHandlerMap.getOrDefault(handlerName, Optional.empty());
        if (!optional.isPresent()) {
            for (BaseHandler handler : handlers) {
                if (handlerName.equals(handler.handlerName())) {
                    hasHandlerMap.putIfAbsent(handlerName, Optional.of(handler));
                    optional = Optional.of(handler);
                    break;
                }
            }
        }
        if (optional.isPresent()) {
            Class<? extends BaseHandler> aClass = optional.get().getClass();
            Type javaType = getType(aClass);
            Object param = OBJECT_MAPPER.readValue(servletRequest.getReader(), typeFactory.constructType(javaType));
            Method method = aClass.getMethod("execute", BaseParam.class);
            Object re = method.invoke(SpringUtils.getBean(handlerName), param);
            log.info("外部接口入参：{}", param.toString());
            log.info("外部接口入参类型{}", javaType.toString());
            return MsgResponse.buildSuccess(re);
        }
        return MsgResponse.buildSuccess(ResponseCodeEnum.SYSTEM_ERROR);
    }

    private static Type getType(Class<? extends BaseHandler> aClass) {
        ParameterizedType parameterizedType = null;
        if (aClass.getGenericSuperclass() instanceof ParameterizedType) {
            parameterizedType = (ParameterizedTypeImpl) aClass.getGenericSuperclass();
        } else if (aClass.getGenericInterfaces()[0] instanceof ParameterizedType) {
            parameterizedType = (ParameterizedTypeImpl) aClass.getGenericInterfaces()[0];
        }
        assert parameterizedType != null;
        return parameterizedType.getActualTypeArguments()[0];
    }
}
