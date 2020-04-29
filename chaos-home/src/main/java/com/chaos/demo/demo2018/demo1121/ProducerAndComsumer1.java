package com.chaos.demo.demo2018.demo1121;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: demo
 * @description:实现一个生产者消费者问题，使用synchronize锁，wait和sleep
 * 产者和消费者在同一时间段内共用同一存储空间，生产者向空间里生产数据，而消费者取走数据
 * @author: 廖鹏
 * @create: 2018-11-21
 **/
public class ProducerAndComsumer1 {
    public static void main(String[] args){
        ProducerAndComsumer1 demo01 = new ProducerAndComsumer1();
        Producer producer = demo01.new Producer();
        Comsumer comsumer = demo01.new Comsumer();
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread comsumerThread = new Thread(comsumer);
        comsumerThread.start();
    }

    private final int maxSize=10;
    private Queue<Integer> queue = new LinkedList<>();

    /**
     * 生产者
     */
    class Producer implements Runnable{
        int i=1;
        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    if(queue.size()==maxSize){
                        try {
                            queue.wait();
                            System.out.println("队列已满等待消费……");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.notify();
                    System.out.println("生产数据："+i);
                    queue.add(i++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Comsumer implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    if(queue.size()==0){
                        try {
                            queue.wait();
                            System.out.println("队列为空等待生产……");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.notify();
                    Integer poll = queue.poll();
                    System.out.println("消费数据："+poll);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
