package com.chaos.demo.littleDemo;

/**
 * 如果某个方法为静态的，那么它的行为不具有多态性
 * @author Administrator
 *@date 2018年2月1日
 */
public class demo5 {
	public static void main(String[] args) {
		Father father = new Son();
		father.staticMethod();
		father.normalMethod();
	}
}

class Father {
	public static void staticMethod() {
		System.out.println("Father static method");
	}
	
	public void normalMethod() {
		System.out.println("Father normal method");
	}
}

class Son extends Father{
	public static void staticMethod() {
		System.out.println("Son static method");
	}
	
	public void normalMethod() {
		System.out.println("son normal method");
	}
}


