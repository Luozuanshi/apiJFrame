package com.domoyun.testCase;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.domoyun.base.Base;
import com.domoyun.base.WriteCollection;
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
public class SS extends Base{
	//数据收集器
	WriteCollection WriteCollecter =new CellData();
	int ExcuteCount =0;
	@Test(dataProvider="CancelLale",dataProviderClass=DataProviderClass.class)//dataProvider="datas"
	public void CancelLale(String OrderID,String TrackingNumber,
			String WayBillNumber,String ChannelName,String WarehouseCode,String sheetname,int sheetNumMaxsize) throws ClientProtocolException, IOException, InterruptedException {
		//1：准备url
		//2：发包,得到响应结果
//		System.out.println(actualResult);
		//3.请求数据断言
		
		  
		  
		  
		//4：要写的数据的收集
		int[] cell={3,4,5,6};
		
		WriteCollecter.addCellData(new CellData("", "", cell, "", "", "", "", OrderID, TrackingNumber, WayBillNumber, ChannelName, WayBillNumber));
	
		++ExcuteCount;
		//5.数据写出 全局静态变量 cellDatasToWriteMap
		if (sheetNumMaxsize ==ExcuteCount) {
			
			WriteCollecter.putmap("CancelLable");
		}
	} 
	
}
	
