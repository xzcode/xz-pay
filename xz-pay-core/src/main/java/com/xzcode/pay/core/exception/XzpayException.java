package com.xzcode.pay.core.exception;

/**
 * 统一支付异常包装类
 * 
 * 
 * @author zai
 * 2017-08-25
 */
public class XzpayException extends RuntimeException{

	private static final long serialVersionUID = -5994659045770630085L;

	public XzpayException() {
		super();
	}

	public XzpayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XzpayException(String message, Throwable cause) {
		super(message, cause);
	}

	public XzpayException(String message) {
		super(message);
	}

	public XzpayException(Throwable cause) {
		super(cause);
	}
	
	
}
