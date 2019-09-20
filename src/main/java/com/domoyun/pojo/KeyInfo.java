package com.domoyun.pojo;
/**
 * 
 * 	项目名称：apiFrame
 *	类名称：KeyInfo
 * @author Test
 * @version 1.0
   * 创建时间2019年4月9日上午10:47:45
   * 类描述 ：通过jsonpath方式存取json数据
 */
public class KeyInfo {
	
	private String jsonPath;
	
	private String value;

	public String getJsonPath() {
		return jsonPath;
	}

	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
