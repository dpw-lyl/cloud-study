package com.dpw.lyl.join.good.job.foundation.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: dengpw
 * @createTime: 2022年08月15日 18:11:45
 * @version: 1.0.0
 * @Description: 获取beanUtil
 */
@Component
public final class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == SpringUtils.applicationContext) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月15 18:13:44
     * @description: 获取ApplicationContext
     * @param:
     * @return: org.springframework.context.ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月15 18:13:31
     * @description: 根据name获取Bean
     * @param: name - [String]
     * @return: java.lang.Object
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月15 18:13:18
     * @description: 根据Class获取Bean
     * @param: clazz - [Class<T>]
     * @return: T
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }


    /**
     * @author: dengpw
     * @createTime: 2022年08月15 18:13:04
     * @description: 根据name,class获取Bean
     * @param: name - [String]
     * @param: clazz - [Class<T>]
     * @return: T
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}