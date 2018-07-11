package com.dower.sharerideapp.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @author NiuXueJun
 * 2015-7-31 下午3:22:08
 */
public class HttpRequestUtil {
	
	/**
	 * HTTP带参数请求
	 * @param url 请求地址
	 * @param param 参数
	 * @param ispost post/get请求
	 * @return String
	 */
	public static String request(String url, Map<String, String> param,
			boolean ispost) {
		if (ispost) {
			return postData(url, param);
		} else {
			return getData(url, param);
		}
	}
	
	/**
	 * HTTP带参数请求
	 * @param url 请求地址
	 * @param param 参数
	 * @param ispost post/get请求
	 * @return String
	 */
	public static String request(String url, Map<String, String> param,
			boolean ispost, int timeOut) {
		if (ispost) {
			return postData(url, param, timeOut);
		} else {
			return getData(url, param);
		}
	}
	
	/**
	 * 获取HTTP请求返回结果
	 * @param url 请求地址
	 * @param param 参数
	 * @return HttpEntity
	 */
	public static HttpEntity getEntityData(String url, Map<String, String> param) {
		try{
			url = url + getParamStrByMap(param);
			HttpGet httpRequest = new HttpGet(url);
			// 取得HttpClient对象
			HttpClient httpclient = new DefaultHttpClient();
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			// 请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return httpResponse.getEntity();
			}
		}catch (Exception e){
		}
		return null;
	}

	/**
	 * HTTP发送post请求
	 * @param url 请求地址
	 * @param content 请求内容
	 * @return String
	 */
	public static String postDataStr(String url, String content) {
		try {
			HttpPost httppost = new HttpPost(url);
			// 添加参数
			httppost.setEntity(new StringEntity(content, HTTP.UTF_8));
			// 设置编码
			HttpResponse response = new DefaultHttpClient().execute(httppost);
			// 发送Post,并返回一个HttpResponse对象
			if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
				// 得到返回的字符串
				String result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求失败:" + url, e);
		}
		return null;
	}

	/**
	 * 获取HTTP发送get请求返回结果
	 * @param url 请求地址
	 * @param param 参数
	 * @return String
	 */
	private static String getData(String url, Map<String, String> param) {
		try{
			url = url + getParamStrByMap(param);
			HttpGet httpRequest = new HttpGet(url);
			// 取得HttpClient对象
			HttpClient httpclient = new DefaultHttpClient();
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			// 请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				// 取得返回的字符串
				String strResult = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
				return strResult;
			}
		}catch (Exception e){
		}
		return null;
	}

	/**
	 * 获取HTTP发送post请求返回结果
	 * @param url 请求地址
	 * @param param 参数
	 * @return String
	 */
	private static String postData(String url, Map<String, String> param) {
		try {
			HttpPost httppost = new HttpPost(url);
			// 建立HttpPost对象
			List<NameValuePair> params = getParamListByMap(param);
			// 添加参数
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 设置编码
			HttpResponse response = new DefaultHttpClient().execute(httppost);
			// 发送Post,并返回一个HttpResponse对象
			if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
				// 得到返回的字符串
				String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求失败:" + url, e);
		}
		return null;
	}
	
	/**
	 * 获取HTTP发送post请求返回结果
	 * @param url 请求地址
	 * @param param 参数
	 * @return String
	 */
	private static String postData(String url, Map<String, String> param, int timeOut) {
		try {
			HttpPost httppost = new HttpPost(url);
			// 建立HttpPost对象
			List<NameValuePair> params = getParamListByMap(param);
			// 添加参数
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 设置编码
			HttpClient client = new DefaultHttpClient();
			HttpParams httpParams = client.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, timeOut);
			HttpConnectionParams.setSoTimeout(httpParams, timeOut);
			HttpResponse response = client.execute(httppost);
			// 发送Post,并返回一个HttpResponse对象
			if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
				// 得到返回的字符串
				String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求失败:" + url, e);
		}
		return null;
	}

	/**
	 * 将map参数转化成HTTP请求参数
	 * @param paramMap
	 * @return String
	 */
	private static String getParamStrByMap(Map<String, String> paramMap) {
		StringBuffer result = new StringBuffer();
		result.append("?");
		for (String paramName : paramMap.keySet()) {
			result.append("&").append(paramName).append("=").append(paramMap.get(paramName));
		}

		return result.toString();
	}

	private static List<NameValuePair> getParamListByMap(Map<String, String> paramMap) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String paramName : paramMap.keySet()) {
			params.add(new BasicNameValuePair(paramName, paramMap.get(paramName)));
		}
		return params;
	}
}
