package com.chaos.webapi.job;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.chaos.webapi.thread.ProfitLossOrderThread;
import com.chaos.webapi.thread.ThreadPoolUtils;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author Administrator
 *@date 2017年9月28日
 */
public class ProfitLossOrderJob implements SimpleJob {
	Logger log = Logger.getLogger(getClass());
	
	private int maxThreadNum = 10;
	private int maxDataNum = 100;
	
	@Override
	public void execute(ShardingContext shardingContext) {
		int shardingItem = shardingContext.getShardingItem();
		invokeThread(shardingItem);
	}
	
	/**
	 * @param shardingItem
	 */
	private void invokeThread(int shardingItem) {
		final CountDownLatch billLatch = new CountDownLatch(maxThreadNum);
		final CountDownLatch checkBillLatch = new CountDownLatch(maxThreadNum);
		int item = shardingItem +1;
		log.info("损溢单更新库存~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~分片： "+item+"process  start!");
		for(int i=0 ;i<maxThreadNum;i++){
			//损溢单更新库存
			ProfitLossOrderThread threadProcess = new ProfitLossOrderThread((shardingItem*10)+i,billLatch,this.maxDataNum);
			ThreadPoolUtils.execute(threadProcess);
		}
		
		try {
			log.info("损溢单更新库存~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~分片： "+item+"等待原有线程结束");
			billLatch.await();
			checkBillLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("损溢单更新库存~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~分片： "+item+"process  end!");
	}
}
