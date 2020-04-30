package com.chaos.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Comparable<User>,Serializable{

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

    private Integer action;

    private Integer status;

    private String actionBy;

    private Long actionTime;

    private String createBy;

    private Long createTime;

    private String remark;
    
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
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public Long getActionTime() {
		return actionTime;
	}

	public void setActionTime(Long actionTime) {
		this.actionTime = actionTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.age-o.age;
	}
	
	
}
