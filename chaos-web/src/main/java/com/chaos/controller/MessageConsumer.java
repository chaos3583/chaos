package com.chaos.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

	@JmsListener(destination="aaa" )
	public void reveiveMessage(String message) {
		System.out.println("消息接收："+message);
	}
	
	
}
