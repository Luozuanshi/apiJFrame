package com.domoyun.testCase;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.domoyun.InterfaceAbstract.WriteCollection;
import com.domoyun.base.Base;
import com.domoyun.dataprovider.DataProviderClass;
import com.domoyun.pojo.bean.CancelLabelBean;
import com.domoyun.routine.DingdingMessage;


/**
 * 	测试类	
 * @author pangluo
 * @date 2018年12月5日
 * @desc 测试类
 * @email
 */
public class CancelLabel extends Base{
	//数据收集器
	WriteCollection WriteCollecter =new CancelLabelBean();
	DingdingMessage message = new DingdingMessage(0);
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
		
		WriteCollecter.addData(new CancelLabelBean("", cell, OrderID, TrackingNumber, WayBillNumber, ChannelName, WarehouseCode));
	
		++ExcuteCount;
		//5.数据写出 全局静态变量 cellDatasToWriteMap
		
		if (sheetNumMaxsize==ExcuteCount) {
			WriteCollecter.putmap("CancelLable");
			message.RobotMarkdown("当天取消单", "### **当天测试单**  `@所有人`\r\n\n\n" + 
					"- 预报订单数量: `"+sheetNumMaxsize+"` \r\n" );
			
		}
		Assert.assertEquals("a", "a");
	} 
	
}
	
