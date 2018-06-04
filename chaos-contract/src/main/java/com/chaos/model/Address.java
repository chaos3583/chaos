package com.chaos.model;

public class Address implements Cloneable{

	private String addr;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	protected Address clone() throws CloneNotSupportedException {
		
		return (Address)super.clone();
	}
	
	
}
