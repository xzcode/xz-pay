package com.xzcode.pay.core.model;

/**
 * 订单支付表单
 * 
 * 
 * @author zai
 * 2017-11-03
 */
public class XzpayPayRequest {
	
	/**
	 * 订单号
	 */
	private String outTradeNo;
	
	/**
	 * 订单总金额，单位：分
	 */
	private long amount;
	
	/**
	 * 商品标题
	 */
	private String subject;
	
	/**
	 * 商品内容描述
	 */
	private String body;
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	/**
	 * 订单创建时间戳(北京时间)(必选)
	 */
	private long timeStart;
	
	/**
	 * 订单过期日期时间戳(北京时间)
	 */
	private long timeExpire;
	
	/**
	 * 交易类型，如 APP\WEB\WAP
	 */
	private String tradeType;
	
	
	/**
	 * 请求回调url地址
	 */
	private String notifyUrl;
	
	
	/**
	 * 请求回调url地址(支付宝PC网页支付支持)
	 */
	private String returnUrl;
	
	
	/**
	 * 客户端ip地址
	 */
	private String clientIp;
	
	/**
	 * 设备类型  DickpayConstant.DeviceType (可不传)
	 */
	private String deviceType;
	
	/**
	 * 自定义额外附加参数，长度限制：120，超过后自动截取120字节 
	 * <br>该参数会自动进行UrlEncode，所以回调的时候请进行UrlDecode
	 * <br>(原始限制:微信127，支付宝512)
	 */
	private String extraData;
	


	public String getOutTradeNo() {
		return outTradeNo;
	}


	public void setOutTradeNo(String orderNo) {
		this.outTradeNo = orderNo;
	}


	public long getAmount() {
		return amount;
	}


	public void setAmount(long amount) {
		this.amount = amount;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		if (subject != null && subject.length() >= 32) {
			this.subject = subject.substring(0, 32);
		}else {
			this.subject = subject;			
		}
	}


	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		if (body != null && body.length() > 80) {
			this.body = body.substring(0, 80);
		}else {
			this.body = body;			
		}
	}

	public String getPayChannel() {
		return payChannel;
	}


	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}


	public long getTimeExpire() {
		return timeExpire;
	}


	public void setTimeExpire(long timeExpire) {
		this.timeExpire = timeExpire;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public String getNotifyUrl() {
		return notifyUrl;
	}


	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}


	public String getClientIp() {
		return clientIp;
	}


	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public String getReturnUrl() {
		return returnUrl;
	}
	
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	public long getTimeStart() {
		return timeStart;
	}
	
	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getDeviceType() {
		return deviceType;
	}
	
	public String getExtraData() {
		return extraData;
	}
	
	public void setExtraData(String extraData) {
		if (extraData != null && extraData.length() > 120) {
			this.extraData = extraData.substring(0, 120);
		}else {
			this.extraData = extraData;			
		}
	}
	
}
