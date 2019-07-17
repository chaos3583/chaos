package com.chaos.common;

import com.chaos.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import com.chaos.mq.MqMessageVO;
import com.chaos.util.JsonMapper;
//import com.hivescm.common.domain.DataResult;
import com.rabbitmq.client.Channel;

public abstract class AbstractSimpleMessageListenerContainer extends SimpleMessageListenerContainer{
	private Logger log = LogManager.getLogger(getClass());
	private final int retryNum = 50;//消息消费重试次数
	private int curRetryNum = 0 ;//当前重试次数
	
	public AbstractSimpleMessageListenerContainer(ConnectionFactory connectionFactory){
		this.setConnectionFactory(connectionFactory);
		this.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		this.setMessageListener(new ChannelAwareMessageListener(){
			@Override
			public void onMessage(Message message, Channel channel) throws Exception {
				handleMessage(message, channel);
			}
			
		});
	}
	@Override
	public String[] getQueueNames() {
		String queueName = getQueueName();
		super.setQueueNames(queueName);
		return super.getQueueNames();
	}
	/**
	 * 获取消息队列名称
	 * @return
	 */
	public abstract String getQueueName();
	/**
	 * 处理消息队列消息
	 * @param message
	 * @param channel
	 * @throws Exception
	 */
	private void handleMessage(Message message, Channel channel) throws Exception {
		String mes = new String(message.getBody(),"UTF-8");
		log.info("Abstract接收到消息内容："+mes);
		try{
			MqMessageVO messagevo = (MqMessageVO) JsonMapper.fromJsonString(mes, MqMessageVO.class);
			String dr = consumeMessage(messagevo);
			log.info("Abstract消息消费返回结果：",mes);
			//入库（成功失败不影响消息队列的处理）
//			buildMqConsumeLog(dr, mes);
			if(StringUtils.isEmpty(dr)){
				if(this.curRetryNum >= this.retryNum){
					channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
					log.info("队列："+getQueueName()+"消息处理失败大于指定次数，确认成功!"+"消息内容："+mes);
					return;
				}
				this.curRetryNum++;
				//延时指定时间
				threadSleep();
				channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
				return;
			}else{
				this.curRetryNum = 0;
				channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
				log.info("队列："+getQueueName()+"消息处理成功!"+"消息内容："+mes);
				return;
			}
			
		}catch(Exception e){
			log.error("Abstract处理消息队列消异常：",e);
			if(this.curRetryNum == 5){//只记录最后一次失败的
				//入库（成功失败不影响消息队列的处理）
//				buildMqConsumeLog(DataResult.faild(-1,e.getMessage()), mes);
			}
			if(this.curRetryNum >= this.retryNum){
				channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
				this.curRetryNum = 0 ;
				log.info("队列："+getQueueName()+"消息处理异常大于指定次数，确认成功!");
			}else{
				this.curRetryNum++;
				//延时指定时间
				threadSleep();
				channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
			}
			
		}
		
	}
	/**
	 * 休眠当前线程（重试次数*10秒）
	 */
	private void threadSleep(){
		//延时指定时间
		try {
			Thread.sleep((this.curRetryNum*10)*1000);
		} catch (InterruptedException e) {
			log.error("线程延时异常：",e);
		}
	}
//	private void buildMqConsumeLog(DataResult<String> dr,String mes){
//		try{
//			String recordSuc = ScmGlobal.getConfig("mq.consumer.record.success");
//			if(StringUtils.isNotEmpty(recordSuc) && "false".equalsIgnoreCase(recordSuc.toLowerCase())){
//				if(dr.isSuccess()){
//					return;
//				}
//			}
//			MqMessageVO mqvo = (MqMessageVO) JsonMapper.fromJsonString(mes, MqMessageVO.class);
////			SspMqConsumerLog sspMqLog = new SspMqConsumerLog();
////			sspMqLog.setId(iScmBaseFeignService.getSCMId(mqvo.getTsharding() == null ? 0L : mqvo.getTsharding()));
////			sspMqLog.setTsharding(sspMqLog.getId());
////			sspMqLog.setMsgContant(mes);
////			sspMqLog.setMsgType(mqvo.getMessageType()==null?null:mqvo.getMessageType().toString());
////			sspMqLog.setQueName(getQueueName());
////			sspMqLog.setBillId(mqvo.getTsharding());
////			sspMqLog.setTs((new Date()).getTime());
//			String drStr = JsonMapper.toJsonString(dr);
//			if(drStr != null && drStr.length() > 250){
//				sspMqLog.setResult(drStr.substring(0, 250));
//			}else{
//				sspMqLog.setResult(drStr);
//			}
//			
//			if(dr.isSuccess()){
//				sspMqLog.setState(ScmQueueStateEnum.SUCCESS.getValue());
//			}else{
//				sspMqLog.setState(ScmQueueStateEnum.FAIL.getValue());
//			}
////			iSspMqConsumerLogService.insert(sspMqLog);
//		}catch(Exception e){
//			log.error("消息入库异常：",e);
//			log.error("队列："+getQueueName()+"消息："+mes+"；入库异常。");
//		}
//	}
	/**
	 * 消费队列消息
	 * @param message
	 * @return
	 */
	public abstract String consumeMessage(MqMessageVO message);
}
