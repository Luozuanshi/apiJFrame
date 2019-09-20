package com.domoyun.pojo.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.domoyun.InterfaceAbstract.ExcelObject;
import com.domoyun.InterfaceAbstract.WriteCollection;
import com.domoyun.InterfaceAbstract.WriteCollectionImp;

/**
 * 	要写回excel数据描述
 * @author pangluo
 * @date 2018年12月3日
 * @desc 
 * @email
 */
public class PrintLabelBean extends WriteCollectionImp {
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
	private String WTactualReponseData;
	//assertResult(断言结果)
	private String WTassertResult;
	//assertResult(BASE64圖片)
	private String WTbase64img;

	//	PreCheckSQL(前置验证SQL)
	private String preCheckSQL;
	//	PreCheckResult(前置验证结果)
	private String preCheckResult;
	//	AfterCheckSQL(后置验证SQL)
	private String afterCheckSQL;
	//	AfterCheckResult(后置验证结果)
	private String afterCheckResult;

	public PrintLabelBean() {

	}
	
	public PrintLabelBean(String caseId, int[] cellNum, String result, String assertresult) {
		super.setCellNum(cellNum);;
		this.caseId = caseId;
		this.WTactualReponseData = result;
		this.WTassertResult = assertresult;
	}

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

	public String getWTactualReponseData() {
		return WTactualReponseData;
	}

	public void setWTactualReponseData(String WTactualReponseData) {
		this.WTactualReponseData = WTactualReponseData;
	}

	public String getWTassertResult() {
		return WTassertResult;
	}

	public void setWTassertResult(String WTassertResult) {
		this.WTassertResult = WTassertResult;
	}

	public String getWTbase64img() {
		return WTbase64img;
	}

	public void setWTbase64img(String WTbase64img) {
		this.WTbase64img = WTbase64img;
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

	public static void main(String[] args) {
		int[] arry0=new int[]{1,2,3,4,5};
		int[] arry1=new int[]{1,2,3,4};
		PrintLabelBean printLabelBean = new PrintLabelBean("1",arry0, "ture", "pass");
//		CancelLabelBean cancelLabelBean = new CancelLabelBean("2",arry1, "fedex123", "123456", "ydcn123456", "fedex", "ca","取消成功");
		System.out.println(printLabelBean);
//		System.out.println(cancelLabelBean);
	}


	@Override
	public String toString() {
		return "PrintLabelBean{" +
				"caseId='" + caseId + '\'' +
				", apiId='" + apiId + '\'' +
				", isExcute='" + isExcute + '\'' +
				", useCaseTitle='" + useCaseTitle + '\'' +
				", requestData='" + requestData + '\'' +
				", expectedReponseData='" + expectedReponseData + '\'' +
				", actualReponseData='" + WTactualReponseData + '\'' +
				", assertResult='" + WTassertResult + '\'' +
				", base64img='" + WTbase64img + '\'' +
				", preCheckSQL='" + preCheckSQL + '\'' +
				", preCheckResult='" + preCheckResult + '\'' +
				", afterCheckSQL='" + afterCheckSQL + '\'' +
				", afterCheckResult='" + afterCheckResult + '\'' +
				"} " + super.toString();
	}
}
