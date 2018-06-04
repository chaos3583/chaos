package com.chaos.mq;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.chaos.util.JsonMapper;

public class MqMessageVO implements Serializable {

	private static final long serialVersionUID = -5204421195576158400L;
	//分库分表依据
	private Long tsharding;
	//消息队列名
	private String queueName;
	//消息内容-字符串
	private String message;
	//消息内容-对象
	private Object mesObj;
	//消息类型
	private Integer messageType;
	
	public Long getTsharding() {
		return tsharding;
	}
	public void setTsharding(Long tsharding) {
		this.tsharding = tsharding;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getMessageType() {
		return messageType ;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	public Object getMesObj() {
		return mesObj;
	}
	public void setMesObj(Object mesObj) {
		this.mesObj = mesObj;
	}
	@Override
	public String toString() {
		if(StringUtils.isEmpty(this.message) && this.mesObj == null){
			return "";
		}
		return JsonMapper.toJsonString(this);
	}

}
