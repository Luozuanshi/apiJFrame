package com.lemon.api.auto.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.lemon.api.auto.util.ExcelUtils;
import com.lemon.api.auto.util.ParameterUtils;

public class BaseTester {
	
	@BeforeSuite
	public void BeforeSuite(){
		//数据准备
		//查询出一个最大的手机号，然后+1
		ParameterUtils.addGlobalData("mobilephone", "13666666668");
	}
	
	@AfterSuite
	public void afterSuite(){
		ExcelUtils.batchWrite("/api.xlsx","target/classes/api.xlsx",2);
	}

}
