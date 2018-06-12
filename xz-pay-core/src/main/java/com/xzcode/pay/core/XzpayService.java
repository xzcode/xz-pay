package com.xzcode.pay.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xzcode.pay.core.adapter.IXzpayAdapter;
import com.xzcode.pay.core.config.XzPayConfig;
import com.xzcode.pay.core.constant.XzPayConstant;
import com.xzcode.pay.core.exception.XzpayException;
import com.xzcode.pay.core.model.XzpayCloseRequest;
import com.xzcode.pay.core.model.XzpayCloseResponse;
import com.xzcode.pay.core.model.XzpayNotification;
import com.xzcode.pay.core.model.XzpayPayRequest;
import com.xzcode.pay.core.model.XzpayPayResponse;
import com.xzcode.pay.core.model.XzpayQueryRequest;
import com.xzcode.pay.core.model.XzpayQueryResponse;
import com.xzcode.pay.core.model.XzpayRefundRequest;
import com.xzcode.pay.core.model.XzpayRefundResponse;
import com.xzcode.pay.core.platforms.weixin.WxUtil;
import com.xzcode.pay.core.util.AmountTransferUtil;

/**
 * 聚合支付服务类
 * 
 * 
 * @author zai
 * 2017-08-25
 */
public class XzpayService {
	
	private static final Logger logger = LoggerFactory.getLogger(XzpayService.class);
	
	private XzPayConfig xzPayConfig;
	
	private IXzpayAdapter alipayAdapter;
	
	private IXzpayAdapter wxpayAdapter;
	
	private Gson gson = new GsonBuilder().create();

	public XzpayService(XzPayConfig xzPayConfig) {
		this.xzPayConfig = xzPayConfig;
	}
	
	
	public XzpayPayResponse pay(XzpayPayRequest request) {
		
		if (request.getAmount() == 0L) {
			throw new XzpayException("Order amount must not be 0 !");
		}
		
		XzpayPayResponse payResponse = null;
		
		switch (request.getPayChannel()) {
		
		case XzPayConstant.PayChannel.ALIPAY:
			
			payResponse = this.alipayAdapter.pay(request);
			
			break;
		case XzPayConstant.PayChannel.WEIXIN:
			
			payResponse = this.wxpayAdapter.pay(request);
			
			break;
			
		default:
			
			break;
		}
		
		return payResponse;
		
		
	}
	
	
	
	public XzpayQueryResponse query(XzpayQueryRequest request){
		
		if (request.getTradeNo() == null || request.getOutTradeNo() == null ) {
			throw new XzpayException("Order TradeNo or OutTradeNo must not be null !");
		}
		
		XzpayQueryResponse queryResponse = null;
		
		switch (request.getPayChannel()) {
		
		case XzPayConstant.PayChannel.ALIPAY:
			
			queryResponse = this.alipayAdapter.query(request);
			
			break;
		case XzPayConstant.PayChannel.WEIXIN:
			
			queryResponse = this.wxpayAdapter.query(request);
			
			break;
			
		default:
			
			break;
		}
		
		return queryResponse;
		
	}
	
	
	public XzpayRefundResponse refund(XzpayRefundRequest request){
		
		if (request.getTradeNo() == null && request.getOutTradeNo() == null ) {
			throw new XzpayException("Order TradeNo or OutTradeNo must not be null !");
		}
		
		XzpayRefundResponse refundResponse = null;
		
		switch (request.getPayChannel()) {
		
		case XzPayConstant.PayChannel.ALIPAY:
			
			refundResponse = this.alipayAdapter.refund(request);
			
			break;
		case XzPayConstant.PayChannel.WEIXIN:
			
			refundResponse = this.wxpayAdapter.refund(request);
			
			break;
			
		default:
			
			break;
		}
		
		return refundResponse;
	}
	
	
	public XzpayCloseResponse close(XzpayCloseRequest request){
		
		if (request.getOutTradeNo() == null ) {
			throw new XzpayException(" outTradeNo must not be null !");
		}
		XzpayCloseResponse closeResponse = null;
		
		switch (request.getPayChannel()) {
		
		case XzPayConstant.PayChannel.ALIPAY:
			
			closeResponse = this.alipayAdapter.close(request);
			
			break;
		case XzPayConstant.PayChannel.WEIXIN:
			
			closeResponse = this.wxpayAdapter.close(request);
			
			break;
			
		default:
			
			break;
		}
		
		return closeResponse;
	}
	
	
	
	/**
	 * 支付宝 request 参数转  map 
	 * @param parameterMap
	 * @return 
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public Map<String, String> alipayParameterMapTransfer(Map<String, String[]> parameterMap) {
		
		Map<String, String> params = new TreeMap<>();

		for (String key : parameterMap.keySet()) {
			
			String[] vals = parameterMap.get(key);

			if (vals != null && vals.length > 0) {
				
				params.put(key, StringUtils.join(vals, ","));
				
			} else {
				
				params.put(key, "");
				
			}
			
		}
		/*
		Map<String,String> params = new HashMap<String,String>();
		for (Iterator<String> iter = parameterMap.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) parameterMap.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		*/
		return params;
	}
	
	/**
	 * 获取支付宝 支付-异步回调通知
	 * @param parameterMap
	 * @return
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public XzpayNotification alipayGetPayNotification(Map<String, String[]> parameterMap){
		
		if (logger.isDebugEnabled()) {
			logger.debug("Alipay pay notifiction:{}", this.gson.toJson(parameterMap));
		}
		
		XzpayNotification notification = new XzpayNotification();
		Map<String, String> params = alipayParameterMapTransfer(parameterMap);
		boolean rsaCheckV1 = this.alipaySignatureRsaCheckV1(params);
		notification.setSignatureVerifySuccess(rsaCheckV1);
		if (!rsaCheckV1) {
			return notification;
		}
		String tradeStatus = params.get("trade_status");
		if ("TRADE_SUCCESS".equals(tradeStatus)) {
			notification.setTradeSuccess(true);
		}
		
		String outTradeNo = params.get("out_trade_no");
		notification.setOutTradeNo(outTradeNo);
		
		String tradeNo = params.get("trade_no");
		notification.setTradeNo(tradeNo);
		
		String totalAmount = params.get("total_amount");
		notification.setAmount(AmountTransferUtil.toCent(new BigDecimal(totalAmount)));
		
		String extraData = params.get("passback_params");
		notification.setExtraData(extraData);
		
		notification.setPayChannel(XzPayConstant.PayChannel.ALIPAY);
		
		notification.setPlatformDefaultCallback("success");
		
		return notification;
	}
	
	
	public XzpayNotification weixinGetPayNotification(InputStream inputStream){
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		try{
			return weixinGetPayNotification(new BufferedReader(inputStreamReader));
		} catch (Exception e) {
			throw new XzpayException("weixinGetPayNotification ERROR!", e);
		}
	}
	
	public XzpayNotification weixinGetPayNotification(BufferedReader reader){
		StringBuilder sb = new StringBuilder();
		String readLine = null;
		try {
			
			while ((readLine = reader.readLine()) != null) {
				sb.append(readLine);
			}
		
			String body = sb.toString();
			return weixinGetPayNotification(body);
    	
		} catch (Exception e) {
			throw new XzpayException("WeixinGetPayNotification ERROR!", e);
		}
	}
	
	
	/**
	 * 获取微信 支付-异步回调通知
	 * @param parameterMap
	 * @return
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public XzpayNotification weixinGetPayNotification(String xmlBody){
		
		if (logger.isDebugEnabled()) {
			logger.debug("Weixin pay notifiction:{}", xmlBody);
		}
		
		XzpayNotification notification = new XzpayNotification();
		
		notification.setPayChannel(XzPayConstant.PayChannel.WEIXIN);
		
		//微信支付通知默认响应
		notification.setPlatformDefaultCallback("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
		
		
		Map<String, String> params = weixinParameterMapTransfer(xmlBody);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Weixin pay notifiction params:{}", params);
		}
		
		boolean signatureCheck = this.weixinSignatureMd5Check(params);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Weixin pay notifiction signatureCheck:{}", signatureCheck);
		}
		
		notification.setSignatureVerifySuccess(signatureCheck);
		
		if (!signatureCheck) {
			if (logger.isDebugEnabled()) {
				logger.debug("Weixin pay notifiction return:{}", gson.toJson(notification));
			}
			return notification;
		}
		String tradeStatus = params.get("result_code");
		if ("SUCCESS".equals(tradeStatus)) {
			notification.setTradeSuccess(true);
		}
		
		String outTradeNo = params.get("out_trade_no");
		notification.setOutTradeNo(outTradeNo);
		
		String tradeNo = params.get("transaction_id");
		notification.setTradeNo(tradeNo);
		
		String totalAmount = params.get("total_fee");
		notification.setAmount(AmountTransferUtil.toCent(new BigDecimal(totalAmount)));
		
		String extraData = params.get("attach");
		notification.setExtraData(extraData);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Weixin pay notifiction return:{}", gson.toJson(notification));
		}
		
		return notification;
	}
	
	/**
	 * 支付宝签名RSA2 V1 验证
	 * @param params
	 * @return
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public boolean alipaySignatureRsaCheckV1(Map<String, String> params){
		try {
			return AlipaySignature.rsaCheckV1(params, this.xzPayConfig.getAlipayPublicKey() , this.xzPayConfig.getAlipayCharset(), this.xzPayConfig.getAlipaySignType());
		} catch (AlipayApiException e) {
			throw new XzpayException("AlipaySignatureRsaCheckV1 ERROR!", e);
		}
	}
	
	/**
	 * 支付宝签名RSA2 V2 验证
	 * @param params
	 * @return
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public boolean AlipaySignatureRsaCheckV2 (Map<String, String> params){
		try {
			return AlipaySignature.rsaCheckV2(params, this.xzPayConfig.getAlipayPublicKey() , this.xzPayConfig.getAlipayCharset(), "RSA2");
		} catch (AlipayApiException e) {
			throw new XzpayException("AlipaySignatureRsaCheckV2 ERROR!", e);
		}
	}
	
	
	
	
	/**
	 * 微信 request body 参数转  map 
	 * @param parameterMap
	 * @return 
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public Map<String, String> weixinParameterMapTransfer(String body) {
		Map<String, String> params = WxUtil.fromXml(body);
		return params;
	}
	
	/**
	 * 检测 微信 返回状态码 是否为 ： 成功
	 * @param params
	 * @return
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public boolean weixinCheckReturnSuccess (Map<String, String> params){
		return "SUCCESS".equals(params.get("return_code"));
	}
	
	public boolean weixinSignatureMd5Check (Map<String, String> params){
		
		//移除sign用于签名验证
		String sign = params.remove("sign");
		
		String paramString = WxUtil.getParamString2((TreeMap<String, String>) params);
		
		//把sign存回去
		params.put("sign", sign);
		
		paramString += "&key=" + xzPayConfig.getWxAppSecret();
		
		if (logger.isDebugEnabled()) {
			logger.debug("Weixin pay notifiction weixinSignatureMd5Check, sign:{}, paramString:{}", sign, paramString);
		}
		
		String signMd5 = WxUtil.signMd5(paramString);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Weixin pay notifiction weixinSignatureMd5Check, sign:{}, checkSign:{}", sign, signMd5);
		}
		
		if (sign.equals(signMd5)) {
			return true;
		}
		
		return false;
	}
	

	public XzPayConfig getDickpayConfig() {
		return xzPayConfig;
	}


	public void setDickpayConfig(XzPayConfig xzPayConfig) {
		this.xzPayConfig = xzPayConfig;
	}


	public IXzpayAdapter getAlipayAdapter() {
		return alipayAdapter;
	}


	public void setAlipayAdapter(IXzpayAdapter alipayChargeAdapter) {
		this.alipayAdapter = alipayChargeAdapter;
	}


	public IXzpayAdapter getWeixinAdapter() {
		return wxpayAdapter;
	}


	public void setWxpayAdapter(IXzpayAdapter wxpayChargeAdapter) {
		this.wxpayAdapter = wxpayChargeAdapter;
	}
	
	
	
	
}
