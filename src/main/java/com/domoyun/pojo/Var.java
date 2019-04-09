package com.domoyun.pojo;

/**
 * 参数化--》接口关联
 * @author pangluo
 * @date 2018年12月10日
 * @desc 
 * @email
 */
public class Var {
	private String jsonPath;
	
	private String key; //-->保存到全局数据池中的key

	public String getJsonPath() {
		return jsonPath;
	}

	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
