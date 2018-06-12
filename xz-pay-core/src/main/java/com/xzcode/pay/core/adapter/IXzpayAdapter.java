package com.xzcode.pay.core.adapter;

import com.xzcode.pay.core.model.XzpayCloseRequest;
import com.xzcode.pay.core.model.XzpayCloseResponse;
import com.xzcode.pay.core.model.XzpayPayRequest;
import com.xzcode.pay.core.model.XzpayPayResponse;
import com.xzcode.pay.core.model.XzpayQueryRequest;
import com.xzcode.pay.core.model.XzpayQueryResponse;
import com.xzcode.pay.core.model.XzpayRefundRequest;
import com.xzcode.pay.core.model.XzpayRefundResponse;

/**
 * 支付渠道适 -支付- 配器 接口
 * 
 * 
 * @author zai
 * 2017-08-25
 */
public interface IXzpayAdapter {
	
	/***
	 * 支付
	 * @param form
	 * @return
	 * @throws Exception
	 * 
	 * @author zai
	 * 2017-11-03
	 */
	XzpayPayResponse pay(XzpayPayRequest request);
	
	/**
	 * 查询订单
	 * @param form
	 * @return
	 * @throws Exception
	 * 
	 * @author zai
	 * 2017-11-03
	 */
	XzpayQueryResponse query(XzpayQueryRequest request);
	
	/**
	 * 退款
	 * @param form
	 * @return
	 * @throws Exception
	 * 
	 * @author zai
	 * 2017-11-03
	 */
	XzpayRefundResponse refund(XzpayRefundRequest request);
	
	/**
	 * 关闭订单
	 * @param form
	 * @return
	 * @throws Exception
	 * 
	 * @author zai
	 * 2017-11-03
	 */
	XzpayCloseResponse close(XzpayCloseRequest request);

	
}
