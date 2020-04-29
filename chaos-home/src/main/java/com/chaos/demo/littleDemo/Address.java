package com.chaos.demo.littleDemo;

public class Address implements Cloneable{
	private String address;

	public Address(String address) {
		super();
		this.address = address;
	}

	public Address() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Address clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Address)super.clone();
	}
}
