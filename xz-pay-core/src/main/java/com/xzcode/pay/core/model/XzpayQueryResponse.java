package com.xzcode.pay.core.model;

/**
 * 订单支付表单
 * 
 * 
 * @author zai
 * 2017-11-03
 */
public class XzpayQueryResponse extends BaseResponse{
	
	
	private String tradeNo;
	
	private String outTradeNo;
	
	/**
	 * 订单交易状态
	 */
	private String tradeStatus;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	
	
	
	
}
