package com.chaos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaos.dao.mapper.IUserDao;
import com.chaos.model.User;
import com.chaos.mq.MqMessageVO;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
//	@Autowired
//	private RabbitMqSendService rabbitMqService;
	
	@Override
	public User login(User user) {
		System.out.println("执行成功");
		MqMessageVO messageVo = new MqMessageVO();
		messageVo.setMessage("测试mq");
		messageVo.setQueueName("chaos-mq");
//		DataResult<Boolean> send = rabbitMqService.send(messageVo);
		return user;
	}
	

}
