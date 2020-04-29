package com.chaos.demo.littleDemo;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory2 implements MethodInterceptor{

	private Object target;
	
	public ProxyFactory2(Object target) {
		super();
		this.target = target;
	}
	public Object getProxyInstance(){
		//工具类
		Enhancer en = new Enhancer();
		//设置父类
		en.setSuperclass(target.getClass());
		//设置回调函数
		en.setCallback(this);
		//创建子类（代理对象）
		return en.create();
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy arg3) throws Throwable {
		
		System.out.println("开始事务");
		//执行目标类方法
		Object invoke = method.invoke(target, arg2);
		System.out.println("提交事务");
		return invoke;
	}
}
