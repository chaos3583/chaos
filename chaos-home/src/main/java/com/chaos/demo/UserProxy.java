package com.chaos.demo;

public class UserProxy implements IUserDao{

	private IUserDao userDao;
	
	public UserProxy(IUserDao userDao) {
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
