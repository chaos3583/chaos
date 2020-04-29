package com.chaos.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class DataResult<T> extends Result {

    private static final long serialVersionUID = -3148778572856143638L;
    private T data;

    public DataResult(String code, String msg, boolean success, String traceID, T data) {
        this.code = (code == null ? "001" : code);
        this.msg = (msg == null ? ("000".equals(code) ? "执行成功" :msg) : msg);
        this.success = success;
        this.traceID = (traceID == null ? StringUtils.EMPTY : traceID);
        this.data = (data == null ? (T) new JSONObject() : data);
    }

    public DataResult(String code, String msg, String traceID, T data) {
        this(code, msg, "000".equals(code), traceID, data);
    }

    public DataResult(String code, String msg, boolean success, String traceID) {
        this(code, msg, success, traceID, null);
    }

    public DataResult(String code, String traceID, T data) {
        this(code, null, traceID, data);
    }

    public DataResult(String code, T data) {
        this(code, null, "", data);
    }

    @Override
    public Map<String, ?> toMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", data);
        result.put("code", this.getCode());
        result.put("msg", this.getMsg());
        result.put("success", this.isSuccess());
        result.put("traceID", this.getTraceID());
        return result;
    }

}
