package com.domoyun.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domoyun.pojo.ApiInfo;
import com.domoyun.pojo.bean.PrintLabelBean;

/**
 * 	表关联
 *	 项目名称：apiFrame
 *	类名称：Distribute
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月12日下午3:23:21
 * 	类描述：
 */
public class Distribute {

	static Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
	
	static Map<String, PrintLabelBean> apiDetailMap = new HashMap<>();

	static {
		//重新把apiinfo信息封装成map
		List<ApiInfo> objectList = (List<ApiInfo>) ExcelUtils.readExcel("/apibatch.xlsx", "api_info", ApiInfo.class);
		for (ApiInfo apiInfo : objectList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		//重新把把apiDetail信息封装成map
//		List<ApiDetail> apiDetailList = (List<ApiDetail>) ExcelUtils.readExcel("/apibatch.xlsx", "request_data",ApiDetail.class);
//		for (ApiDetail apiDetail : apiDetailList) {
//			apiDetailMap.put(apiDetail.getCaseId(), apiDetail);
//		}
		
//		List<ApiDetail> getCountryList = (List<ApiDetail>) ExcelUtils.readExcel("/apibatch.xlsx", "getCountry",ApiDetail.class);
//		for (ApiDetail apiDetail : apiDetailList) {
//			apiDetailMap.put(apiDetail.getCaseId(), apiDetail);
//		}

	}

	/**
	 * 通过apiId获得对应的url
	 * @param apiId
	 * @return
	 */
	public static String getUrlByApiId(String apiId) {
		return apiInfoMap.get(apiId).getUrl();
	}

	/**
	 * 通过apiId获得请求方法：get or post
	 * @param apiId
	 * @return
	 */
	public static String getRequestMethodByApiId(String apiId) {
		return apiInfoMap.get(apiId).getType();
	}

	public static int getRequestMethodByApiId(PrintLabelBean obj){
		return Integer.valueOf(obj.getApiId());
	}

	public static void main(String[] args) {
		System.out.println(apiDetailMap.get("10086").getRowNum());
	}
	
	/**
	 * 通过caseId获得行号
	 * @param caseId
	 * @return
	 */
	public static int getRowNumByCaseId(String caseId){
		return apiDetailMap.get(caseId).getRowNum();
	}

	public static int getRowNumByCaseId(PrintLabelBean obj){
		return Integer.valueOf(obj.getCaseId());
	}

}
