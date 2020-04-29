package com.chaos.demo.demo2018.demo0828;
/**
 * 死锁demo
 */
public class Demo082801 {
    public static String resourceA="A";
    public static String resourceB="B";

    public  static void deadLock(){
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                    }
                    System.out.println("获得A锁执行线程A");
                    synchronized (resourceB){
                        System.out.println("获得B锁执行线程A");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB){
                    System.out.println("获得B锁执行线程B");
                    synchronized (resourceA){
                        System.out.println("获得A锁执行线程B");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }

    public static void main(String[] args){
        deadLock();
    }
}
