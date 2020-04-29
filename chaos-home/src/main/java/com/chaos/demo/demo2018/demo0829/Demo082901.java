package com.chaos.demo.demo2018

/**
 *主线程无法捕获子线程的异常，子线程的异常只能自己处理
 */
public class Demo082901 {
    public static void myThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                int j=10;
                try {
                    int result = j / i;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("子线程捕获到异常");
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args){
        try {
            myThread();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("主线程捕获到异常");
        }
    }
}
