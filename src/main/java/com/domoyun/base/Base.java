package com.domoyun.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.domoyun.InterfaceAbstract.WriteCollection;
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
		
		//静态数据准备
		ExcelUtils reader = new ExcelUtils();
		ParameterUtils.addGlobalData("appToken", reader.readExcelCell(3, 6));
		System.out.println(ParameterUtils.getGlobalData("appToken"));
		ParameterUtils.addGlobalData("appKey", reader.readExcelCell(4, 6));
		System.out.println(ParameterUtils.getGlobalData("appKey"));
	}
	
	@AfterSuite
	public static void afterSuite() {
		//一次性数据回写	
		ExcelUtils.batchWrite("apibatch.xlsx","target/classes/result.xlsx");
	}
	
}
