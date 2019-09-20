package com.domoyun.pojo;

/**
 *	 项目名称：apiFrame
 *	类名称：Sheet
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月12日下午4:06:59
 * 	类描述：
 */
public class Sheet {
	public String sheetName;
	public String sheetNum;
	
	public String getSheetName() {
		return sheetName;
	}
	
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	public String getSheetNum() {
		return sheetNum;
	}
	
	public void setSheetNum(String sheetNum) {
		this.sheetNum = sheetNum;
	}
	
	@Override
	public String toString() {
		return "Sheet [sheetName=" + sheetName + ", sheetNum=" + sheetNum + "]";
	}
	
	
}
