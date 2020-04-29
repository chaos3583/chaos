package com.chaos.demo.littleDemo;

import com.chaos.model.User;

public class demo7 {

	public static void main(String[] args) {
		User user = new User();
		//目标对象
		UserDao userDao = new UserDao();
		//把目标对象传给代理对象
		UserProxy proxy = new UserProxy(userDao);
		//执行的是代理的方法
		proxy.add();
		
	}
}
