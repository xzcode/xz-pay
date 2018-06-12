package com.xzcode.pay.core.model;

/**
 * 支付异步通知包装类
 * 
 * 
 * @author zai
 * 2018-02-06
 */
public class XzpayNotification {
	
	/**
	 * 是否成功返回（微信先验证）
	 */
	private boolean isReturnSuccess;
	
	/**
	 * 签名是否验证通过
	 */
	private boolean isSignatureVerifySuccess;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 返回给支付平台的默认数据体
	 */
	private String platformDefaultCallback;
	
	
	/**
	 * 是否交易成功
	 */
	private boolean isTradeSuccess;
	
	
	/**
	 * 订单号
	 */
	private String outTradeNo;
	
	/**
	 * 支付宝订单号
	 */
	private String tradeNo;
	
	/**
	 * 订单总金额，单位：分
	 */
	private long amount;
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	/**
	 * 交易类型，如 APP\WEB\WAP
	 */
	private String tradeType;
	
	/**
	 * 自定义额外附加参数，长度限制：120，超过后自动截取120字节 
	 * <br>该参数会自动进行UrlEncode，所以回调的时候请进行UrlDecode
	 * <br>(原始限制:微信127，支付宝512)
	 */
	private String extraData;

	public boolean isReturnSuccess() {
		return isReturnSuccess;
	}

	public void setReturnSuccess(boolean isReturnSuccess) {
		this.isReturnSuccess = isReturnSuccess;
	}

	public boolean isSignatureVerifySuccess() {
		return isSignatureVerifySuccess;
	}

	public void setSignatureVerifySuccess(boolean isSignatureVerifySuccess) {
		this.isSignatureVerifySuccess = isSignatureVerifySuccess;
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
	
	public boolean isTradeSuccess() {
		return isTradeSuccess;
	}
	
	public void setTradeSuccess(boolean isTradeSuccess) {
		this.isTradeSuccess = isTradeSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPlatformDefaultCallback() {
		return platformDefaultCallback;
	}
	
	public void setPlatformDefaultCallback(String platformDefaultCallback) {
		this.platformDefaultCallback = platformDefaultCallback;
	}
	
	
	
	
}
