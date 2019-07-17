//package com.chaos.rabbitmqSender;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.chaos.mq.MqMessageVO;
//import com.chaos.util.JsonMapper;
///**
// * 消息发送服务类
// * @author zlj
// * @date 2018年3月20日 下午6:09:45
// */
//@Service
//public class RabbitMqSendService {
//
//	private static final Logger logger = LogManager.getLogger(RabbitMqSendService.class);
//
//	@Autowired
//	private AmqpTemplate amqpTemplate;
//	/**
//	 * 消息发送
//	 * @param mqvo
//	 * @return
//	 */
//	public Boolean send(MqMessageVO mqvo){
//		logger.info("收到发送MQ消息请求，内容："+JsonMapper.toJsonString(mqvo));
//		if(StringUtils.isEmpty(mqvo.getQueueName())){
//			return false;
//		}
//		try{
//			amqpTemplate.convertAndSend(mqvo.getQueueName(), mqvo.toString());
//		}catch(Exception e){
//			logger.error("消息发送异常：",e);
//			return false;
//		}
//		logger.info("RabbitMQ发送消息成功："+JsonMapper.toJsonString(mqvo));
//		return true;
//	}
//}
