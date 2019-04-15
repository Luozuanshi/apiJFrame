package com.domoyun.testCase;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.domoyun.base.ApiUtils;
import com.domoyun.base.Base;
import com.domoyun.pojo.ApiDetail;
import com.domoyun.pojo.CellData;
import com.domoyun.pojo.ExcelObject;
import com.domoyun.util.ExcelUtils;
import com.domoyun.util.HttpUtils;
import com.domoyun.util.LoggerControler;

/**
 * 		
 * 		
 * @author pangluo
 * @date 2018年12月5日
 * @desc 
 * @email
 */
public class All_Test_Case extends Base{
	final static LoggerControler log = LoggerControler.getLogger(All_Test_Case.class);
	@DataProvider
	public Object[][] datas(){
		
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("/apibatch.xlsx","getCountry", ApiDetail.class);
		int size = objectList.size();
		System.out.println(objectList);
		
		//创建一个容器--》数据提供者需要的二维数组--》只要获得需要的信息即可
		Object[][] datas = new Object[size][6];
		
		for(int i=0;i<size;i++){
			ApiDetail apiDetail = (ApiDetail) objectList.get(i);
			datas[i][0] = apiDetail.getCaseId();
			datas[i][1] = apiDetail.getApiId();
			datas[i][2] = apiDetail.getRequestData();
			datas[i][3] = apiDetail.getExpectedReponseData();
			datas[i][4] = apiDetail.getPreCheckSQL();
			datas[i][5] = apiDetail.getAfterCheckSQL();
		}
//		ExcelUtils.cellDatasToWriteList.clear();
		return datas;
	}

	@Test(dataProvider="datas")//dataProvider="datas"
	public void test_case(String caseId,String apiId,String requestData,String expectedReponseData,
			String preCheckSQL,String afterCheckSQL) throws ClientProtocolException, IOException, InterruptedException {
		//0:前置验证*证
//		DataValidateUtils.preValidate(caseId,8,preCheckSQL);
		//1：准备url
		String url = ApiUtils.getUrlByApiId(apiId);
//		System.out.println(caseId);
		//2：准备参数
		/*
		 * Map<String, Object> paramsMap = (Map<String, Object>)
		 * JSON.parse(requestData);
		 * 
		 * Set<String> keySet = paramsMap.keySet(); for (String key : keySet) {
		 * System.out.println(key+":"+paramsMap.get(key)); }
		 */
		//3：发包,得到响应结果
		String actualResult = HttpUtils.request(apiId,url,requestData);
//		System.out.println(actualResult);
		String assertstString =null;
		
		  if (!(actualResult.isEmpty())) { 
			  assertstString = String.valueOf(actualResult.contains(expectedReponseData)); 
		  }
		  else {
		  assertstString = "响应结果为空"; }
		
		//4：要写的数据的收集
		int[] cell={6,7,8};
		
		ExcelUtils.addCellData("getCountry",2,new CellData(caseId, cell, actualResult,assertstString));
		
		//5:后置验证
//		DataValidateUtils.afterValidate(caseId,10,afterCheckSQL);

		Assert.assertTrue(actualResult.contains(expectedReponseData));
		} 
	
	@AfterClass
	public static void AfterClass() {
		
		ExcelUtils.putmap("getCountry");
		log.info("getCountry收集");
	}
	
}
	
