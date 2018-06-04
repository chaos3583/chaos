package com.chaos.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements Comparable<User>{

	private Integer id;
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
     * token
     */
    private String token;
    
	public User() {
		super();
	}

	public User(Integer age) {
		super();
		this.age = age;
	}

	/**
     * token 失效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tokenExpireDate;
    /**
     * token 失效时间（秒）
     */
    private Integer tokenExpireTime;

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpireDate() {
		return tokenExpireDate;
	}

	public void setTokenExpireDate(Date tokenExpireDate) {
		this.tokenExpireDate = tokenExpireDate;
	}

	public Integer getTokenExpireTime() {
		return tokenExpireTime;
	}

	public void setTokenExpireTime(Integer tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.age-o.age;
	}
	
	
}
