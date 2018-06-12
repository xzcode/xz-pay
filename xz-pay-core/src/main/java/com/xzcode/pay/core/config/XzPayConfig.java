package com.xzcode.pay.core.config;

/**
 * 聚合支付配置类
 * 
 * 
 * @author zai
 * 2017-08-25
 */
public class XzPayConfig {
	
	/**
	 * 是否开启
	 */
	private boolean enabled;
	
	/**
	 * 支付宝支付回调url
	 */
	private String alipayPayNotifyUrl;
	
	/**
	 * 支付宝退款回调url
	 */
	private String alipayRefundNotifyUrl;
	
	/**
	 * 支付宝网关（固定）
	 */
	private String alipayServerUrl = "https://openapi.alipay.com/gateway.do";
	
	/**
	 * APPID即创建应用后生成
	 */
	private String alipayAppId;
	
	/**
	 * 开发者应用私钥，由开发者自己生成
	 */
	private String alipayAppPrivateKey;
	
	/**
	 * 支付宝公钥，由支付宝生成
	 */
	private String alipayPublicKey;
	
	/**
	 * 请求和签名使用的字符编码格式，支持GBK和UTF-8
	 */
	private String alipayCharset = "utf-8";
	
	/**
	 * 参数返回格式，只支持json
	 */
	private String alipayFormat = "json";
	 
	 /**
	  * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2,默认： RSA2
	  */
	private String alipaySignType = "RSA2";
	
	
	/**
	 * 微信开放平台审核通过的应用APPID
	 */
	private String wxAppId;
	
	/**
	 * 微信支付分配的商户号
	 */
	private String wxMchId;
	
	/**
	 * key为商户平台设置的密钥key
	 */
	private String wxAppSecret;
	
	/**
	 * 微信支付回调url
	 */
	private String wxPayNotifyUrl;
	
	
	/**
	 * 微信退款回调url
	 */
	private String wxRefundNotifyUrl;


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getAlipayPayNotifyUrl() {
		return alipayPayNotifyUrl;
	}


	public void setAlipayPayNotifyUrl(String alipayPayNotifyUrl) {
		this.alipayPayNotifyUrl = alipayPayNotifyUrl;
	}


	public String getAlipayRefundNotifyUrl() {
		return alipayRefundNotifyUrl;
	}


	public void setAlipayRefundNotifyUrl(String alipayRefundNotifyUrl) {
		this.alipayRefundNotifyUrl = alipayRefundNotifyUrl;
	}


	public String getAlipayServerUrl() {
		return alipayServerUrl;
	}


	public void setAlipayServerUrl(String alipayServerUrl) {
		this.alipayServerUrl = alipayServerUrl;
	}


	public String getAlipayAppId() {
		return alipayAppId;
	}


	public void setAlipayAppId(String alipayAppId) {
		this.alipayAppId = alipayAppId;
	}


	public String getAlipayAppPrivateKey() {
		return alipayAppPrivateKey;
	}


	public void setAlipayAppPrivateKey(String alipayAppPrivateKey) {
		this.alipayAppPrivateKey = alipayAppPrivateKey;
	}


	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}
	
	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}


	public String getAlipayCharset() {
		return alipayCharset;
	}


	public void setAlipayCharset(String alipayCharset) {
		this.alipayCharset = alipayCharset;
	}


	public String getAlipayFormat() {
		return alipayFormat;
	}


	public void setAlipayFormat(String alipayFormat) {
		this.alipayFormat = alipayFormat;
	}


	public String getAlipaySignType() {
		return alipaySignType;
	}


	public void setAlipaySignType(String alipaySignType) {
		this.alipaySignType = alipaySignType;
	}


	public String getWxAppId() {
		return wxAppId;
	}


	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}


	public String getWxMchId() {
		return wxMchId;
	}


	public void setWxMchId(String wxMchId) {
		this.wxMchId = wxMchId;
	}


	public String getWxAppSecret() {
		return wxAppSecret;
	}


	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}


	public String getWxPayNotifyUrl() {
		return wxPayNotifyUrl;
	}


	public void setWxPayNotifyUrl(String wxPayNotifyUrl) {
		this.wxPayNotifyUrl = wxPayNotifyUrl;
	}


	public String getWxRefundNotifyUrl() {
		return wxRefundNotifyUrl;
	}


	public void setWxRefundNotifyUrl(String wxRefundNotifyUrl) {
		this.wxRefundNotifyUrl = wxRefundNotifyUrl;
	}

	 
	
	
}
