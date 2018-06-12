package com.xzcode.pay.core.platforms.alipay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.google.gson.Gson;
import com.xzcode.pay.core.platforms.alipay.config.AlipayConfig;

/**
 * 支付宝 - 支付服务类
 * 
 * 
 * @author zai
 * 2017-08-25
 */
public class AlipayService extends DefaultAlipayClient{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AlipayService.class);
	
	private AlipayConfig alipayConfig;
	
	
	private Gson gson = new Gson();
	
	public AlipayService(AlipayConfig alipayConfig) {
		super(	
				alipayConfig.getServerUrl(), 
				alipayConfig.getAppId(), 
				alipayConfig.getAppPrivateKey(), 
				alipayConfig.getFormat(), 
				alipayConfig.getCharset(), 
				alipayConfig.getAlipayPublicKey(), 
				alipayConfig.getSignType()
				
				);
		this.alipayConfig = alipayConfig;
	}

	
	
	/*private AlipayClient alipayClient;
	public void	init() {
		//实例化客户端
		//alipayClient只需要初始化一次，后续调用不同的API都可以使用同一个alipayClient对象。
		this.alipayClient = new DefaultAlipayClient(
		this.alipayConfig.getServerUrl(), 
		this.alipayConfig.getAppId(), 
		this.alipayConfig.getAppPrivateKey(), 
		this.alipayConfig.getFormat(), 
		this.alipayConfig.getCharset(), 
		this.alipayConfig.getAlipayPublicKey(), 
		this.alipayConfig.getSignType());
		
		
	}*/
	
	
	/*@Override
	public AlipayResponse execute(AlipayRequest<?> request) throws AlipayApiException {
		
		
		if (LOGGER.isDebugEnabled()) {
			AlipayResponse response = super.execute(request); 
			LOGGER.debug("\nAlipay response:\n", gson.toJson(response));
			return response;
		}
		return super.execute(request); 
		
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AlipayResponse> T execute(AlipayRequest<T> request) throws AlipayApiException {
		if (LOGGER.isDebugEnabled()) {
			AlipayResponse response = super.execute(request); 
			LOGGER.debug("\nAlipay response:\n", gson.toJson(response));
			return (T) response;
		}
		return super.execute(request);
	}
	
	

	
	public AlipayConfig getAlipayConfig() {
		return alipayConfig;
	}

	

	
	
}
