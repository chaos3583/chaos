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
		User login = userDao.login(user);
		return login;
	}
	

}
