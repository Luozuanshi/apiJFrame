/**
 * 
 */
package com.domoyun.dataprovider;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.domoyun.InterfaceAbstract.ExcelObject;
import com.domoyun.hibernate.LabelrequestRecord.LabelRequestRecord;
import com.domoyun.hibernate.LabelrequestRecord.SelectSQL;
import com.domoyun.pojo.InterfaceT.ApiDetail;
import com.domoyun.routine.DingdingMessage;
import com.domoyun.util.ExcelUtils;


/**
 *	 项目名称：apiFrame
 *	类名称：dataprovider
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月16日上午9:25:56
 * 	类描述：每个sheet数据提供者
 */
public class DataProviderClass {
	static DingdingMessage message = new DingdingMessage(0);
	
	@DataProvider(name="getCountry")
	public static Object[][] getCountry(Method m){
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("apibatch.xlsx","getCountry", ApiDetail.class);
		int size = objectList.size();
		System.out.println(size);
		System.out.println(objectList);
		//创建一个容器--》数据提供者需要的二维数组--》只要获得需要的信息即可
		Object[][] datas = new Object[size][9];
		
		for(int i=0;i<size;i++){
			ApiDetail apiDetail = (ApiDetail) objectList.get(i);
			datas[i][0] = apiDetail.getCaseId();
			datas[i][1] = apiDetail.getApiId();
			datas[i][2] = apiDetail.getRequestData();
			datas[i][3] = apiDetail.getExpectedReponseData();
			datas[i][4] = apiDetail.getPreCheckSQL();
			datas[i][5] = apiDetail.getAfterCheckSQL();
			datas[i][6] = m.getName();
			datas[i][7] = size;
			datas[i][8]=apiDetail.getUseCaseTitle();
		}
		return datas;
	}
	
	@DataProvider(name="getRegion")
	public static Object[][] getRegion(Method m){
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("apibatch.xlsx","getRegion", ApiDetail.class);
		int size = objectList.size();
		System.out.println(size);
		System.out.println(objectList);
		//创建一个容器--》数据提供者需要的二维数组--》只要获得需要的信息即可
		Object[][] datas = new Object[size][8];
		
		for(int i=0;i<size;i++){
			ApiDetail apiDetail = (ApiDetail) objectList.get(i);
			datas[i][0] = apiDetail.getCaseId();
			datas[i][1] = apiDetail.getApiId();
			datas[i][2] = apiDetail.getRequestData();
			datas[i][3] = apiDetail.getExpectedReponseData();
			datas[i][4] = apiDetail.getPreCheckSQL();
			datas[i][5] = apiDetail.getAfterCheckSQL();
			datas[i][6] = m.getName();
			datas[i][7] = size;
		}
		return datas;
	}
	
	@DataProvider(name="CancelLale")
	public static Object[][] CancelLale(Method m){
		List<LabelRequestRecord> objectList = SelectSQL.demo9();
		System.out.println(objectList);
		int size = objectList.size();
		System.out.println(size);
		if (size==0) {
			
			message.RobotMarkdown("当天取消单", "### **当天测试单**  `@所有人`\r\n\n\n" + 
					"- 预报订单数量: `"+size+"` \r\n" );
		}
		System.out.println(objectList);
		//创建一个容器--》数据提供者需要的二维数组--》只要获得需要的信息即可
		Object[][] datas = new Object[size][7];
		
		for(int i=0;i<size;i++){
			LabelRequestRecord LabelRequestRecord = (LabelRequestRecord) objectList.get(i);
			datas[i][0] = LabelRequestRecord.getOrderID();
			datas[i][1] = LabelRequestRecord.getTrackingNumber();
			datas[i][2] = LabelRequestRecord.getWayBillNumber();
			datas[i][3] = LabelRequestRecord.getChannelName();
			datas[i][4] = LabelRequestRecord.getWarehouseCode();
			datas[i][5] = m.getName();
			datas[i][6] = size;
		}
		return datas;
		
	
		
	}
}
