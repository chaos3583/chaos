package com.chaos.demo.demo2019.demo0211;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: 测试AtomicInteger原子性
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-02-11 15:42
 **/
public class Demo01 {

    public static AtomicInteger i = new AtomicInteger();

    public static void main(String[] args) {
        while(i.get()<10){
            System.out.println(i);
            i.incrementAndGet();
        }
    }
}
