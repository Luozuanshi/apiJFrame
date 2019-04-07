package com.lemon.api.auto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lemon.api.auto.pojo.ApiDetail;
import com.lemon.api.auto.pojo.ApiInfo;
import com.lemon.api.auto.pojo.ExcelObject;

public class ApiUtils {

	static Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
	
	static Map<String, ApiDetail> apiDetailMap = new HashMap<>();

	static {
		//重新把apiinfo信息封装成map
		List<ApiInfo> objectList = (List<ApiInfo>) ExcelUtils.readExcel("/api.xlsx", 1, ApiInfo.class);
		for (ApiInfo apiInfo : objectList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		//重新把把apiDetail信息封装成map
		List<ApiDetail> apiDetailList = (List<ApiDetail>) ExcelUtils.readExcel("/api.xlsx", 2,ApiDetail.class);
		for (ApiDetail apiDetail : apiDetailList) {
			apiDetailMap.put(apiDetail.getCaseId(), apiDetail);
		}

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

}
