package com.domoyun.pojo;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 描述单条sql验证结果信息的pojo类
 * @author pangluo
 * @date 2018年12月5日
 * @desc 
 * @email 
 */
public class ValidateResult {
	/**
	  	 	[
				 		{"no":"1","actualResult":[{},{}],"result":"success"},
				 		{"no":"1","actualResult":[{},{}],"result":"fail"},
				 		{"no":"1","actualResult":[{},{}],"result":"fail"},
				 	]
	 */

	private String no;
	private List<LinkedHashMap<String, String>> actualResult;
	private String result;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public List<LinkedHashMap<String, String>> getActualResult() {
		return actualResult;
	}

	public void setActualResult(List<LinkedHashMap<String, String>> actualResult) {
		this.actualResult = actualResult;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ValidateResult(String no, List<LinkedHashMap<String, String>> actualResult, String result) {
		super();
		this.no = no;
		this.actualResult = actualResult;
		this.result = result;
	}

	@Override
	public String toString() {
		return "ValidateResult [no=" + no + ", actualResult=" + actualResult + ", result=" + result + "]";
	}

}
