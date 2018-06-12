package com.xzcode.pay.core.model;

import com.xzcode.pay.core.model.wx.WxPayResp;

/**
 * 订单支付表单
 * 
 * 
 * @author zai
 * 2017-11-03
 */
public class XzpayPayResponse extends BaseResponse{
	
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
		
	/**
	 * 支付平台订单号
	 */
	private String tradeNo;
	
	/**
	 * 总金额
	 */
	private long totalAmount;
	
	/**
	 * 微信支付返回数据
	 */
	private WxPayResp wxPayResp;

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

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public WxPayResp getWxPayResp() {
		return wxPayResp;
	}
	
	public void setWxPayResp(WxPayResp wxPayResp) {
		this.wxPayResp = wxPayResp;
	}
	
}
