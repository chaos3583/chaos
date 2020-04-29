package com.chaos.demo.littleDemo;

import com.chaos.demo.littleDemo.NoVisibility;

public class UserProxy implements NoVisibility.IUserDao {

	private NoVisibility.IUserDao userDao;
	
	public UserProxy(NoVisibility.IUserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void add() {
		System.out.println("开始事务");
		userDao.add();//执行目标对象的方法
		System.out.println("提交事务");
	}

}
