package com.lemon.api.auto.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import com.lemon.api.auto.pojo.ApiInfo;
import com.lemon.api.auto.pojo.ExcelObject;

public class HttpUtils {

	/**
	 * 写一个方法，发get请求
	 * 登录接口、注册接口、充值接口、提现接口....
	 * 1:url会变
	 * 2:参数可能变化
	 * @return
	 */
	private static String get(String url, Map<String, String> paramsMap) {
		try {
			//创建一个容器，保存名值对参数
			List<NameValuePair> parameters = null;

			//有参数设置参数，没参数不要设值参数
			if (paramsMap != null) {
				parameters = new ArrayList<>();
				//遍历map的所有的键值对
				Set<String> keySet = paramsMap.keySet();
				for (String name : keySet) {
					String value = paramsMap.get(name);
					parameters.add(new BasicNameValuePair(name, value));
				}

				//编码后的参数列表
				String encodeParams = URLEncodedUtils.format(parameters, "utf-8");
				url += ("?" + encodeParams);
			}

			HttpGet get = new HttpGet(url);
			//			get.addHeader("Cookie", "JSESSIONID=59916658BFE68AF0F469C6C5D1BC39C4");

			//添加cookie请求头
			String jSessionId = ParameterUtils.getGlobalData("JSESSIONID");
			if (jSessionId != null) {
				get.addHeader("Cookie", jSessionId);
			}

			//创建一个HTTP发包客户端(HTTP发包客户端具备这样的功能：浏览器、postman、jmeter、fiddler、soapui、app)
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//发数据包-->获得相应
			CloseableHttpResponse response = httpClient.execute(get);
			//3：响应体
			HttpEntity respEntity = response.getEntity();

			return EntityUtils.toString(respEntity);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	private static String post(String url, Map<String, String> paramsMap) {
		String result = "";
		try {
			//2:生成post请求
			HttpPost post = new HttpPost(url);
			List<NameValuePair> parameters = null;
			if (paramsMap != null) {

				parameters = new ArrayList<>();
				//遍历map的所有的键值对
				Set<String> keySet = paramsMap.keySet();
				for (String name : keySet) {
					String value = paramsMap.get(name);
					parameters.add(new BasicNameValuePair(name, value));
				}
				//创建一个原生form表单的请求体
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
				//设置post请求请求体
				post.setEntity(entity);
			}
			//			post.addHeader("Cookie", "JSESSIONID=59916658BFE68AF0F469C6C5D1BC39C4");
			//			post.addHeader("Cookie", ParameterUtils.getGlobalData("JSESSIONID"));
			//添加cookie请求头
			String jSessionId = ParameterUtils.getGlobalData("JSESSIONID");
			if (jSessionId != null) {
				post.addHeader("Cookie", jSessionId);
			}

			//创建一个HTTP发包客户端(HTTP发包客户端具备这样的功能：浏览器、postman、jmeter、fiddler、soapui、app)
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//发数据包-->获得相应
			CloseableHttpResponse response = httpClient.execute(post);
			//断言：响应状态码为200
			Assert.assertTrue(response.getStatusLine().getStatusCode() == 200);
			//将会话id添加到全局数据池
			addSeesionIdToGlobalData(response);
			//获得响应体
			HttpEntity respEntity = response.getEntity();
			result = EntityUtils.toString(respEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将登录成功后返回的token保存到全局数据池中间
	 * @param response
	 */
	private static void addSeesionIdToGlobalData(CloseableHttpResponse response) {
		//		Header[] headers = response.getAllHeaders();
		//根据响应头的名称获得对应响应头
		Header header = response.getFirstHeader("Set-Cookie");
		if (header != null) {
			//获得Set-Cookie响应头的值
			String headerValue = header.getValue();
			//JSESSIONID=F1B8174B3B8DE92ADA325AE8D6DF0D5D; Path=/futureloan; HttpOnly
			if (headerValue != null && headerValue.trim().length() > 0) {
				//如果包含JSESSIONID字符串
				if (headerValue.contains("JSESSIONID")) {
					int idx = headerValue.indexOf(";");
					String jsessionId = headerValue.substring(0, idx);
					//保存到全局数据池
					ParameterUtils.addGlobalData("JSESSIONID", jsessionId);
				}
			}

		}
	}

	private static String delete(String url, Map<String, String> paramsMap) {
		// TODO 明天要写好这个delete方法
		return null;
	}

	private static String put(String url, Map<String, String> paramsMap) {
		//FIXME 这里有大问题，非常紧急
		return null;
	}

	//example
	public static void main(String[] args) {
		//		getUrlByApiId("1");
		/*	Set<String> keySet = apiInfoMap.keySet();
			for (String key : keySet) {
				System.out.println(key + "--->" + apiInfoMap.get(key));
			}*/

		//		System.out.println(apiInfoMap.get("6").getUrl());

		String loginUrl = "http://120.79.150.210:8080/futureloan/mvc/api/member/login";
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("mobilephone", "13666666666");
		paramsMap.put("pwd", "123456");
		String loginResult = post(loginUrl, paramsMap);
		System.out.println(loginResult);

		//		
		//		String url = "http://120.79.150.210:8080/futureloan/mvc/api/financelog/getFinanceLogList?memberId=9";
		//		String result = get(url, null);
		//		System.out.println(result);
	}

	/**
	 * 发包（分发各种请求）
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String request(String apiId, String url, Map<String, String> paramsMap) {
		String result = "";
		String method = ApiUtils.getRequestMethodByApiId(apiId);
		if ("get".equalsIgnoreCase(method)) {
			result = get(url, paramsMap);
		} else if ("post".equalsIgnoreCase(method)) {
			result = post(url, paramsMap);
		} else if ("put".equalsIgnoreCase(method)) {
			result = put(url, paramsMap);
		} else if ("delete".equalsIgnoreCase(method)) {
			result = delete(url, paramsMap);
		}
		return result;
	}

}
