package com.xzcode.pay.core.adapter.impl;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.google.gson.Gson;
import com.xzcode.pay.core.adapter.IXzpayAdapter;
import com.xzcode.pay.core.constant.XzPayConstant;
import com.xzcode.pay.core.exception.XzpayException;
import com.xzcode.pay.core.model.XzpayCloseRequest;
import com.xzcode.pay.core.model.XzpayCloseResponse;
import com.xzcode.pay.core.model.XzpayPayRequest;
import com.xzcode.pay.core.model.XzpayPayResponse;
import com.xzcode.pay.core.model.XzpayQueryRequest;
import com.xzcode.pay.core.model.XzpayQueryResponse;
import com.xzcode.pay.core.model.XzpayRefundRequest;
import com.xzcode.pay.core.model.XzpayRefundResponse;
import com.xzcode.pay.core.platforms.alipay.config.AlipayConfig;
import com.xzcode.pay.core.platforms.alipay.constant.AlipayCodeConstant;
import com.xzcode.pay.core.util.AmountTransferUtil;

/**
 * 支付宝 适配器
 * 
 * 
 * @author zai
 * 2017-08-30
 */
public class AlipayAdapter implements IXzpayAdapter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AlipayAdapter.class);
	
	private AlipayConfig alipayConfig;
	
	private Gson gson = new Gson();
	
	public void setAlipayConfig(AlipayConfig alipayConfig) {
		this.alipayConfig = alipayConfig;
	}
	
	public AlipayConfig getAlipayConfig() {
		return alipayConfig;
	}
	
	private AlipayClient alipayClient;
	


	public AlipayAdapter(AlipayConfig alipayConfig) {
		super();
		this.alipayConfig = alipayConfig;
		this.alipayClient = new DefaultAlipayClient(
				alipayConfig.getServerUrl(), 
				alipayConfig.getAppId(), 
				alipayConfig.getAppPrivateKey(), 
				alipayConfig.getFormat(), 
				alipayConfig.getCharset(), 
				alipayConfig.getAlipayPublicKey(), 
				alipayConfig.getSignType()
			);
	}
	
	/**
	 * app支付
	 * @param payRequest
	 * @return
	 * @throws Exception
	 * 
	 * @author zai
	 * 2018-02-07
	 */
	public XzpayPayResponse payApp(XzpayPayRequest payRequest){
		
		AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
		
		alipayRequest.setReturnUrl(payRequest.getReturnUrl());
		alipayRequest.setNotifyUrl(payRequest.getNotifyUrl());
		
		
		String bizContent = getPayBizContent(payRequest,"QUICK_MSECURITY_PAY");
		
		alipayRequest.setBizContent(bizContent);
		try {
		
			//对应 APP 执行支付 - //这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = this.alipayClient.sdkExecute(alipayRequest);
			
			
			
			XzpayPayResponse payResponse = getPayResopnse(
					response.getBody(), 
					response.getOutTradeNo(), 
					response.getTotalAmount(), 
					response.getCode(), 
					response.getSubMsg()
					);
			
			
			return payResponse;
		
		} catch (AlipayApiException e) {
			throw new XzpayException("Alipay App Pay Error!", e);
		}
		
	}

	/**
	 * 网页支付
	 * @param payRequest
	 * @return
	 * @throws Exception
	 * 
	 * @author zai
	 * 2018-02-07
	 */
	public XzpayPayResponse payWeb(XzpayPayRequest payRequest) {
		
		try {
			AlipayTradePagePayRequest alipayRequest= new AlipayTradePagePayRequest();
			
			alipayRequest.setReturnUrl(payRequest.getReturnUrl());
			alipayRequest.setNotifyUrl(payRequest.getNotifyUrl());
			
			// !!! 此参数必须
			String bizContent = getPayBizContent(payRequest, "FAST_INSTANT_TRADE_PAY");
			
			alipayRequest.setBizContent(bizContent);
			
			//对应 WEB 执行支付 - 使用的是pageExecute
			AlipayTradePagePayResponse response = this.alipayClient.pageExecute(alipayRequest);
			
			XzpayPayResponse payResponse = getPayResopnse(
					response.getBody(), 
					response.getOutTradeNo(), 
					response.getTotalAmount(), 
					response.getCode(), 
					response.getSubMsg()
					);
			
			return payResponse;
			
		} catch (AlipayApiException e) {
			throw new XzpayException("Alipay Web Pay Error!", e);
		}
		
	}

	/**
	 * 手机网站支付
	 * @param payRequest
	 * @return
	 * 
	 * @author zai
	 * 2018-02-07
	 */
	public XzpayPayResponse payWap(XzpayPayRequest payRequest){
		
		 AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		
		alipayRequest.setReturnUrl(payRequest.getReturnUrl());
		alipayRequest.setNotifyUrl(payRequest.getNotifyUrl());
		
		try {
			String bizContent = getPayBizContent(payRequest, "QUICK_WAP_PAY");
			
			alipayRequest.setBizContent(bizContent);
			
			//对应 WAP 执行支付 - 使用的是pageExecute
			AlipayTradeWapPayResponse response;
				response = this.alipayClient.pageExecute(alipayRequest);
			
			XzpayPayResponse payResponse = getPayResopnse(
					response.getBody(), 
					response.getOutTradeNo(), 
					response.getTotalAmount(), 
					response.getCode(), 
					response.getSubMsg()
					);
			
			return payResponse;
		} catch (AlipayApiException e) {
			throw new XzpayException("Alipay Wap Pay Error!", e);
		}
	}
	
	
	private XzpayPayResponse getPayResopnse(
			String body, 
			String outTradeNo,
			String totalAmount,
			String code, 
			String subMsg
			) {
		//String body = response.getBody();
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("\nAlipay charge respone body string:\n{}", body);
		}
		
		XzpayPayResponse payResponse = new XzpayPayResponse();
		
		payResponse.setPayChannel(XzPayConstant.PayChannel.ALIPAY);
		
		payResponse.setBody(body);
		
		if (AlipayCodeConstant.SUCCESS.equals(code)) {
			payResponse.setSuccess(true);
		}else {
			payResponse.setSuccess(false);
			payResponse.setMessage(subMsg);
			return payResponse;
		}
		
		//payResponse.setTradeNo(response.getTradeNo());
		
		payResponse.setOutTradeNo(outTradeNo);
		payResponse.setTotalAmount(AmountTransferUtil.toCent(new BigDecimal(totalAmount)));
		return payResponse;
	}
	

	@Override
	public XzpayPayResponse pay(XzpayPayRequest payRequest){
		
		if (payRequest.getNotifyUrl() == null) {
			payRequest.setNotifyUrl(this.alipayConfig.getPayNotifyUrl());
		}
		
		if (XzPayConstant.TradeType.APP.equals(payRequest.getTradeType())) {
			
			return payApp(payRequest);
			
		}else if (XzPayConstant.TradeType.WEB.equals(payRequest.getTradeType())) {
			
			return payWeb(payRequest);
			
		}else if (XzPayConstant.TradeType.WAP.equals(payRequest.getTradeType())) {
			return payWap(payRequest);
			
		}else {
			throw new XzpayException("Unkonw TradeType!");
		}
		
	}
	
	private String getPayBizContent(XzpayPayRequest payRequest, String productCode) {
		
		Map<String, Object> bizContent = new LinkedHashMap<>();
		
		bizContent.put("subject", payRequest.getSubject());
		
		bizContent.put("body", payRequest.getBody());
		
		bizContent.put("out_trade_no", payRequest.getOutTradeNo());
		
		bizContent.put("product_code", productCode);
		
		try {
		
			/**
			 * 附加参数
			 */
			if (payRequest.getExtraData() != null) {
				bizContent.put("passback_params", URLEncoder.encode(payRequest.getExtraData(),"utf-8"));			
			}
			
			if (payRequest.getTimeExpire() != 0L) {
				
				if (payRequest.getTimeStart() == 0L) {
					throw new XzpayException("If TimeExpire is not 0L, TimeStart cant not be 0L !");
				}
				
				long expireTime = payRequest.getTimeStart() - payRequest.getTimeExpire();
				if (expireTime <= 0L) {
					throw new XzpayException("Dickpay TimeStart > TimeExpire is not allowed!");
				}
				expireTime = expireTime / (60 * 1000) ;
				bizContent.put("timeout_express", expireTime + "m");
			}
			
			//TODO 需要对金额进行转换
			bizContent.put("total_amount", AmountTransferUtil.toYuan(payRequest.getAmount()));
			
			String bizConStr = gson.toJson(bizContent);
			
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("\nAlipay bizContent:\n{}", bizConStr);
			}
			
			return bizConStr;
		
		} catch (Exception e) {
			throw new XzpayException(e);
		}
	}



	@Override
	public XzpayQueryResponse query(XzpayQueryRequest form) {
		//AlipayConfig config = alipayService.getAlipayConfig();
		try {
			
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			
			Map<String, Object> bizContent = new LinkedHashMap<>();
			
			bizContent.put("out_trade_no", form.getOutTradeNo());
			
			bizContent.put("trade_no", form.getTradeNo());
			
			request.setBizContent(gson.toJson(bizContent));
			
			AlipayTradeQueryResponse response = this.alipayClient.execute(request);
			
			String body = response.getBody();
			
			XzpayQueryResponse queryResponse = new XzpayQueryResponse();
			
			queryResponse.setPayChannel(XzPayConstant.PayChannel.ALIPAY);
			
			queryResponse.setBody(body);
			
			if (AlipayCodeConstant.SUCCESS.equals(response.getCode())) {
				queryResponse.setSuccess(true);
			}else {
				queryResponse.setSuccess(false);
				queryResponse.setMessage(response.getSubMsg());
				return queryResponse;
			}
			
			return queryResponse;
		} catch (AlipayApiException e) {
			throw new XzpayException("Alipay Trade Query Error!", e);
		}
	}



	@Override
	public XzpayRefundResponse refund(XzpayRefundRequest form) {
		
		try {
			
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			
			/*request.setNotifyUrl(form.getRefundNotifyUrl());				
			if (null == form.getRefundNotifyUrl()) {
				request.setNotifyUrl(alipayConfig.getRefundNotifyUrl());
			}*/
			
			
			Map<String, Object> bizContent = new LinkedHashMap<>();
			
			bizContent.put("out_trade_no", form.getOutTradeNo());
			
			//bizContent.put("trade_no", form.getTradeNo());
			
			bizContent.put("refund_amount", AmountTransferUtil.toYuan(form.getRefundAmount()));
			
			bizContent.put("out_request_no", form.getOutRefundNo());
			
			bizContent.put("refund_reason", form.getRefundReason());
			
			request.setBizContent(gson.toJson(bizContent));
			
			
			AlipayTradeRefundResponse response = this.alipayClient.execute(request);
			
			XzpayRefundResponse refundResponse = new XzpayRefundResponse();
			
			refundResponse.setPayChannel(XzPayConstant.PayChannel.ALIPAY);
			
			String body = response.getBody();
			
			refundResponse.setBody(body);
			
			if (AlipayCodeConstant.SUCCESS.equals(response.getCode())) {
				refundResponse.setSuccess(true);
			}else {
				refundResponse.setSuccess(false);
				refundResponse.setMessage(response.getSubMsg());
				return refundResponse;
			}
			
			refundResponse.setRefundFee(AmountTransferUtil.toCent(new BigDecimal(response.getRefundFee())));
			
			refundResponse.setOutTradeNo(response.getOutTradeNo());
			
			refundResponse.setTradeNo(response.getTradeNo());
			
			return refundResponse;
		
		} catch (AlipayApiException e) {
			throw new XzpayException("Alipay Trade Refund Error!", e);
		}
	}



	@Override
	public XzpayCloseResponse close(XzpayCloseRequest request) {
		try {
			AlipayTradeCloseRequest closeRequest = new AlipayTradeCloseRequest();
			
			Map<String, Object> bizContent = new LinkedHashMap<>();
			
			bizContent.put("out_trade_no", request.getOutTradeNo());
			
			closeRequest.setBizContent(gson.toJson(bizContent));
			
			AlipayTradeCloseResponse response = this.alipayClient.execute(closeRequest);
			
			XzpayCloseResponse closeResponse = new XzpayCloseResponse();
			
			closeResponse.setPayChannel(XzPayConstant.PayChannel.ALIPAY);
			
			closeResponse.setBody(response.getBody());
			
			if (AlipayCodeConstant.SUCCESS.equals(response.getCode())) {
				closeResponse.setSuccess(true);
			}else {
				closeResponse.setSuccess(false);
				closeResponse.setMessage(response.getSubMsg());
				return closeResponse;
			}
			
			return closeResponse;
		
		} catch (AlipayApiException e) {
			throw new XzpayException("Alipay Trade Close Error!", e);
		}
	}
	
	public AlipayClient getAlipayClient() {
		return alipayClient;
	}
}
