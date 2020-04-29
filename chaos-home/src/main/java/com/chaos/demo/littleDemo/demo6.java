package com.chaos.demo.littleDemo;

/**
 * 在父类构造函数内部调用具有多态行为的方法将导致无法预测的结果，因为此时子类对象还没初始化，此时调用子类方法不会得到我们想要的结果
 * @author Administrator
 *@date 2018年2月1日
 */
public class demo6 {
	public static void main(String[] args) {
		new Son1(5);
	}
}

class Father1 {
	
	Father1(){
		System.out.println("father before draw");
		draw();//此处调用具有多态行为的方法
		System.out.println("father after draw");
	}
	
	void draw(){
		System.out.println("father.draw");
	}
}

class Son1 extends Father1{
	private int a=8;
	Son1(int i){
		a=i;
		System.out.println("son construstor="+a);
	}
	void draw() {
		System.out.println("son.draw="+a);
	}
}
