package com.chaos.demo;

/**
 * 浅复制与深复制
 * 
 */
public class demo4 {

	public static void main(String[] args) {
		User u1 = new User();
		u1.setAge(32);
		Address addr = new Address("北京");
		u1.setAddr(addr);
		try {
			User u2 =u1.clone();
			u1.setAge(12);
			u1.getAddr().setAddress("杭州");
			System.out.println("u1:"+u1.getAge()+"----"+"address:"+u1.getAddr().getAddress());
			
			System.out.println("u2:"+u2.getAge()+"----"+"address:"+u2.getAddr().getAddress());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
