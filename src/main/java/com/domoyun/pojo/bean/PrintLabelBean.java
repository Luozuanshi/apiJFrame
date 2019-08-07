package com.domoyun.pojo.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.domoyun.InterfaceAbstract.ExcelObject;
import com.domoyun.InterfaceAbstract.WriteCollection;
import com.domoyun.util.ExcelUtils;

/**
 * 	要写回excel数据描述
 * @author pangluo
 * @date 2018年12月3日
 * @desc 
 * @email
 */
public class PrintLabelBean extends ExcelObject implements WriteCollection{
	private String caseId;
	private String result;
	private String assertresult;

	public PrintLabelBean() {
		
	}
	
	public PrintLabelBean(String caseId, int[] cellNum, String result, String assertresult) {
		super.setCellNum(cellNum);;
		this.caseId = caseId;
		this.result = result;
		this.assertresult = assertresult;
	}
	
	public String getCaseId() {
		return caseId;
	}

	@Override
	public String toString() {
		return "PrintLabelBean [caseId=" + caseId + ", result=" + result + ", assertresult=" + assertresult
				+ ", getCellNum()=" + Arrays.toString(getCellNum()) + "]";
	}



	@Override
	public void addData(WriteCollection WriteCollection) {
		cellDatasToWriteList.add(WriteCollection);
	}
	
	@Override
	public List<PrintLabelBean> getData() {
		List<PrintLabelBean> tempCellDatas = new ArrayList<>();
		for(int i=0;i<cellDatasToWriteList.size();i++){
			PrintLabelBean example = (PrintLabelBean) cellDatasToWriteList.get(i);
			tempCellDatas.add(example);
	    }
		return tempCellDatas;
	}

	@Override
	public void putmap(String sheetName) {
		cellDatasToWriteMap.put(sheetName, getData());
		clearlist();
	}
	
	@Override
	public List<PrintLabelBean> getDatasToWriteList(String sheetName) {
		return (List<PrintLabelBean>) cellDatasToWriteMap.get(sheetName);
	}

	@Override
	public void clearlist() {
		cellDatasToWriteList.clear();		
	}

	@Override
	public void batchWrite(String sourceExcelPath, String targetExcelPath) {
		
	}

	public static void main(String[] args) {
		int[] arry0=new int[]{1,2,3,4,5};
		int[] arry1=new int[]{1,2,3,4};
		PrintLabelBean printLabelBean = new PrintLabelBean("1",arry0, "ture", "pass");
		CancelLabelBean cancelLabelBean = new CancelLabelBean("2",arry1, "fedex123", "123456", "ydcn123456", "fedex", "ca");
		System.out.println(printLabelBean);
		System.out.println(cancelLabelBean);
	}
}
