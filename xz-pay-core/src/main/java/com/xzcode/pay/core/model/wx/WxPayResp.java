package com.xzcode.pay.core.model.wx;

/**
 * 微信支付返回数据
 * 
 * @author zai
 * 2017-11-27
 */
public class WxPayResp {
	
	/**
	 * 微信返回的支付交易会话ID
	 */
	private String prepayid;
	
	/**
	 * 随机字符串，不长于32位。
	 */
	private String noncestr;
	
	/**
	 * 时间戳
	 */
	private String timestamp;
	
	/**
	 * 签名
	 */
	private String sign;
	

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	

}
