package com.chaos.demo.demo2018.demo1121;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: demo
 * @description: 生产者和消费者，使用BlockingQueue实现
 * @author: 廖鹏
 * @create: 2018-11-22
 **/
public class ProducerAndComsumer2 {
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args){
        ProducerAndComsumer2 producerAndComsumer2 = new ProducerAndComsumer2();
        producerAndComsumer2.new Producer().start();
        producerAndComsumer2.new Comsumer().start();
    }

    class Producer extends Thread{
        int i=1;
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("生产数据："+i);
                    queue.put(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Comsumer extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    Integer take = queue.take();
                    System.out.println("消费数据："+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
