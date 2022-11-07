package com.dpw.lyl.join.good.job.foundation.utils;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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
public final class SpringUtils implements ApplicationContextAware, BeanFactoryPostProcessor {

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


    public static <T> T getBean(String name) throws BeansException {
        return (T) getApplicationContext().getBean(name);
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
     * @description: 根据name, class获取Bean
     * @param: name - [String]
     * @param: clazz - [Class<T>]
     * @return: T
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }


    /**
     * @author: dengpw
     * @createTime: 2022年09月14 12:23:56
     * @description: 如果applicationContext包含一个与所给名称匹配的bean定义，则返回true
     * @param: name - [String]
     * @return: boolean
     */
    public static boolean containsBean(String name) {
        return getApplicationContext().containsBean(name);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年09月14 12:24:45
     * @description: 判断以给定名字注册的bean定义是一个singleton还是一个prototype
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param: name - [String]
     * @return: boolean
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().isSingleton(name);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年09月14 12:25:11
     * @description: Class 注册对象的类型
     * @param: name - [String]
     * @return: java.lang.Class<?>
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getType(name);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年09月14 12:25:33
     * @description: 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param: name - [String]
     * @return: java.lang.String[]
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getAliases(name);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年09月14 12:25:54
     * @description: AOP代理对象获取
     * @param: invoker - [T]
     * @return: T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (null == SpringUtils.applicationContext) {
            SpringUtils.applicationContext = (ApplicationContext) beanFactory;
        }
    }
}