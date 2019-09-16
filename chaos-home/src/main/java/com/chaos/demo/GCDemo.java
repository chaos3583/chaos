package com.chaos.demo;

import sun.misc.GC;

/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-08-30 10:27
 **/
public class GCDemo {
    public Object instance =null;

    public static void main(String[] args) {
        GCDemo objA = new GCDemo();
        GCDemo objB = new GCDemo();
        objA.instance=objB;
        objB.instance=objA;
        System.gc();
    }
}
