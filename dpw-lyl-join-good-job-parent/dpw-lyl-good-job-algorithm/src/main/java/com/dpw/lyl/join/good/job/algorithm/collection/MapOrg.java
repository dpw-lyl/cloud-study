package com.dpw.lyl.join.good.job.algorithm.collection;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * @author Administrator
 */
public class MapOrg {

    private static int a;
    private static final Object lock = new Object();

    public static void main(String[] args) throws Exception {

        Callable<Integer> integerCallable = MapOrg::getBack;
        Callable<Integer> integerCallable1 = MapOrg::getBack;
        Callable<Integer> integerCallable2 = MapOrg::getBack;
        Callable<Integer> integerCallable3 = MapOrg::getBack;
        integerCallable.call();
        integerCallable1.call();
        integerCallable2.call();
        integerCallable3.call();
    }

    public static int getBack() {
        synchronized (lock) {
            a = a + 1;
        }
        return a;
    }
}
