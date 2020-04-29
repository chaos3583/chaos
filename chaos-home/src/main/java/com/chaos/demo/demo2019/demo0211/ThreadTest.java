package com.chaos.demo.demo2019.demo0211;

/**
 * @program: demo
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-02-12 10:49
 **/
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 200, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        long s1 = System.currentTimeMillis();
        // 计数控制
        final CountDownLatch latch = new CountDownLatch(330);
        for(int i=0;i<330;i++){
            // 自定义执行体
            MyTask myTask = new MyTask(i,latch);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        // 控制所有线程全部跑完（异步执行可以去掉）
        latch.await();
        long s2 = System.currentTimeMillis();
        long s =s2-s1;
        System.out.println("线程池执行时间:"+s);
        System.out.println("执行完毕=============================");
        executor.shutdown();
    }
}


class MyTask implements Runnable {
    private int taskNum;
    private CountDownLatch latch;

    public MyTask(int num,CountDownLatch latch) {
        this.taskNum = num;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
