package com.chaos.demo.demo2018.demo1116;

/**
 * @program: demo
 * @description: 单例，饿汉式
 * @author: 廖鹏
 * @create: 2018-11-16
 **/
public class SingleTon2 {
    private static SingleTon2 s2 = new SingleTon2();
    private SingleTon2(){}

    public static synchronized SingleTon2 getInstans(){
        return s2;
    }
}
