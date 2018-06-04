package com.chaos.demo;

import java.math.BigDecimal;

public class demo10 {
	
	public static void main(String[] args) {
		BigDecimal b1 = new BigDecimal("2.532");
		BigDecimal b2 = new BigDecimal("1.400");
		
		int compareTo = b1.compareTo(b2);
		
		System.out.println("b1:"+b1);
		System.out.println("b2:"+b2);
		
		BigDecimal setScale = b1.setScale(2,BigDecimal.ROUND_HALF_UP);
		System.out.println("scale:"+setScale);
		
		BigDecimal divide2 = b1.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
		System.out.println("divide2:"+divide2);
		
		BigDecimal b3 = BigDecimal.valueOf(2.532);
		BigDecimal b4 = BigDecimal.valueOf(1.400);
		System.out.println("b3:"+b3);
		System.out.println("b4:"+b4);
		
		//加法
		BigDecimal add = b1.add(b2);
		System.out.println("加法："+add);
		
		//减法
		BigDecimal subtract = b1.subtract(b2);
		System.out.println("减法："+subtract);
		
		//乘法
		BigDecimal multiply = b1.multiply(b2);
		System.out.println("乘法："+multiply);
		
		//除法
		BigDecimal divide= b1.divide(b2, 2); //保留两位小数,可添加第三个参数来指定四舍五入方式，不指定默认四舍五入
		System.out.println("除法："+divide);
		
	}
}
