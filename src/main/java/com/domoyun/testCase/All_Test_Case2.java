package com.domoyun.testCase;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.domoyun.base.Base;
import com.domoyun.pojo.ApiDetail;
import com.domoyun.pojo.CellData;
import com.domoyun.pojo.ExcelObject;
import com.domoyun.util.ApiUtils;
import com.domoyun.util.ExcelUtils;
import com.domoyun.util.HttpUtils;
/**
 * @author pangluo
 * @date 2018年12月5日
 * @desc 
 * @email
 */
public class All_Test_Case2 extends Base{
	
	@DataProvider
	public Object[][] datas(){
		
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("/apibatch.xlsx","getCountry", ApiDetail.class);
		int size = objectList.size();
//		System.out.println(objectList);
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
		//1：准备url
		String url = ApiUtils.getUrlByApiId(apiId);
		//2：准备参数
//		 System.out.println(caseId);
		//3：发包,得到响应结果
		String actualResult = HttpUtils.request(apiId,url,requestData);
//		System.out.println(actualResult);
		String assertstString =null;
		  if (!(actualResult.isEmpty())) { 
			  assertstString = String.valueOf(actualResult.contains(expectedReponseData)); 
		  }
		  else {
		  assertstString = "66"; }
		
		//4：要写的数据的收集
		int[] cell={6,7,8};
		ExcelUtils.addCellData("getCountry",3,new CellData(caseId, cell, actualResult,assertstString));
		Assert.assertTrue(actualResult.contains(expectedReponseData));
		} 
	@AfterClass
	public static void AfterClass() {
		System.out.println("getCountry收集");
		ExcelUtils.putmap("getCountry");
	}
	

}
	
