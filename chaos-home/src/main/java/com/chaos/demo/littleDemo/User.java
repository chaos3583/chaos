package com.chaos.demo.littleDemo;

public class User implements Cloneable{
	private String name;
	
	private Integer age;
	
	private Address addr;

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public User clone() throws CloneNotSupportedException {
		User user = (User)super.clone();
		user.addr=(Address) addr.clone();
		return user;
	}
}
