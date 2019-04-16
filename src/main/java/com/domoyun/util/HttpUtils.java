package com.domoyun.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.domoyun.base.Configure;

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
				//
				post.addHeader("Content-Type", "application/json");
				post.addHeader("Authorization", "Basic Nzc3Nzc6LlFrPilaMnZ+Kg==");
				
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
			System.out.println(response.getStatusLine().getStatusCode());
//			Assert.assertTrue(response.getStatusLine().getStatusCode() == 200);
		
			//将会话id添加到全局数据池
			addSeesionIdToGlobalData(response);
			//获得响应体
			HttpEntity respEntity = response.getEntity();
			result = EntityUtils.toString(respEntity);
			System.out.println(result);
			//获取响应头
			Header[] header = response.getAllHeaders();
			for (Header header2 : header) {
				System.out.println(header2.getName()+":"+header2.getValue());
			}
			
			
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
		String xmlparmete="<paramsJson>{\"product_sku\":\"API_sku_AutoTest001\",\"reference_no\":\"\",\"product_title\":\"意大利怀表\",\"product_weight\":\"0.35\",\"product_length\":\"29.70\",\"product_width\":\"21.00\",\"product_height\":\"4\",\"contain_battery\":\"0\",\"product_declared_value\":\"10.5\",\"product_declared_name\":\"MenTshirt\",\"cat_lang\":\"en\",\"cat_id_level0\":\"400001\",\"cat_id_level1\":\"500013\",\"cat_id_level2\":\"600788\",\"verify\":\"0\",\"hs_code\":\"code123\",\"warning_qty\":\"10\"}</paramsJson>";
		System.out.println(HttpPostWithXml("http://test-oms.eminxing.com:50080/default/svc/web-service", xmlparmete)); 
		
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
		String method = Configure.getRequestMethodByApiId(apiId);
		System.out.println(method);
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
	/**
	 * 发包（分发各种请求）
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String request(String apiId, String url, String requestData) {
		String result = "";
		String method = Configure.getRequestMethodByApiId(apiId);
		System.out.println(method);
		 if ("postJson".equalsIgnoreCase(method)) {
			result = HttpPostWithJson(url, requestData);
		}else if ("postXml".equalsIgnoreCase(method)) {
			result = HttpPostWithXml(url, requestData);
		} 
		return result;
	}
	
	/**
	 * json格式post请求
	 * @param url
	 * @param json String类型的json格式数据
	 * @return String类型json格式数据
	 */
	public static String HttpPostWithJson(String url, String json) {
		String returnValue = "请求数据格式Json,这是默认返回值，接口调用失败";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try{
			//第一步：创建HttpClient对象
		 httpClient = HttpClients.createDefault();
		 	
		 	//第二步：创建httpPost对象
	        HttpPost httpPost = new HttpPost(url);
	        
	        //第三步：给httpPost设置JSON格式的参数
	        StringEntity requestEntity = new StringEntity(json,"utf-8");
	        
	        httpPost.addHeader("Authorization", "Basic Nzc3Nzc6LlFrPilaMnZ+Kg==");
	        requestEntity.setContentEncoding("UTF-8");    	        
	        httpPost.setHeader("Content-type", "application/json");
	        httpPost.setEntity(requestEntity);
	       
	       //第四步：发送HttpPost请求，获取返回值
	       returnValue = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法
	      
		}
		 catch(Exception e)
		{
			// e.printStackTrace();
		}
		finally {
	       try {
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }
		 //第五步：返回值
	     return returnValue;
	}
	
	/**
	 * xml格式post请求
	 * @param url
	 * @param xml String类型的xml格式数据
	 * @return String类型xml格式数据
	 */
	public static String HttpPostWithXml(String url, String xml) {
		String returnValue = "请求数据格式Xml,这是默认返回值，接口调用失败";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		
		String xmlparmete=
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.example.org/Ec/\">\n" +
                        "\t<SOAP-ENV:Body>\n" +
                        "\t\t<ns1:callService>\n"+
                        ParameterUtils.getFunctionOptStr(ParameterUtils.getCommonStr(xml))+
                        "\t\t\t<appToken>"+"e3b35ad93c4d3d831728ff1217d02b90"+"</appToken>\n" +
                        "\t\t\t<appKey>"+ "b39989ad17963d61d1c18267d3fc1605"+"</appKey>\n" +
                        "\t\t\t<service>"+"createProduct"+"</service>\n" +
                        "\t\t</ns1:callService>\n" +
                        "\t</SOAP-ENV:Body>\n" +
                        "</SOAP-ENV:Envelope>";
		System.out.println(xmlparmete);
        try {
        	//创建HttpClient对象
        	 httpClient = HttpClients.createDefault();
        	//创建HttpPost对象
             HttpPost httpPost = new HttpPost(url);
            //设置setEntity
            if (xml != null && xml.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(xmlparmete, "UTF-8");
                stringEntity.setContentType("text/xml");
                httpPost.setEntity(stringEntity);
            //发送HttpPost请求，获取返回值
                response = httpClient.execute(httpPost);
               HttpEntity entity =  response.getEntity();
               returnValue =EntityUtils.toString(entity); 
            }else {
                System.out.println("xml请求正文为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
	
	
}
