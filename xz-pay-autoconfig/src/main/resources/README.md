/*config example*/
sourcemuch:
	commons:
		  pay:
		  #是否开启
		  enabled: true
		  
		  #支付宝网关（固定）
		  alipay_serverUrl: https://openapi.alipay.com/gateway.do
		  
		  #APPID即创建应用后生成
		  alipayAppId: appid_0KubvLC0uvrHHSa9OOOmjTaT
		  
		  #开发者应用私钥，由开发者自己生成
		  #alipayAppPrivateKey: appPrivateKey....
		  
		  
		  #支付宝公钥
		  alipayPublicKey: alipayPublicKey...
		  
		  
		  #请求和签名使用的字符编码格式，支持GBK和UTF-8
		  alipayCharset: utf-8
		  
		  #参数返回格式，只支持json
		  alipayFormat: json
		  
		  #商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2,默认： RSA2
		  alipaySignType: RSA2
		  
		  #支付宝支付回调url
		  alipayPayNotifyUrl: http://alipay_payNotifyUrl
		  
		  #支付宝退款回调url
		  alipayPayNotifyUrl: http://alipay_refundNotifyUrl
		  
		  #微信开放平台审核通过的应用APPID
		  wxAppId: appid_930432io4ji32oj4oi32j4
		  
		  #微信支付分配的商户号
		  wxMchId: 1561156894
		  
		  #key为商户平台设置的密钥key
		  wxAppSecret: sd8f78ds97f89sd7f89sd7f89sd7f
		  
		  #微信支付回调url
		  wxPayNotifyUrl: http://...
		  
		  #微信退款回调url
		  wxRefundNotifyUrl: http://...
		  