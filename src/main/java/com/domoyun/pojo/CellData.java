package com.domoyun.pojo;

/**
 * 要写回excel数据
 * @author pangluo
 * @date 2018年12月3日
 * @desc 
 * @email
 */
public class CellData {

	private String caseId;
	private int[] cellNum;
	private String result;
	private String assertresult;

	public CellData(String caseId, int[] cellNum, String result, String assertresult) {
		super();
		this.caseId = caseId;
		this.cellNum = cellNum;
		this.result = result;
		this.assertresult = assertresult;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public int[] getCellNum() {
		return cellNum;
	}

	public void setCellNum(int[] cellNum) {
		this.cellNum = cellNum;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getAssertresult() {
		return assertresult;
	}

	public void setAssertresult(String assertresult) {
		this.assertresult = assertresult;
	}

	@Override
	public String toString() {
		return "CellData [caseId=" + caseId + ", cellNum=" + cellNum + ", result=" + result + ", assertresult="
				+ assertresult + "]";
	}

}
