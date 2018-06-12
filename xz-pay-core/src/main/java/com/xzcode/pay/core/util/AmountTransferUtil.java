package com.xzcode.pay.core.util;

import java.math.BigDecimal;

/**
 * 支付金额转换
 * 
 * 
 * @author zai
 * 2017-08-30
 */
public class AmountTransferUtil {
	
	public static final BigDecimal MULTIPLE = new BigDecimal("100");
	
	/**
	 * 转换为分
	 * @param amount
	 * @return
	 * 
	 * @author zai
	 * 2017-08-30
	 */
	public static int toCent(BigDecimal amount){
		amount = amount.multiply(MULTIPLE);
		return amount.intValue();
	};
	
	/**
	 * 
	 * 转换为元
	 * @param pingppAmount
	 * @return
	 * 
	 * @author zai
	 * 2017-08-30
	 */
	public static BigDecimal toYuan(long amount){
		BigDecimal decimal = new BigDecimal(amount);
		decimal = decimal.divide(MULTIPLE);
		return decimal;
	};
	
}
