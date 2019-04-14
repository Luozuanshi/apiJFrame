package com.domoyun.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.domoyun.util.ExcelUtils;
import com.domoyun.util.ParameterUtils;
/**
 * 	
 *	 项目名称：apiFrame
 *	类名称：Base
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月9日下午7:33:17
 * 	类描述：参数化数据准备,套件运行之后数据回写
 */
public class Base {
	
	
	@BeforeSuite
	public void BeforeSuite(){
		//数据准备
		//查询出一个最大的手机号，然后+1
		ParameterUtils.addGlobalData("mobilephone", "13666666668");
		System.out.println("数据准备");
	}
	
	@AfterSuite
	public static void afterSuite() {
			
		ExcelUtils.batchWrite("/apibatch.xlsx","target/classes/result.xlsx");
		System.out.println("数据写出");
		
		
	}
	

	
//	@AfterClass
//	public void AfterClass() {
//		ExcelUtils.batchWrite("/apibatch.xlsx","target/classes/result.xlsx",ExcelUtils.sheetName);
//		System.out.println("数据写出");
//	}


}
