package com.xzcode.pay.core.model;

public class BaseResponse {
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	private boolean success;
	
	private String message;
	
	/**
	 * 返回原始数据体
	 */
	private String body;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getPayChannel() {
		return payChannel;
	}
	
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	

}
