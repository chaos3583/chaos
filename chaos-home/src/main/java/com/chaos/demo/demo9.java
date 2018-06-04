package com.chaos.demo;

public class demo9 {

	public static void main(String[] args) {
		CglibDao cglibDao = new CglibDao();
		//给目标对象生成代理对象
		CglibDao proxy =(CglibDao) new ProxyFactory2(cglibDao).getProxyInstance();
		//代理对象执行方法
		proxy.save();
	}
}
