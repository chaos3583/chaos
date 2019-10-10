package com.chaos.demo;

/**
 * @program: chaos
 * * @description: 分析StringBuilder为什么是线程不安全的
 * 1、StringBuilder和StringBuffer都继承了AbstractStringBuilder
 * 2、StringBuilder的append方法没有加synchronized，而且方法里面调的是父类AbstractStringBuilder的append方法，
 *    AbstractStringBuilder的append方法存在count += len 操作是非原子性的，所以是非线程安全的；
 * 3、StringBuffer的append方法有synchronized修饰，所以即使也是调的父类的append方法，但却是线程安全的
 * * @author: liaopeng
 * * @create: 2019-10-10 14:13
 **/
public class StringBuilderDemo {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i <10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0;j<1000;j++){
                        sb.append("a");
                        sf.append("a");
                    }
                }
            }).start();
        }
        Thread.sleep(100);
        System.out.println("StringBuilder:"+sb.length());
        System.out.println("StringBuffer:"+sf.length());

    }
}
