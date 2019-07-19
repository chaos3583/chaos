package com.chaos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaos.dao.mapper.UserMapper;
import com.chaos.model.User;
import com.chaos.mq.MqMessageVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
//	@Autowired
//	private RabbitMqSendService rabbitMqService;
	
	@Override
	public User login(User user) {
		System.out.println("执行成功");
		MqMessageVO messageVo = new MqMessageVO();
		messageVo.setMessage("测试mq");
		messageVo.setQueueName("chaos-mq");
		User loginUser = userMapper.login(user);
//		DataResult<Boolean> send = rabbitMqService.send(messageVo);
		return loginUser;
	}

	@Override
	public User selectByUser(User user) {
		return userMapper.selectByUser(user);
	}


}
