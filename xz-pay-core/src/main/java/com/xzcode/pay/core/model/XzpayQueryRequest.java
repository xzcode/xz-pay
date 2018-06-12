package com.xzcode.pay.core.model;

/**
 * 订单查询表单
 * 
 * 
 * @author zai
 * 2017-11-03
 */
public class XzpayQueryRequest {
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	//商户订单号
	private String outTradeNo;
	
	//支付平台订单号
	private String tradeNo;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public String getPayChannel() {
		return payChannel;
	}
	
	
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	
	
	
}
