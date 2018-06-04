package com.chaos.controller;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendMessage(Destination destination, final String message) {
		
		jmsTemplate.convertAndSend(destination, message);
	}
}
