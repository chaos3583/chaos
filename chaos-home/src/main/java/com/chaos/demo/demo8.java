package com.chaos.demo;

/**
 * 动态代理测试类
 * @author Administrator
 *@date 2018年2月1日
 */
public class demo8 {

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		//给目标对象生成代理对象
		UserDao proxy =(UserDao) new ProxyFactory2(userDao).getProxyInstance();
		//代理对象执行方法
		proxy.add();
		
	}
}
