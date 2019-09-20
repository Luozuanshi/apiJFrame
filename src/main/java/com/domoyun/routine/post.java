package com.domoyun.routine;
 
import java.io.IOException;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

 
 
public class post {
	/**
	 *  	main方法测试类
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://test.tms.com/V4/api/LabelPrintService/PrintLabel?type=json";
		
		String json1 = "";
		String json2 = "{\"Version\":\"0.0.0.3\",\"Data\":{\"OrderID\":\"TestFedex6069QW\",\"ParcelInformation\":{\"Weight\":\"70\",\"WeightUnit\":1,\"Length\":\"30\",\"Width\":\"20\",\"Height\":\"10\",\"SizeUnit\":1,\"ExistDangeroursGoods\":false,\"ProductInformations\":[{\"Description\":\"phonecase\",\"Quantity\":1,\"Weight\":\"1.000\",\"WeightUnit\":4,\"Value\":\"0.0\",\"Sku\":\"\",\"Remark\":\"\",\"ProductUrl\":\"\",\"HSCode\":\"testcode\"}]},\"RecipientAddress\":{\"FirstName\":\"Shelley\",\"LastName\":\"Bond \",\"Company\":null,\"StreetAddress\":\"3817 Harrison Street\",\"StreetAddress2\":\"Address2\",\"StreetAddress3\":null,\"City\":\"San Francisco\",\"State\":\"CA\",\"ZIPCode\":\"94122\",\"Country\":\"US\",\"PhoneNumber\":\"+1.4155669802\",\"IsResidential\":true},\"ChannelName\":\"FEDEX\",\"Token\":\"99999999999999999999999999999999\",\"ServiceTypeCode\":\"FEDEX_GROUND\",\"WarehouseCode\":\"CA\",\"LabelMarkText\":null,\"RedundancyField\":{\"SignatureOption\":\"\"}}}\r\n" + "";
		String json3 = "{\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"imtype\":\"LTCUS\"}},{\"range\":{\"rtdatetime\":{\"gte\":1521164922000,\"lte\":1521164932000}}}]}}}";
		
		String json = json2;
		
		System.out.println(HttpPostWithJson(url, json));
		
		
	}
	/**
	 * json格式post请求
	 * @param url
	 * @param json String类型的json格式数据
	 * @return String类型json格式数据
	 */
	public static String HttpPostWithJson(String url, String json) {
		String returnValue = "这是默认返回值，接口调用失败";
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
			 e.printStackTrace();
		}
		finally {
	       try {
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }
		 //第五步：处理返回值
	     return returnValue;
	}
	
}
