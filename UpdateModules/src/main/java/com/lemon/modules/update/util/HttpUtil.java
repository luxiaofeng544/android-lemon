package com.lemon.modules.update.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

/**
 * @projectName UpdateModules
 * @PackageName com.xinxin.android.update.util
 * @Title HttpUtil
 * @Description  基础  Http网络访问工具
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class HttpUtil {

	/**
	 * 设置http超时时间
	 */
	private static final int TIMEOUT = 2 * 60 * 1000;
	
	private String urlString ;
	private int responseCode;
	private String httpMethod;
	private InputStream isResult;
	private HttpClient httpClient;
	
	private static HttpUtil mUtil;
	
	private boolean isWork = false;//判断网络是否处于工作中
	
	private HttpUtil() {
	}
	
	public static HttpUtil getInstance(){
		if(mUtil == null){
			mUtil = new HttpUtil();
		}
		return mUtil;
	}
	
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public InputStream getIsResult() {
		return isResult;
	}
	public void setIsResult(InputStream isResult) {
		this.isResult = isResult;
	}
	public HttpClient getHttpClient() {
		return httpClient;
	}
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	/**
	 * 初始化 httpClient 
	 * 
	 * @return	执行结果
	 * 			
	 * 			1 成功 , 0 失败
	 */
	private int initHttpClient() {
		if(httpClient == null) {
			HttpParams httpParameters = new BasicHttpParams();// Set the timeout in milliseconds until a connection is established.  
            HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);// Set the default socket timeout (SO_TIMEOUT) // in milliseconds which is the timeout for waiting for data.  
            HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT); 
            httpClient = new DefaultHttpClient(httpParameters);
		}
		
		return httpClient != null ? 1:0;
	}
	
	/**
	 * 	执行  http 方法
	 * 			
	 * 			兼容  get 和  post 
	 * 				
	 * 				当 postParams = null 时,执行get 
	 * 
	 * @param url	http 链接
	 * 
	 * @param postParams	post 数据
	 * 
	 * @return	返回方法执行状态
	 * 
	 * 			1 成功,0失败,-1异常,-2 responseCode != 200
	 * 
	 */
	public synchronized int excuteHttp(String url,Map<String, String> postParams)throws IOException{
		
		int result = 1 ;
		
		if(url == null || url.length() <= 0) {
			return 0;
		}
		
		if(initHttpClient() == 0 || httpClient == null) {
			return 0;	
		} 
		
		HttpRequestBase httpMethod = null;
		
		try {
			
			//设置post数据
			if (postParams != null &&postParams.size() > 0 ) {
				HttpPost post = new HttpPost(url);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String name : postParams.keySet()) {
					params.add(new BasicNameValuePair(name, postParams.get(name)));
				}
				HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
				post.setEntity(entity);
				httpMethod = post;
			} else {
				httpMethod = new HttpGet(url);
			}
			
			HttpResponse response = httpClient.execute(httpMethod);
			responseCode = response.getStatusLine().getStatusCode();
			
			switch(responseCode) {
				case HttpStatus.SC_OK:
					isResult = response.getEntity().getContent();
					break;
				case HttpStatus.SC_NOT_FOUND:
					//do for 404 state 
					result = -2;
					break;
				default:
					//do default state
					result = -2;
					break;
			}
			
		} catch (ClientProtocolException e) {
			result = -1;
			Log.e(this.getClass().getSimpleName(), "ClientProtocolException异常", e);
		}
		catch (IOException e) {
			result = -1;
			Log.e(this.getClass().getSimpleName(), "IOException异常", e);
			throw e;
		} 
		finally {
		}
		
		return result;
	}
	
	/**
	 * 关闭 httpClient
	 * 
	 */
	public void close() {
		if (httpClient != null) {
			try {
				httpClient.getConnectionManager().shutdown();
				httpClient = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
