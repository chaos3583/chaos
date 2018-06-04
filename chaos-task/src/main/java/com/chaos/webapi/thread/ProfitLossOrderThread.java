package com.chaos.webapi.thread;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

/**
 * @author Administrator
 *@date 2017年9月28日
 */
public class ProfitLossOrderThread extends Thread {

	Logger log = Logger.getLogger(getClass());
	//分片的索引
	private int shardingItem;
	private CountDownLatch endLatch;
	private int maxDataNum;
	
	public ProfitLossOrderThread(int shardingItem,CountDownLatch endLatch ,int maxDataNum){
		this.shardingItem = shardingItem;
		this.endLatch = endLatch;
		this.maxDataNum = maxDataNum;
	}
	
	@Override
	public void run() {
		log.info("任务执行中！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
	}

}
