package com.domoyun.testCase;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.domoyun.InterfaceAbstract.WriteCollection;
import com.domoyun.base.Base;
import com.domoyun.DAO.dataprovider.Configure;
import com.domoyun.DAO.dataprovider.DataProviderClass;
import com.domoyun.pojo.bean.CancelLabelBean;
import com.domoyun.util.HttpUtils;


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
<<<<<<< HEAD
//	DingdingMessage message = new DingdingMessage(0);
=======
	DingdingMessage message = new DingdingMessage(0);
>>>>>>> cf06e432d150d4db741840ad2fa4c35b6da63fb6
	int ExcuteCount =0;


	@Test(dataProvider="CancelLabel",dataProviderClass=DataProviderClass.class)//dataProvider="datas"
	public void CancelLabel(String OrderID,String TrackingNumber,
			String WayBillNumber,String ChannelName,String WarehouseCode,String created,String sheetname,int sheetNumMaxsize,String caseId,String apiId) throws ClientProtocolException, IOException, InterruptedException {

		//1：准备url
		String url = Configure.getUrlByApiId(apiId);
<<<<<<< HEAD
		//读取用戶配置
//		String Authorization = readproperties(created);

		String requestData = "{\n" +
				"  \"Data\": {\n" +
				"    \"orderID\": \"" + OrderID + "\",\n" +
				"    \"trackingNumber\": \"" + TrackingNumber + "\"\n" +
=======
        //读取用戶配置
		String Authorization = readproperties(created);

		String requestData ="{\n" +
				"  \"Data\": {\n" +
				"    \"orderID\": \""+OrderID+"\",\n" +
				"    \"trackingNumber\": \""+TrackingNumber+"\"\n" +
>>>>>>> cf06e432d150d4db741840ad2fa4c35b6da63fb6
				"  },\n" +
				"  \"RequestTime\": \"2018-06-13T10:35:59.9024253Z\",\n" +
				"  \"Version\": \"0.0.0.3\"\n" +
				"}";
<<<<<<< HEAD
		//发包,得到响应结果
//		String actualResult = HttpUtils.request(apiId, url, requestData, sheetname, Authorization);
//		System.out.println(actualResult);
		//断言
		String assertstString = null;

//		if (!(actualResult.isEmpty())) {
//			assertstString = (actualResult.contains("ShortMessage\": \"订单无法取消") ? "取消失败" : "取消成功");
//		} else {
//			assertstString = "响应结果为空";
//		}


		WriteCollecter.addData(new CancelLabelBean("", "", OrderID, TrackingNumber, WayBillNumber, ChannelName, WarehouseCode, assertstString));
		++ExcuteCount;

		//5.数据写出 全局静态变量 cellDatasToWriteMap 发送钉钉消息
		if (sheetNumMaxsize == ExcuteCount) {
			WriteCollecter.putmap("CancelLabel");
////			message.RobotMarkdown("当天取消单", "### **当天测试单**  `@所有人`\r\n\n\n" +
//					"- 预报订单数量: `"+sheetNumMaxsize+"` \r\n" );
//					}
		}
	}
}
	//扩展方法
//	public String readproperties(String x){
//		Properties properties = new Properties();
//		try {
//			properties.load(CancelLabel.class.getResourceAsStream("/config/Created.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String haha = properties.getProperty(x);
//		return haha;
//	}


=======
        //发包,得到响应结果
		String actualResult = HttpUtils.request(apiId,url,requestData,sheetname,Authorization);
//		System.out.println(actualResult);
		//断言
		String assertstString =null;

		if (!(actualResult.isEmpty())) {
			assertstString = (actualResult.contains("ShortMessage\": \"订单无法取消")?"取消失败":"取消成功");
		}
		else {
			assertstString = "响应结果为空"; }


		  
		WriteCollecter.addData(new CancelLabelBean("", "",OrderID, TrackingNumber, WayBillNumber, ChannelName, WarehouseCode,assertstString));
		++ExcuteCount;

		//5.数据写出 全局静态变量 cellDatasToWriteMap 发送钉钉消息
        if (sheetNumMaxsize==ExcuteCount) {
			WriteCollecter.putmap("CancelLabel");
			message.RobotMarkdown("当天取消单", "### **当天测试单**  `@所有人`\r\n\n\n" + 
					"- 预报订单数量: `"+sheetNumMaxsize+"` \r\n" );
					}
	} 

	//扩展方法
	public String readproperties(String x){
		Properties properties = new Properties();
		try {
			properties.load(CancelLabel.class.getResourceAsStream("/config/Created.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String haha = properties.getProperty(x);
		return haha;
	}

}
>>>>>>> cf06e432d150d4db741840ad2fa4c35b6da63fb6
	
