package com.xzcode.pay.shared.model;

import java.math.BigDecimal;

/**
 * 支付表单包装类
 * 
 * @author Joy
 * 2016年1月5日 下午3:04:02
 */
public class ChargeForm {
	
	/**
	 * 本地订单号
	 */
	private String orderNo;
	
	/**
	 * 金额
	 */
	private BigDecimal amount;
	
	/**
	 * 三位 ISO 货币代码，目前仅支持人民币 cny。，支持币种请看@see CurrencyType
	 */
	private String currencyType;
	
	/**
	 * 支付通道，@see PayChannel
	 */
	private String chargeChannel;
	
	/**
	 * 发起支付请求终端的 IP 地址，格式为 IPV4，如: 127.0.0.1
	 */
	private String clientIp;
	
	/**
	 * 商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（upacp/upacp_wap）限制在 32 个字节。
	 */
	private String subject;
	
	/**
	 * 商品的描述信息，该参数最长为 128 个 Unicode 字符，yeepay_wap 对于该参数长度限制为 100 个 Unicode 字符。
	 */
	private String body;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getChargeChannel() {
		return chargeChannel;
	}
	public void setChargeChannel(String chargeChannel) {
		this.chargeChannel = chargeChannel;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
