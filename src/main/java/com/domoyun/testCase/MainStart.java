package com.domoyun.testCase;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.domoyun.base.Base;
import com.domoyun.dataprovider.Configure;
import com.domoyun.dataprovider.DataProviderClass;
import com.domoyun.pojo.CellData;
import com.domoyun.routine.FastJson;
import com.domoyun.util.ExcelUtils;
import com.domoyun.util.HttpUtils;

/**
 * 	测试类	
 * @author pangluo
 * @date 2018年12月5日
 * @desc 测试类
 * @email
 */
public class MainStart extends Base{
	
	@Test(dataProvider="getCountry",dataProviderClass=DataProviderClass.class)//dataProvider="datas"
	public void getCountry(String caseId,String apiId,String requestData,String expectedReponseData,
			String preCheckSQL,String afterCheckSQL,String sheetname,int sheetNumMaxsize,String useCaseTitle) throws ClientProtocolException, IOException, InterruptedException {
		//1：准备url
		String url = Configure.getUrlByApiId(apiId);
		//2：发包,得到响应结果
		String actualResult = HttpUtils.request(apiId,url,requestData,sheetname);
//		System.out.println(actualResult);
		//3.请求数据断言
		String assertstString =null;
		
		  if (!(actualResult.isEmpty())) { 
			  assertstString = (actualResult.contains(expectedReponseData)?"通过":"不通过"); 
		  }
		  else {
		  assertstString = "响应结果为空"; }
		  
		  Map<String, String> actualResultMap =  FastJson.base64Output(actualResult,useCaseTitle,sheetname); 
		  actualResult = actualResultMap.get("actualResult");
		  String filepath = actualResultMap.get("filepath");
		  String filename = actualResultMap.get("filename");
		  
		//4：要写的数据的收集
		int[] cell={7,8,9};
		ExcelUtils.addCellData(new CellData(sheetname,caseId, cell, actualResult,assertstString,filepath,filename));
		
		/*
		 * url =
		 * "http://192.168.109.224:8000/V4/Api/LabelPrintService/CancelLabel?type=json";
		 * requestData ="{\r\n" + "  \"Data\": {\r\n" +
		 * "    \"orderID\": \"TestHKRM171068\", \r\n" +
		 * "    \"trackingNumber\": \"IDP222217605GB\"\r\n" + "  }, \r\n" +
		 * "  \"RequestId\": \"请自己生成一个GUID\", \r\n" +
		 * "  \"RequestTime\": \"当前的UTC0时间\", \r\n" + "  \"Version\": \"0.0.0.3\"\r\n" +
		 * "}\r\n" + ""; actualResult = HttpUtils.HttpPostWithJson(url, requestData);
		 */
		
		//5.数据写出 全局静态变量 cellDatasToWriteMap
		if (sheetNumMaxsize ==Integer.parseInt(caseId)) {
			ExcelUtils.putmap(sheetname);
		}
		//6.用例断言
		Assert.assertTrue(actualResult.contains(expectedReponseData));
	} 
	
}
	
