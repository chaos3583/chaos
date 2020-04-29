package com.chaos.demo.demo2018.demo1116;

/**
 * @program: demo
 * @description: 单例，懒汉式
 * @author: 廖鹏
 * @create: 2018-11-16
 **/
public class SingleTon1 {
    private static SingleTon1 s1=null;
    private SingleTon1(){}

    public static synchronized SingleTon1 getInstans(){
        if(s1==null){
            s1=new SingleTon1();
        }
        return s1;
    }
}
