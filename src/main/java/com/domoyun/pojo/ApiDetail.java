package com.domoyun.pojo;

import com.domoyun.InterfaceAbstract.ExcelObject;

/**
 * 	描述第二张sheet
 * @author pangluo
 * @date 2018年11月30日
 * @desc 
 * @email
 */
public class ApiDetail extends ExcelObject{
	//	CaseId(用例编号)
	private String caseId;
	//	ApiId(接口编号)
	private String apiId;
	//	IsExcute(是否执行)
	private String isExcute;
	//	UseCaseTitle(用例标题)
	private String useCaseTitle;
	//	RequestData(接口请求参数)
	private String requestData;
	//	ExpectedReponseData(期望响应数据)
	private String expectedReponseData;
	//ActualReponseData(实际响应数据)
	private String actualReponseData;
	//断言结果
	private String assertResult;
	
	private String base64img;
	
//	PreCheckSQL(前置验证SQL)
	private String preCheckSQL;
//	PreCheckResult(前置验证结果)
	private String preCheckResult;
//	AfterCheckSQL(后置验证SQL)
	private String afterCheckSQL;
//	AfterCheckResult(后置验证结果)
	private String afterCheckResult;


	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getIsExcute() {
		return isExcute;
	}

	public void setIsExcute(String isExcute) {
		this.isExcute = isExcute;
	}
	
	public String getUseCaseTitle() {
		return useCaseTitle;
	}

	public void setUseCaseTitle(String useCaseTitle) {
		this.useCaseTitle = useCaseTitle;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getExpectedReponseData() {
		return expectedReponseData;
	}

	public void setExpectedReponseData(String expectedReponseData) {
		this.expectedReponseData = expectedReponseData;
	}
	
	public String getAssertResult() {
		return assertResult;
	}

	public void setAssertResult(String assertResult) {
		this.assertResult = assertResult;
	}

	public String getBase64img() {
		return base64img;
	}

	public void setBase64img(String base64img) {
		this.base64img = base64img;
	}

	public String getPreCheckSQL() {
		return preCheckSQL;
	}

	public void setPreCheckSQL(String preCheckSQL) {
		this.preCheckSQL = preCheckSQL;
	}

	public String getPreCheckResult() {
		return preCheckResult;
	}

	public void setPreCheckResult(String preCheckResult) {
		this.preCheckResult = preCheckResult;
	}

	public String getAfterCheckSQL() {
		return afterCheckSQL;
	}

	public void setAfterCheckSQL(String afterCheckSQL) {
		this.afterCheckSQL = afterCheckSQL;
	}

	public String getAfterCheckResult() {
		return afterCheckResult;
	}

	public void setAfterCheckResult(String afterCheckResult) {
		this.afterCheckResult = afterCheckResult;
	}

	public String getActualReponseData() {
		return actualReponseData;
	}

	public void setActualReponseData(String actualReponseData) {
		this.actualReponseData = actualReponseData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApiDetail [caseId=" + caseId + ", apiId=" + apiId + ", isExcute=" + isExcute + ", useCaseTitle="
				+ useCaseTitle + ", requestData=" + requestData + ", expectedReponseData=" + expectedReponseData
				+ ", actualReponseData=" + actualReponseData + ", assertResult=" + assertResult + ", base64img="
				+ base64img + ", preCheckSQL=" + preCheckSQL + ", preCheckResult=" + preCheckResult + ", afterCheckSQL="
				+ afterCheckSQL + ", afterCheckResult=" + afterCheckResult + ", getSheetName()=" + getSheetName()
				+ ", getRowNum()=" + getRowNum() + ", getCellNum()=" + getCellNum() + ", getFileName()=" + getFileName()
				+ ", getFilePath()=" + getFilePath() + ", getMaxRowNum()=" + getMaxRowNum() + ", getMaxCellNum()="
				+ getMaxCellNum() + "]\n";
	}

	
}
