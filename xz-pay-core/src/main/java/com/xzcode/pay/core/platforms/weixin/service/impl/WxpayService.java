package com.xzcode.pay.core.platforms.weixin.service.impl;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.xzcode.pay.core.platforms.weixin.WxUtil;
import com.xzcode.pay.core.platforms.weixin.config.WxpayConfig;
import com.xzcode.pay.core.util.HTTPCommonUtil;

public class WxpayService{
	

	private static final Logger LOGGER = LoggerFactory.getLogger(WxpayService.class);
	
	private WxpayConfig wxpayConfig;
	
	
	private Gson gson = new Gson();
	
	
	
	public WxpayService(WxpayConfig wxpayConfig) {
		this.wxpayConfig = wxpayConfig;
	}
	
	

	/**
	 * 发起微信支付
	 * @param charge
	 * @return
	 * 
	 * @author zai
	 * 2017-08-30
	 */
	public Map<String, String> pay(Map<String, String> params) {
		
		//参数请查看文档: https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1		
		String url= "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		//终端设备号(门店号或收银设备ID)，默认请传"WEB"
		params.put("device_info", "WEB");
		
		//符合ISO 4217标准的三位字母代码，默认人民币：CNY
		params.put("fee_type", "CNY");
		
		//签名方式，默认:MD5
		params.put("sign_type", "MD5");
		
		handleCommonParams(params);
		
		String xml = WxUtil.toXml(params);
		
		String httpBody = HTTPCommonUtil.postRequestBody(url, xml, 20000);
		
		Map<String, String> resultMap = WxUtil.fromXml(httpBody);
		resultMap.put("body", httpBody);
		
		return resultMap;
		
	}
	
	
	public Map<String, String> query(Map<String, String> params) {
		
		
		String url= "https://api.mch.weixin.qq.com/pay/orderquery";
		
		handleCommonParams(params);
		
		String xml = WxUtil.toXml(params);
		
		String httpBody = HTTPCommonUtil.postRequestBody(url, xml, 20000);
		
		Map<String, String> resultMap = WxUtil.fromXml(httpBody);
		resultMap.put("body", httpBody);
		
		return resultMap;
		
	}
	
	public Map<String, String> refund(Map<String, String> params) {
		
		
		String url= "https://api.mch.weixin.qq.com/pay/refundquery";
		
		handleCommonParams(params);
		
		String xml = WxUtil.toXml(params);
		
		String httpBody = HTTPCommonUtil.postRequestBody(url, xml, 20000);
		
		Map<String, String> resultMap = WxUtil.fromXml(httpBody);
		resultMap.put("body", httpBody);
		
		return resultMap;
		
	}
	
	
	public Map<String, String> close(Map<String, String> params) {
		
		
		String url= "https://api.mch.weixin.qq.com/pay/closeorder";
		
		handleCommonParams(params);
		
		
		String xml = WxUtil.toXml(params);
		
		String httpBody = HTTPCommonUtil.postRequestBody(url, xml, 20000);
		
		Map<String, String> resultMap = WxUtil.fromXml(httpBody);
		resultMap.put("body", httpBody);
		
		return resultMap;
		
	}
	
	
	/**
	 * 处理公共参数
	 * @param params
	 * 
	 * @author zai
	 * 2017-11-03
	 */
	private void handleCommonParams(Map<String, String> params) {
		
		//appid
		params.put("appid", this.wxpayConfig.getAppId());
		
		//随机字符串  String(32)
		params.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));
		
		//商户id
		params.put("mch_id", this.wxpayConfig.getMchId());
		
		
		if(params.get("notify_url") == null) {
			params.put("notify_url", this.wxpayConfig.getPayNotifyUrl());
		}
		
		//签名  String(32)
		params.put("sign", this.signMd5(params));
		
	}
	
	
	public String signMd5(Map<String, String> params) {
		StringBuilder temp = new StringBuilder();
		for (String key : params.keySet()) {
			temp.append(key).append("=").append(params.get(key)).append("&");
		}
		//key为商户平台设置的密钥key
		temp.append("key=").append(this.wxpayConfig.getAppSecret());
		
		String sign = DigestUtils.md5Hex(temp.toString()).toUpperCase();
		
		return sign;
	}
	

	

	public WxpayConfig getWeixinConfig() {
		return wxpayConfig;
	}
}
