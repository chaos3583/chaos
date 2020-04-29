package com.chaos.demo.littleDemo;

import com.chaos.demo.littleDemo.NoVisibility;

public class UserDao implements NoVisibility.IUserDao {

	@Override
	public void add() {
		System.out.println("添加方法");
	}

}
