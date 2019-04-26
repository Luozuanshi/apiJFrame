package com.domoyun.testCase;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.domoyun.base.Base;
import com.domoyun.base.Configure;
import com.domoyun.base.DataProviderClass;
import com.domoyun.pojo.CellData;
import com.domoyun.routine.Base64;
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
			  assertstString = String.valueOf(actualResult.contains(expectedReponseData)); 
		  }
		  else {
		  assertstString = "响应结果为空"; }
		  actualResult =  FastJson.base64Output(actualResult,useCaseTitle); 

		  
		//4：要写的数据的收集
		  int[] cell={7,8,9};
		ExcelUtils.addCellData(new CellData(sheetname,caseId, cell, actualResult,assertstString));
		
		//5.数据写出 全局静态变量 cellDatasToWriteMap
		if (sheetNumMaxsize ==Integer.parseInt(caseId)) {
			ExcelUtils.putmap(sheetname);
		}
		//6.用例断言
		Assert.assertTrue(actualResult.contains(expectedReponseData));

	} 
//	@Test(dataProvider="getRegion",dataProviderClass=DataProviderClass.class)//dataProvider="datas"
	public void getRegion(String caseId,String apiId,String requestData,String expectedReponseData,
			String preCheckSQL,String afterCheckSQL,String sheetname,int sheetNumMaxsize) throws ClientProtocolException, IOException, InterruptedException {
		//1：准备url
		String url = Configure.getUrlByApiId(apiId);
		//2：发包,得到响应结果
		String actualResult = HttpUtils.request(apiId,url,requestData,sheetname);
//		System.out.println(actualResult);
		//3.请求数据断言
		String assertstString =null;
		
		  if (!(actualResult.isEmpty())) { 
			  assertstString = String.valueOf(actualResult.contains(expectedReponseData)); 
		  }
		  else {
		  assertstString = "响应结果为空"; }
		  actualResult =  FastJson.base64Output(actualResult,""); 
		//4：要写的数据的收集
		int[] cell={7,8,9};
		ExcelUtils.addCellData(new CellData(sheetname,caseId, cell, actualResult,assertstString));
		
		//5.数据写出 全局静态变量 cellDatasToWriteMap
		if (sheetNumMaxsize ==Integer.parseInt(caseId)) {
			ExcelUtils.putmap(sheetname);
		}
		//6.用例断言
		Assert.assertTrue(actualResult.contains(expectedReponseData));

	} 
//	@Test(dataProvider="getRegionForReceiving",dataProviderClass=DataProviderClass.class)//dataProvider="datas"
	public void getRegionForReceiving(String caseId,String apiId,String requestData,String expectedReponseData,
			String preCheckSQL,String afterCheckSQL,String sheetname,int sheetNumMaxsize) throws ClientProtocolException, IOException, InterruptedException {
		//1：准备url
		String url = Configure.getUrlByApiId(apiId);
		//2：发包,得到响应结果
		String actualResult = HttpUtils.request(apiId,url,requestData,sheetname);
//		System.out.println(actualResult);
		//3.请求数据断言
		String assertstString =null;
		
		  if (!(actualResult.isEmpty())) { 
			  assertstString = String.valueOf(actualResult.contains(expectedReponseData)); 
		  }
		  else {
		  assertstString = "响应结果为空"; }
		  actualResult =  FastJson.base64Output(actualResult,""); 
		  
		  
		//4：要写的数据的收集
		int[] cell={7,8,9};
		ExcelUtils.addCellData(new CellData(sheetname,caseId, cell, actualResult,assertstString));
		
		//5.数据写出 全局静态变量 cellDatasToWriteMap
		if (sheetNumMaxsize ==Integer.parseInt(caseId)) {
			ExcelUtils.putmap(sheetname);
		}
		//6.用例断言
		Assert.assertTrue(actualResult.contains(expectedReponseData));

	} 
	
}
	
