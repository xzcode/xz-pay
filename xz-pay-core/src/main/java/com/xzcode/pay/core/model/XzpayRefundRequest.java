package com.xzcode.pay.core.model;

/**
 * 订单退款表单
 * 
 * 
 * @author zai
 * 2017-11-03
 */
public class XzpayRefundRequest {
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
	
	/**
	 * 支付平台订单号
	 */
	private String tradeNo;
	
	/**
	 * 商户提供的退款订单号-- 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	 */
	private String outRefundNo;
	
	/**
	 * 退款金额
	 */
	private long totalAmount;
	
	
	/**
	 * 退款金额
	 */
	private long refundAmount;
	
	
	/**
	 * 退款理由
	 */
	private String refundReason;
	
	/**
	 * 退款异步通知url
	 */
	//private String refundNotifyUrl;
	
	/**
	 * 退款异步通知url
	 * @return
	 * 
	 * @author zai
	 * 2018-02-11
	 */
	/*public String getRefundNotifyUrl() {
		return refundNotifyUrl;
	}*/
	
	/**
	 * 退款异步通知url
	 * @param refundNotifyUrl
	 * 
	 * @author zai
	 * 2018-02-11
	 */
	/*public void setRefundNotifyUrl(String refundNotifyUrl) {
		this.refundNotifyUrl = refundNotifyUrl;
	}*/
	

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

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

	public long getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(long refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getRefundReason() {
		return refundReason;
	}
	
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	
	
	
	
	
}
