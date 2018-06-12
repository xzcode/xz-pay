package com.xzcode.pay.core.platforms.weixin.config;


/**
 * 微信支付配置
 * 
 * 
 * @author zai
 * 2017-08-25
 */
public class WxpayConfig {
	
	
	/**
	 * 微信开放平台审核通过的应用APPID
	 */
	private String appId;
	
	/**
	 * 微信支付分配的商户号
	 */
	private String mchId;
	
	/**
	 * key为商户平台设置的密钥key
	 */
	private String appSecret;
	
	/**
	 * 支付宝支付回调url
	 */
	private String payNotifyUrl;
	
	/**
	 * 支付宝退款回调url
	 */
	private String refundNotifyUrl;
	
	

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	
	public String getAppSecret() {
		return appSecret;
	}
	
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getPayNotifyUrl() {
		return payNotifyUrl;
	}

	public void setPayNotifyUrl(String payNotifyUrl) {
		this.payNotifyUrl = payNotifyUrl;
	}

	public String getRefundNotifyUrl() {
		return refundNotifyUrl;
	}

	public void setRefundNotifyUrl(String refundNotifyUrl) {
		this.refundNotifyUrl = refundNotifyUrl;
	}
	
	
	
	
}
