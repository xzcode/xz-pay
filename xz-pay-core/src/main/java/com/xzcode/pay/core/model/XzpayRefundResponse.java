package com.xzcode.pay.core.model;

/**
 * 订单支付表单
 * 
 * 
 * @author zai
 * 2017-11-03
 */
public class XzpayRefundResponse extends BaseResponse{
	
	
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
		
	/**
	 * 支付平台订单号
	 */
	private String tradeNo;
	
	/**
	 * 退款金额
	 */
	private long refundFee;
	
	/**
	 * 支付平台退款单号
	 */
	private String refundId;
	
	/**
	 * 退款理由
	 */
	private String refundReason;
	

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

	public long getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(long refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
	public String getRefundReason() {
		return refundReason;
	}
	
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	
	
	
}
