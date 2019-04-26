/**
 * 
 */
package com.domoyun.base;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.domoyun.pojo.ApiDetail;
import com.domoyun.pojo.ExcelObject;
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
	
	@DataProvider(name="getCountry")
	public static Object[][] getCountry(Method m){
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("/apibatch.xlsx","getCountry", ApiDetail.class);
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
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("/apibatch.xlsx","getRegion", ApiDetail.class);
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
	
	@DataProvider(name="getRegionForReceiving")
	public static Object[][] getRegionForReceiving(Method m){
		List<ExcelObject> objectList = (List<ExcelObject>) ExcelUtils.readExcel("/apibatch.xlsx","getRegionForReceiving", ApiDetail.class);
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
}
