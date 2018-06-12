package com.xzcode.pay.core.platforms.alipay.config;


/**
 * 支付宝 -- 支付配置类
 * 
 * 
 * @author zai
 * 2017-08-25
 */
public class AlipayConfig {
	
	/**
	 * 支付宝网关（固定）
	 */
	private String serverUrl = "https://openapi.alipay.com/gateway.do";
	
	/**
	 * APPID即创建应用后生成
	 */
	private String appId;
	
	/**
	 * 开发者应用私钥，由开发者自己生成
	 */
	private String appPrivateKey;
	
	/**
	 * 支付宝公钥，由支付宝生成
	 */
	private String alipayPublicKey;
	
	/**
	 * 请求和签名使用的字符编码格式，支持GBK和UTF-8
	 */
	private String charset = "utf-8";
	
	/**
	 * 参数返回格式，只支持json
	 */
	private String format = "json";
	 
	 /**
	  * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2,默认： RSA2
	  */
	private String signType = "RSA2";
	
	/**
	 * 支付宝支付回调url
	 */
	private String payNotifyUrl;
	
	/**
	 * 支付宝退款回调url
	 */
	private String refundNotifyUrl;
	 
	
	
	public String getServerUrl() {
		return serverUrl;
	}
	
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppPrivateKey() {
		return appPrivateKey;
	}

	public void setAppPrivateKey(String appPrivateKey) {
		this.appPrivateKey = appPrivateKey;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}
	
	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	public String getPayNotifyUrl() {
		return payNotifyUrl;
	}
	
	public void setPayNotifyUrl(String payNotifyUrl) {
		this.payNotifyUrl = payNotifyUrl;
	}
	
	public void setRefundNotifyUrl(String refundNotifyUrl) {
		this.refundNotifyUrl = refundNotifyUrl;
	}
	
	public String getRefundNotifyUrl() {
		return refundNotifyUrl;
	}
	
	
}
