package com.domoyun.testCase;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
 * 数据--》数据库
 * 数据验证--》 查询数据库
 * 什么时候验证？--> 
 * 		执行测试用例之前：前置数据验证
 * 		执行测试用例之后：后置数据--》验证
 * 怎么验证？？--》 jdbc操作--》查询sql
 * 注册的功能：
 * 		正向的测试用例：13888888888 12345  tom
 * 		前置：查询数据库确定13888888888不存在
 * 			第一条验证sql：一条记录，单个字段
 * 			sql:select count(1) from member where mobilephone='13888888888';
 * 			预期结果：0
 * 		后置：完成注册测试用例执行后，13888888888对应的用户信息已经保存到数据库中
 * 
 * 			no:1  第一条验证sql：一条记录，单个字段 验证member表已经存在该用户信息
 * 			sql:select count(1) from member where mobilephone='13888888888';
 * 			预期结果：1
 * 			{
 * 				"no":"1",
 * 				"sql":"select count(1) from member where mobilephone='13888888888';",
 * 				"expectedResult":[{"count(1)":"1"}]
 * 
 * 			}
 * 
 * 			no:2  第二条验证sql：结果一条记录，多个字段
 * 			sql：select leaveamount,type where mobilephone='13888888888';
 * 			预期结果：leaveamount=0.00,type=1
 * 			{
 * 				"no":"2",
 * 				"sql":"select leaveamount,type where mobilephone='13888888888';",
 * 				"expectedResult":[
 * 					{"leaveamount":"0.00","type":"1"}
 * 				]
 * 
 * 			}
 * 
 * 			no:3  第三条验证sql 分页查询用户列表：多条记录，多个字段 -->String
 * 			sql：select RegName,MobilePhone,Type,LeaveAmount from member LIMIT 0,5;  -->String
 * 			期望结果：[]
 * 				???	13517315669	1	0.00
				kaishui	13212575711	1	0.00
				kaishui111	13212575722	1	0.00
				tom_2	13888887779	1	0.00
				kaishui1	13222222222	1	0.00
			
			{
				"no":"3",
				"sql":"select RegName,MobilePhone,Type,LeaveAmount from member LIMIT 0,5;",
				"expectedResult":[
					{"RegName":"???","MobilePhone":"13517315669","Type":"1","LeaveAmount":"0.00"},
					{"RegName":"kaishui","MobilePhone":"13517315669","Type":"1","LeaveAmount":"0.00"},
					{"RegName":"kaishui111","MobilePhone":"13517315669","Type":"1","LeaveAmount":"0.00"}
					
				]
			}

 * 				
 * 	1:上面的这些sql、每条sql的期望结果都应该保存到excel中--》excel表格保存的数据类型-->String-->json
 	[
 		{
  				"no":"1",
  				"sql":"select count(1) from member where mobilephone='13888888888';",
  				"expectedResult":[{"count(1)":"1"}]
 
  		},
  		{
  				"no":"2",
  				"sql":"select leaveamount,type where mobilephone='13888888888';",
 				"expectedResult":[
 					{"leaveamount":"0.00","type":"1"}
 				]
  
  		},
  		{
				"no":"3",
				"sql":"select RegName,MobilePhone,Type,LeaveAmount from member LIMIT 0,5;",
				"expectedResult":[
					{"RegName":"???","MobilePhone":"13517315669","Type":"1","LeaveAmount":"0.00"},
					{"RegName":"kaishui","MobilePhone":"13517315669","Type":"1","LeaveAmount":"0.00"},
					{"RegName":"kaishui111","MobilePhone":"13517315669","Type":"1","LeaveAmount":"0.00"}
					
				]
		}
 		
 	]
 * 		
 * 		
 * @author pangluo
 * @date 2018年12月5日
 * @desc 
 * @email
 */
public class All_Test_Case extends Base{
	
	@DataProvider
	public Object[][] datas(){
		
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("/apibatch.xlsx","request_data", ApiDetail.class);
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
		  assertstString = "66"; }
		
		//4：要写的数据的收集
		int[] cell={6,7,8};
		
		ExcelUtils.addCellData("request_data",2,new CellData(caseId, cell, actualResult,assertstString));
		
		//5:后置验证
//		DataValidateUtils.afterValidate(caseId,10,afterCheckSQL);

		Assert.assertTrue(actualResult.contains(expectedReponseData));
		} 
	
	@AfterClass
	public static void AfterClass() {
		System.out.println("request_data收集");
		ExcelUtils.putmap("request_data");
	}
	
}
	
