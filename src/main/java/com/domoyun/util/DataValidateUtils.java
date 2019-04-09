package com.domoyun.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.domoyun.pojo.CellData;
import com.domoyun.pojo.SqlChecker;
import com.domoyun.pojo.ValidateResult;

public class DataValidateUtils {
	

	/**
	 * 前置验证
	 * @param preCheckSQL
	 * @param preCheckSQL2 
	 * @param cellNum 
	 */
	public static void preValidate(String caseId, int cellNum, String preCheckSQL) {
		if (StringUtils.isEmpty(preCheckSQL) ) {
			return;
		}
//		System.out.println("前置验证开始，sql："+preCheckSQL);
		//解析json
		List<SqlChecker> sqlCheckerList =  (List<SqlChecker>) JSONObject.parseArray(preCheckSQL,SqlChecker.class);
		
		//创建一个容器，保存验证结果信息
		List<ValidateResult> validateResultList = new ArrayList<>();
		
		for (SqlChecker sqlChecker : sqlCheckerList) {
			String no = sqlChecker.getNo();
			String sql = sqlChecker.getSql();
			//期望的结果
			List<LinkedHashMap<String, String>> expectedResultList = sqlChecker.getExpectedResult();
			String expectedResultStr = JSONObject.toJSONString(expectedResultList);
			System.out.println(expectedResultStr);
			//实际的结果
			List<LinkedHashMap<String, String>> actualResultList = DBUtils.select(sql);
			String actualResultStr = JSONObject.toJSONString(actualResultList);
			System.out.println(actualResultStr);
			//判断
			if (actualResultStr.equals(expectedResultStr)) {
//				System.out.println("本条sql验证通过");
				//回写结果到excel对应的测试用例
				validateResultList.add(new ValidateResult(no, actualResultList, "success"));
			}else {
				validateResultList.add(new ValidateResult(no, actualResultList, "fail"));
//				System.out.println("验证不通过");
			}
			//断言
			Assert.assertEquals(actualResultStr, expectedResultStr);
		}
		
		String validateResultToWrite = JSONObject.toJSONString(validateResultList);
//		System.out.println("以下是要回写的结果");
//		System.out.println(validateResultToWrite);	
		
		//收集到回写数据池中间
		ExcelUtils.addCellData(new CellData(caseId, cellNum, validateResultToWrite));
//		System.out.println("------------------------------------------");
	}

	/**
	 * 后置验证
	 * @param preCheckSQL
	 * @param preCheckSQL2 
	 * @param cellNum 
	 */
	public static void afterValidate(String caseId, int cellNum, String afterCheckSQL) {
		if (StringUtils.isEmpty(afterCheckSQL) ) {
			return;
		}
		
//		System.out.println("后置验证开始，sql："+afterCheckSQL);
		//解析json
		List<SqlChecker> sqlCheckerList =  (List<SqlChecker>) JSONObject.parseArray(afterCheckSQL,SqlChecker.class);
		
		//创建一个容器，保存验证结果信息
		List<ValidateResult> validateResultList = new ArrayList<>();
		
		for (SqlChecker sqlChecker : sqlCheckerList) {
			String no = sqlChecker.getNo();
			String sql = sqlChecker.getSql();
			//期望的结果
			List<LinkedHashMap<String, String>> expectedResultList = sqlChecker.getExpectedResult();
			String expectedResultStr = JSONObject.toJSONString(expectedResultList);
			System.out.println(expectedResultStr);
			//实际的结果
			List<LinkedHashMap<String, String>> actualResultList = DBUtils.select(sql);
			String actualResultStr = JSONObject.toJSONString(actualResultList);
			System.out.println(actualResultStr);
			//判断
			if (actualResultStr.equals(expectedResultStr)) {
//				System.out.println("本条sql验证通过");
				//回写结果到excel对应的测试用例
				validateResultList.add(new ValidateResult(no, actualResultList, "success"));
			}else {
				validateResultList.add(new ValidateResult(no, actualResultList, "fail"));
//				System.out.println("验证不通过");
			}
		}
		
		String validateResultToWrite = JSONObject.toJSONString(validateResultList);
//		System.out.println("以下是要回写的结果");
//		System.out.println(validateResultToWrite);	
		
		//收集到回写数据池中间
		ExcelUtils.addCellData(new CellData(caseId, cellNum, validateResultToWrite));
		System.out.println("------------------------------------------");
	}
	
	
	//example
	public static void main(String[] args) {
		String preSql = " 	[\r\n" + 
				" 		{\r\n" + 
				"  				\"no\":\"1\",\r\n" + 
				"  				\"sql\":\"select count(1) as count from member where mobilephone='13517315669';\",\r\n" + 
				"  				\"expectedResult\":[{\"count\":\"1\"}]\r\n" + 
				" \r\n" + 
				"  		},\r\n" + 
				"  		{\r\n" + 
				"  				\"no\":\"2\",\r\n" + 
				"  				\"sql\":\"select Leaveamount as amount,Type as type from member where mobilephone='13517315669';\",\r\n" + 
				" 				\"expectedResult\":[\r\n" + 
				" 					{\"amount\":\"0.00\",\"type\":\"1\"}\r\n" + 
				" 				]\r\n" + 
				"  \r\n" + 
				"  		},\r\n" + 
				"  		{\r\n" + 
				"				\"no\":\"3\",\r\n" + 
				"				\"sql\":\"select RegName as name ,MobilePhone as phone from member where id>62  LIMIT 0,3;\",\r\n" + 
				"				\"expectedResult\":[\r\n" + 
				"					{\"name\":\"pangluo\",\"phone\":\"13212575711\"},\r\n" + 
				"					{\"name\":\"kaishui111\",\"phone\":\"13212575722\"},\r\n" + 
				"					{\"name\":\"tom_2\",\"phone\":\"13888887779\"}\r\n" + 
				"					\r\n" + 
				"				]\r\n" + 
				"		}\r\n" + 
				" 		\r\n" + 
				" 	]";
//		System.out.println(preSql);
		
//		String afterSql = "[{\"no\":\"1\",\"sql\":\"selectcount(1)frommemberwheremobilephone='13888888888';\",\"expectedResult\":[{\"count(1)\":\"1\"}]}]";
		//解析json
		List<SqlChecker> sqlCheckerList =  (List<SqlChecker>) JSONObject.parseArray(preSql,SqlChecker.class);
		
		//创建一个容器，保存验证结果信息
		List<ValidateResult> validateResultList = new ArrayList<>();
		
		for (SqlChecker sqlChecker : sqlCheckerList) {
			String no = sqlChecker.getNo();
			String sql = sqlChecker.getSql();
			//期望的结果
			List<LinkedHashMap<String, String>> expectedResultList = sqlChecker.getExpectedResult();
			String expectedResultStr = JSONObject.toJSONString(expectedResultList);
//			System.out.println(expectedResultStr);
			//实际的结果
			List<LinkedHashMap<String, String>> actualResultList = DBUtils.select(sql);
			String actualResultStr = JSONObject.toJSONString(actualResultList);
//			System.out.println(actualResultStr);
//			System.out.println("----------------------------------------------------------");
			
			// 多条sql
			/**
			 	[
			 		{"no":"1","actualResult":[{},{}],"result":"success"},
			 		{"no":"1","actualResult":[{},{}],"result":"fail"},
			 		{"no":"1","actualResult":[{},{}],"result":"fail"},
			 	]
			 */
			
			//断言
			if (actualResultStr.equals(expectedResultStr)) {
//				System.out.println("本条sql验证通过");
				//回写结果到excel对应的测试用例
				validateResultList.add(new ValidateResult(no, actualResultList, "success"));
			}else {
				validateResultList.add(new ValidateResult(no, actualResultList, "fail"));
//				System.out.println("验证不通过");
			}
		}
		
		String validateResultToWrite = JSONObject.toJSONString(validateResultList);
//		System.out.println("以下是要回写的结果");
//		System.out.println(validateResultToWrite);
		
	}

}
