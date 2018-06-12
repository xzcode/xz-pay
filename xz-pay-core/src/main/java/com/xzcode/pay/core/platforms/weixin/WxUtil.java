package com.xzcode.pay.core.platforms.weixin;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 微信支付工具类
 * 
 * 
 * @author zai
 * 2017-11-27
 */
public class WxUtil {
	
	

	/**
	 * MD5参数签名
	 * @param params 
	 * @return
	 * 
	 * @author zai
	 * 2017-11-27
	 */
	public static String signMd5(String str) {
		return DigestUtils.md5Hex(str).toUpperCase();
	}
	
	/**
	 * 获取参数连接字符串
	 * @param params 请传入字典序的map，treemap
	 * @return
	 * 
	 * @author zai
	 * 2017-11-28
	 */
	public static String getParamString(TreeMap<String, Object> params) {
		StringBuilder temp = new StringBuilder();
		for (String key : params.keySet()) {
			temp.append(key).append("=").append(params.get(key)).append("&");
		}
		temp.setLength(temp.length() - 1);
		
		return temp.toString();
	}
	
	/**
	 * 获取参数连接字符串
	 * @param params
	 * @return
	 * 
	 * @author zai
	 * 2018-02-06
	 */
	public static String getParamString2(TreeMap<String, String> params) {
		StringBuilder temp = new StringBuilder();
		for (String key : params.keySet()) {
			temp.append(key).append("=").append(params.get(key)).append("&");
		}
		temp.setLength(temp.length() - 1);
		
		return temp.toString();
	}
	
	//<mch_id><![CDATA[10000100]]></mch_id>
	/**
	 * 参数转为xml格式数据
	 * @param params
	 * @return
	 * 
	 * @author zai
	 * 2017-11-27
	 */
	public static String toXml(Map<String, String> params) {
		StringBuilder temp = new StringBuilder();
		temp.append("<xml>");
		for (String key : params.keySet()) {
			temp.append("<").append(key).append("><![CDATA[").append(params.get(key)).append("]]></").append(key).append(">");
		}
		temp.append("</xml>");
		return temp.toString();
	}
	
	/**
	 * 从xml转换为map对象
	 * @param xml
	 * @return
	 * 
	 * @author zai
	 * 2017-11-27
	 */
	public static TreeMap<String, String> fromXml(String xml) {
		
		Document doc = Jsoup.parse(xml);
		
		Elements elements = doc.select("xml>*");
		
		TreeMap<String, String> params = new TreeMap<>();
		
		elements.forEach(x -> {
			params.put(x.tagName(), x.text());
		});
		
		return params;
	}
	

}
