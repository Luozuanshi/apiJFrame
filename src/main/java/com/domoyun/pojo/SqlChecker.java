package com.domoyun.pojo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 描述前置或者后置验证sql的pojo类
 * @author pangluo
 * @date 2018年12月5日
 * @desc 
 * @email
 */
public class SqlChecker {
	
	private String no;
	
	private String sql;
	
	private List<LinkedHashMap<String, String>> expectedResult;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<LinkedHashMap<String, String>> getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(List<LinkedHashMap<String, String>> expectedResult) {
		this.expectedResult = expectedResult;
	}

	@Override
	public String toString() {
		return "SqlChecker [no=" + no + ", sql=" + sql + ", expectedResult=" + expectedResult + "]";
	}

}
