package com.chaos.demo.littleDemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

	public static void main(String[] args){
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(3);//标识队列长度为2
		DiscardOldestPolicy handler = new ThreadPoolExecutor.DiscardOldestPolicy();//饱和策略，标识当任务数超过了最大线程数时，丢弃队列里最近一个任务，执行当前任务；
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, workQueue,handler);
		for(int i=0;i<15;i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
//					System.out.println("线程执行");
					for(int j=0;j<2000332200;j++) {
						
					}
				}
			});
			threadPool.execute(thread);
			System.out.println("队列大小："+workQueue.size());
			System.out.println("线程池大小："+threadPool.getPoolSize());
		}
		threadPool.shutdown();
	}
}
