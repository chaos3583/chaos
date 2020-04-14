package com.chaos.biz;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import com.chaos.common.AbstractSimpleMessageListenerContainer;
import com.chaos.mq.MqMessageVO;

public class ChaosTestMq extends AbstractSimpleMessageListenerContainer {

//	public ChaosTestMq(ConnectionFactory connectionFactory) {
//		super(connectionFactory);
//	}
	
	@Override
	public String getQueueName() {
		return "chaos-mq";
	}

	/*@Override
	public DataResult<String> consumeMessage(MqMessageVO message) {
		System.out.println(message.getMessage());
		return DataResult.success("dd",String.class);
	}*/

}
