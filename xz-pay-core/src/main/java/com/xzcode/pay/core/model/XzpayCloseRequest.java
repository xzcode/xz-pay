package com.xzcode.pay.core.model;

/**
 * 订单关闭表单
 * 
 * 
 * @author zai
 * 2017-11-03
 */
public class XzpayCloseRequest {
	
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	//商户订单号
	private String outTradeNo;
	
	

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	
	public String getPayChannel() {
		return payChannel;
	}
	
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	
	
	
	
}
