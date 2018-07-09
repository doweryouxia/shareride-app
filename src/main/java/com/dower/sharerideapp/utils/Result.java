package com.dower.sharerideapp.utils;

/**
 * 返回数据
 * @author wangwei
 *
 */
public class Result {
	
	public Result(){}
	
	public Result(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public Result(boolean success, String msg, Object resultInfo) {
		super();
		this.success = success;
		this.msg = msg;
		this.resultInfo = resultInfo;
	}

	public boolean success;//是否成功
	
	public String msg;//消息描述
	
	public Object resultInfo;//返回内容

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(Object resultInfo) {
		this.resultInfo = resultInfo;
	}
	
}
