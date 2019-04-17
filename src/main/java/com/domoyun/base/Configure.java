package com.domoyun.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domoyun.pojo.ApiDetail;
import com.domoyun.pojo.ApiInfo;
import com.domoyun.pojo.CellData;
import com.domoyun.pojo.ExcelObject;
import com.domoyun.util.ExcelUtils;
/**
 * 	 配置信息Sheet 表关联
 *	 项目名称：apiFrame
 *	类名称：ApiUtils
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月12日下午3:23:21
 * 	类描述：读取表格配置信息
 */
public class Configure {

	static Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
	
//	static Map<String, ApiDetail> apiDetailMap = new HashMap<>();

	static {
		//重新把apiinfo信息封装成map
		List<ApiInfo> objectList = (List<ApiInfo>) ExcelUtils.readExcel("/apibatch.xlsx", "api_info", ApiInfo.class);
		for (ApiInfo apiInfo : objectList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
	}

	/**
	 * 通过ApiInfo-apiId获得对应的url
	 * @param apiId
	 * @return
	 */
	public static String getUrlByApiId(String apiId) {
		return apiInfoMap.get(apiId).getUrl();
	}

	/**
	 * 通过ApiInfo-apiId获得请求方法：get or post
	 * @param apiId
	 * @return
	 */
	public static String getRequestMethodByApiId(String apiId) {

		return apiInfoMap.get(apiId).getType();
	}
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 通过caseId获得行号
	 * @param caseId
	 * @return
	 */
	public static int getRowNumByCaseId(ApiDetail obj){
		return Integer.valueOf(obj.getCaseId());
	}

	/**
	 * 通过caseId获得行号
	 * @param caseId
	 * @return
	 */
	public static int getRowNumByCaseId(CellData obj){
		return Integer.valueOf(obj.getCaseId());
	}

}
