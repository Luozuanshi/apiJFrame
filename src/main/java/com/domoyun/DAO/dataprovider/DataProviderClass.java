/**
 * 
 */
package com.domoyun.DAO.dataprovider;

import java.lang.reflect.Method;
import java.util.List;

import com.domoyun.pojo.bean.CancelLabelBean;
import com.domoyun.pojo.bean.PrintLabelBean;
import org.testng.annotations.DataProvider;

import com.domoyun.InterfaceAbstract.ExcelObject;
import com.domoyun.DAO.hibernate.LabelrequestRecord.LabelRequestRecord;
import com.domoyun.DAO.hibernate.LabelrequestRecord.SelectSQL;
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
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("apibatch.xlsx","getCountry", PrintLabelBean.class);
		int size = objectList.size();
		System.out.println(size);
		System.out.println(objectList);
		//创建一个容器--》数据提供者需要的二维数组--》只要获得需要的信息即可
		Object[][] datas = new Object[size][9];
		
		for(int i=0;i<size;i++){
			PrintLabelBean printLabelBean = (PrintLabelBean) objectList.get(i);
			datas[i][0] = printLabelBean.getCaseId();
			datas[i][1] = printLabelBean.getApiId();
			datas[i][2] = printLabelBean.getRequestData();
			datas[i][3] = printLabelBean.getExpectedReponseData();
			datas[i][4] = printLabelBean.getPreCheckSQL();
			datas[i][5] = printLabelBean.getAfterCheckSQL();
			datas[i][6] = m.getName();
			datas[i][7] = size;
			datas[i][8] =  printLabelBean.getUseCaseTitle();
		}
		return datas;
	}
	
	@DataProvider(name="getRegion")
	public static Object[][] getRegion(Method m){
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("apibatch.xlsx","getRegion", PrintLabelBean.class);
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
	
	@DataProvider(name="CancelLabel")
	public static Object[][] CancelLabel(Method m){
		List<LabelRequestRecord> DatebaseObjectList = SelectSQL.demo9();
		List<ExcelObject> objectsList = (List<ExcelObject>) ExcelUtils.readExcel("apibatch.xlsx","CancelLabel", CancelLabelBean.class);
		System.out.println(DatebaseObjectList);
		int size = DatebaseObjectList.size();
		System.out.println(size);
		if (size==0) {
			
			message.RobotMarkdown("当天取消单", "### **当天测试单**  `@所有人`\r\n\n\n" + 
					"- 预报订单数量: `"+size+"` \r\n" );
		}

		System.out.println(DatebaseObjectList);

		//创建一个容器--》数据提供者需要的二维数组--》只要获得需要的信息即可
		Object[][] datas = new Object[size][10];

		for(int i=0;i<size;i++){
			LabelRequestRecord LabelRequestRecord = (LabelRequestRecord) DatebaseObjectList.get(i);
			CancelLabelBean cancelLabelBean =(CancelLabelBean)  objectsList.get(i);

			datas[i][0] = LabelRequestRecord.getOrderID();
			datas[i][1] = LabelRequestRecord.getTrackingNumber();
			datas[i][2] = LabelRequestRecord.getWayBillNumber();
			datas[i][3] = LabelRequestRecord.getChannelName();
			datas[i][4] = LabelRequestRecord.getWarehouseCode();
			datas[i][5]=  String.valueOf(LabelRequestRecord.getCreated());
			datas[i][6] = m.getName();
			datas[i][7] = size;
			datas[i][8] = cancelLabelBean.getCaseId();
			datas[i][9] = cancelLabelBean.getApiId();
		}
		return datas;
		
	
		
	}
}
