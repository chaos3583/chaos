package com.chaos.util;

import java.io.Serializable;

public class ServiceResult<T> implements Serializable {
	private Boolean success = Boolean.valueOf(true);
	private String errorCode;
	private String message;
	private T body;

	public ServiceResult() {
	}

	public ServiceResult(T body) {
		this.errorCode = "000";
		this.body = body;
	}

	public ServiceResult(String errorCode, String message) {
		this.success = Boolean.valueOf(false);
		this.errorCode = errorCode;
		this.message = message;
	}
	public ServiceResult(String errorCode, String message, T body) {
		this.success = Boolean.valueOf(false);
		this.errorCode = errorCode;
		this.message = message;
		this.body = body;
	}

	public Boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return this.body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
