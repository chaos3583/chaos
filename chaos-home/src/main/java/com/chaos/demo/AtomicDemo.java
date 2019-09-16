package com.chaos.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-08-23 11:46
 **/
public class AtomicDemo {

    private static final int THREADS_CONUT = 20;
    public static int count = 0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_CONUT];
        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
        AtomicInteger integer = new AtomicInteger();
        integer.incrementAndGet();
    }
}
