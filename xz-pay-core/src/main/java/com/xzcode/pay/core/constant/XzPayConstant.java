package com.xzcode.pay.core.constant;

public interface XzPayConstant {
	
	
	
	/**
	 * 设备类型
	 * 
	 * 
	 * @author zai
	 * 2017-11-28
	 */
	interface DeviceType{
		
		String ANDROID = "ANDROID";
		
		String IOS = "IOS";
		
		String WEB = "WEB";
		
		String WAP = "WAP";
		
	}
	
	/**
	 * 
	 * 交易支付类型
	 * 
	 * @author zai
	 * 2017-08-25
	 */
	interface TradeType{
		
		String APP = "APP";
		
		String WAP = "WAP";
		
		String WEB = "WEB";
		
	}
	
	/**
	 * 支付渠道
	 * 
	 * 
	 * @author zai
	 * 2017-08-25
	 */
	interface PayChannel{
		
		String ALIPAY = "alipay";
		
		String WEIXIN = "weixin";
		
	}
	
	
	/**
	 * 订单交易状态
	 * 
	 * 
	 * @author zai
	 * 2017-11-06
	 */
	interface TradeStatus{
		/**
		 * 支付成功
		 */
		String SUCCESS = "SUCCESS";
		
		/**
		 * 支付中
		 */
		String PAYING = "PAYING";
		
		/**
		 * 已关闭, 未付款交易超时关闭，或支付完成后全额退款
		 */
		String CLOSED = "CLOSED";
		
		/**
		 * 交易结束
		 */
		String FINISHED = "FINISHED";
		
		/**
		 * 出错
		 */
		String ERROR = "ERROR";
		
	}

}
