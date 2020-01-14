package com.chaos.biz;

import com.sun.star.sheet.DataResult;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import com.chaos.common.AbstractSimpleMessageListenerContainer;
import com.chaos.mq.MqMessageVO;
<<<<<<< HEAD
//import com.hivescm.common.domain.DataResult;
=======
>>>>>>> chaos

public class ChaosTestMq extends AbstractSimpleMessageListenerContainer {

	public ChaosTestMq(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}
	
	@Override
	public String getQueueName() {
		return "chaos-mq";
	}

	@Override
	public String consumeMessage(MqMessageVO message) {
		System.out.println(message.getMessage());
		return "dd";
	}

}
