package com.xzcode.pay.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xzcode.pay.core.XzpayService;
import com.xzcode.pay.core.adapter.impl.AlipayAdapter;
import com.xzcode.pay.core.adapter.impl.WxpayAdapter;
import com.xzcode.pay.core.config.XzPayConfig;
import com.xzcode.pay.core.platforms.alipay.config.AlipayConfig;
import com.xzcode.pay.core.platforms.weixin.config.WxpayConfig;
import com.xzcode.pay.core.platforms.weixin.service.impl.WxpayService;

/**
 * 
 * xz支付 spring boot自动配置
 * 
 * @author zai
 * 2018-06-12 19:29:46
 */
@Configuration
@ConditionalOnProperty(prefix = XzpayAutoConfig.PROP_PREFIX, name="enabled", havingValue = "true")
@ConditionalOnMissingBean({XzpayService.class})
public class XzpayAutoConfig {
	
	
	protected static final String PROP_PREFIX = "com.xzcode.pay";
	
	/**
	 * 实例化xz支付服务
	 * @return
	 * 
	 * @author zai
	 * 2018-06-12 19:37:22
	 */
	@Bean
	public XzpayService xzpayService() {
		
		XzPayConfig config = xzPayConfig();
		
		AlipayConfig alipayConfig = alipayConfig();
		WxpayConfig wxpayConfig = wxpayConfig();
		
		//初始化支付宝支付适配器
		AlipayAdapter alipayAdapter = new AlipayAdapter(alipayConfig);
		
		//初始化微信支付服务
		WxpayService wxpayService = new WxpayService(wxpayConfig);
		
		//初始化微信支付适配器
		WxpayAdapter wxpayAdapter = new WxpayAdapter(wxpayService);
		
		//初始化xz整合支付服务
		XzpayService xzpayService = new XzpayService(config);
		
		xzpayService.setAlipayAdapter(alipayAdapter);
		xzpayService.setWxpayAdapter(wxpayAdapter);
		
		return xzpayService;
	}
	
	/**
	 * 读取支付配置
	 * @return
	 * 
	 * @author zai
	 * 2018-06-12 19:38:14
	 */
	@Bean
	@ConfigurationProperties(prefix = XzpayAutoConfig.PROP_PREFIX)
	public XzPayConfig xzPayConfig() {
		return new XzPayConfig();
	}
	
	/**
	 * 获取支付宝配置
	 * @return
	 * 
	 * @author zai
	 * 2018-06-12 19:35:17
	 */
	@Bean
	public AlipayConfig alipayConfig() {
		XzPayConfig xzPayConfig = xzPayConfig();
		AlipayConfig alipayConfig = new AlipayConfig();
		
		alipayConfig.setServerUrl(xzPayConfig.getAlipayServerUrl());
		alipayConfig.setAppId(xzPayConfig.getAlipayAppId());
		alipayConfig.setAlipayPublicKey(xzPayConfig.getAlipayPublicKey());
		alipayConfig.setAppPrivateKey(xzPayConfig.getAlipayAppPrivateKey());
		alipayConfig.setCharset(xzPayConfig.getAlipayCharset());
		alipayConfig.setFormat(xzPayConfig.getAlipayFormat());
		alipayConfig.setSignType(xzPayConfig.getAlipaySignType());
		
		alipayConfig.setPayNotifyUrl(xzPayConfig.getAlipayPayNotifyUrl());
		alipayConfig.setRefundNotifyUrl(xzPayConfig.getAlipayRefundNotifyUrl());
		
		return alipayConfig;
	}
	
	/**
	 * 获取微信支付配置
	 * @return
	 * 
	 * @author zai
	 * 2018-06-12 19:35:23
	 */
	@Bean
	public WxpayConfig wxpayConfig() {
		
		XzPayConfig xzPayConfig = xzPayConfig();
		WxpayConfig wxpayConfig = new WxpayConfig();
		
		wxpayConfig.setAppId(xzPayConfig.getWxAppId());
		
		wxpayConfig.setAppSecret(xzPayConfig.getWxAppSecret());
		
		wxpayConfig.setMchId(xzPayConfig.getWxMchId());
		
		wxpayConfig.setPayNotifyUrl(xzPayConfig.getWxPayNotifyUrl());
		
		wxpayConfig.setRefundNotifyUrl(xzPayConfig.getWxRefundNotifyUrl());
		
		return wxpayConfig;
	}
	
	

}
