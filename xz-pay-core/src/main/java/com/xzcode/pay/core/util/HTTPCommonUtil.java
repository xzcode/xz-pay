package com.xzcode.pay.core.util;

import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.helper.HttpConnection;

public class HTTPCommonUtil {
	
	static{
		trustEveryone();
	}
	
	//信任https
	public static void trustEveryone() {
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new X509TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			} }, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getHttpHeaders(String url, int timeout) {
		Connection conn = null;
		try {
			conn = HttpConnection.connect(url);
			conn.timeout(timeout);
			conn.header("Accept-Encoding", "gzip,deflate,sdch");
			conn.header("Connection", "close");
			conn.get();
			Map<String, String> result = conn.response().headers();
			result.put("title", conn.response().parse().title());
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getHttpBody(String url, String method,Map<String, String> params, int timeout) {
		Connection conn = null;
		try {
			conn = HttpConnection.connect(url);
			conn.timeout(timeout);
			
			if (params != null) {
				conn.data(params);				
			}
			if ("post".equalsIgnoreCase(method)) {
				conn.post();
			}else {
				conn.get();
			}
			return conn.response().body();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getHttpBody(String url, String method,Map<String, String> params) {
		return getHttpBody(url, method, params, 10000);
	}
	
	public static String getHttpBody(String url, String method) {
		return getHttpBody(url, method, null, 10000);
	}
	
	
	public static Response getHttpResponse(String url, Map<String, String> params,int timeout) throws SocketTimeoutException {
		Connection conn = null;
		try {
			conn = HttpConnection.connect(url);
			conn.timeout(timeout);
			conn.ignoreContentType(true);
			//conn.header("Content-Type","text/*");
			if (params !=null) {
				conn.data(params);
				conn.post();
			}else {
				conn.get();
			}
			return conn.response();

		} catch (Exception e) {
			if (e instanceof SocketTimeoutException || e instanceof java.net.ConnectException) {
				throw new SocketTimeoutException("time out!!");
			}else {
				throw new RuntimeException(e);
			}
		}
	}
	
	public static String postRequestBody(String url, String requestBody, int timeout) {
		Connection conn = null;
		try {
			conn = HttpConnection.connect(url).ignoreContentType(true);
			conn.timeout(timeout);
			
			if (requestBody != null) {
				conn.requestBody(requestBody);				
			}
				conn.method(Method.POST);
			Response response = conn.execute();
			
			return response.body();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
}
