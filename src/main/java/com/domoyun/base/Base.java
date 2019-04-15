package com.domoyun.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.domoyun.util.ExcelUtils;
import com.domoyun.util.LoggerControler;
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
	final static LoggerControler log = LoggerControler.getLogger(Base.class);
	@BeforeSuite
	public void BeforeSuite(){
		
		//静态数据准备
		ParameterUtils.addGlobalData("mobilephone", "13111111111");
		log.info("数据准备");
		
	}
	
	@AfterSuite
	public static void afterSuite() {
			
		ExcelUtils.batchWrite("/apibatch.xlsx","target/classes/result.xlsx");
		log.info("数据写出");
	}
	

	
//	@AfterClass
//	public void AfterClass() {
//		ExcelUtils.batchWrite("/apibatch.xlsx","target/classes/result.xlsx",ExcelUtils.sheetName);
//		System.out.println("数据写出");
//	}


}
