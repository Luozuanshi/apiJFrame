package com.domoyun.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.domoyun.base.WriteCollection;
import com.domoyun.util.ExcelUtils;

/**
 * 	要写回excel数据描述
 * @author pangluo
 * @date 2018年12月3日
 * @desc 
 * @email
 */
public class CellData implements WriteCollection{
	private String sheetName;
	private String caseId;
	private int[] cellNum;
	private String result;
	private String assertresult;
	private String filepath;
	private String fileName;

	@Override
	public void addCellData(CellData cellData) {
		cellDatasToWriteList.add(cellData);	
	}

	@Override
	public List<CellData> getCellData() {
		List<CellData> tempCellDatas = new ArrayList<>();
		for(int i=0;i<cellDatasToWriteList.size();i++){
			CellData example = cellDatasToWriteList.get(i);
			tempCellDatas.add(example);
	    }
		return tempCellDatas;
	}

	@Override
	public List<CellData> getCellDatasToWriteList(String sheetName) {
		return cellDatasToWriteMap.get(sheetName);
	}

	@Override
	public void putmap(String sheetName) {
		cellDatasToWriteMap.put(sheetName, getCellData());
		clearlist();
	}

	@Override
	public void clearlist() {
		cellDatasToWriteList.clear();
	};
	
	public CellData(String sheetName,String caseId, int[] cellNum, String result, String assertresult,String filePath,String filename) {
		super();
		this.sheetName = sheetName;
		this.caseId = caseId;
		this.cellNum = cellNum;
		this.result = result;
		this.assertresult = assertresult;
		this.filepath = filePath;
		this.fileName =filename;
	}

	public CellData(){
		
	}
	
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
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
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "CellData [sheetName=" + sheetName + ", caseId=" + caseId + ", cellNum=" + Arrays.toString(cellNum)
				+ ", result=" + result + ", assertresult=" + assertresult + ", filepath=" + filepath + ", fileName="
				+ fileName + "]";
	}
	
}
