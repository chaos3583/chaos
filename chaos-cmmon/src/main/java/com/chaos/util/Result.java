package com.chaos.util;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;


public abstract class Result implements Serializable{

    private static final long serialVersionUID = 955720894672662001L;

    protected String code = "000";
    protected String msg = "";
    protected boolean success;
    protected String traceID = "";

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getTraceID() {
		if(StringUtils.isEmpty(traceID)){
			traceID = MDC.get("traceID");
		}
		return traceID;
	}


	public void setTraceID(String traceID) {
		this.traceID = traceID;
	}


	public abstract Map<String, ?> toMap();
}
