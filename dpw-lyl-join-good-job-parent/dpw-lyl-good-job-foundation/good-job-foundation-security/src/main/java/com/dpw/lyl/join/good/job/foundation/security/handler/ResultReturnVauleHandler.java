package com.dpw.lyl.join.good.job.foundation.security.handler;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dengpw
 * @createTime: 2022年09月21 15:50:19
 * @version: 1.0.0
 * @date 2021年05月19日14:17
 * @desc
 */
public class ResultReturnVauleHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler target;

    public ResultReturnVauleHandler(HandlerMethodReturnValueHandler target) {
        this.target = target;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return target.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

       /* AjaxResult ajaxResult = (AjaxResult) returnValue;

        Object data = ajaxResult.get("data");

        Map<String, String> returnResult = this.FieldsConvert(data);
        ajaxResult.put("data", returnResult);*/
        target.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

    public Map<String, String> FieldsConvert(
            Object data) throws IllegalAccessException, IllegalArgumentException {

        if (data == null) {
            return null;
        }
        Map<String, String> map =
                new HashMap<>();
        Field[] fields = data.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            String name = field.getName();
            Object value;
            if (field.getType() == Long.class) {
                value = field.get(data).toString();
            } else {
                value = field.get(data);
            }
            if (field.get(data) != null) {
                map.put(name, value.toString());
            }
        }

        return map;
    }
}
