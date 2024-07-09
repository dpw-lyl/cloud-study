package com.dpw.lyl.join.good.job.design.singleton;
/**
  * @description:
  * @author: Administrator
  * @create: 2023/9/4 0004 14:43
  * @version: 1.0.0
 */
public class Singleton {

    private Singleton singleton ;

    private Singleton(){}

    static class Instance{
        private static final Singleton MSG =new Singleton();
    }

    public static Singleton getInstance(){
        return Instance.MSG;
    }
}
