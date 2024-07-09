package com.dpw.lyl.join.good.job.algorithm.collection;

import java.util.HashMap;

/**
 * @description: 模拟内存溢出
 * @author: dengpw$
 * @create: 2023-09-06 18:28
 * @version: 1.0.0$
 **/
public class TestDemo {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            threadLocal.set("itcast");
            print(name);
            System.out.println(name + "-after remove : " +
                    threadLocal.get());
        }, "t1").start();
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            threadLocal.set("itheima");
            print(name);
            System.out.println(name + "-after remove : " +
                    threadLocal.get());
        }, "t2").start();
    }
    static void print(String str) {
//打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + threadLocal.get());
//清除本地内存中的本地变量
        threadLocal.remove();
    }
}