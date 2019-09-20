package com.domoyun.InterfaceAbstract;

import com.domoyun.pojo.bean.CancelLabelBean;
import com.domoyun.util.ExcelUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class WriteCollectionImp extends ExcelObject implements WriteCollection{
	Object obj;
	private int[] cellNum;

	public WriteCollectionImp(){
	}

	public WriteCollectionImp(Object obj){
		this.obj =obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	@Override
	public void addData(WriteCollection writeCollection) {
		cellDatasToWriteList.add(writeCollection);
	}

	@Override
	public List<WriteCollection> getData() {
		List<WriteCollection> tempCellDatas = new ArrayList<>();
		for(int i=0;i<cellDatasToWriteList.size();i++){
			WriteCollection example = (WriteCollection) cellDatasToWriteList.get(i);
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
	public List<WriteCollection> getDatasToWriteList(String sheetName) {
		return (List<WriteCollection>) cellDatasToWriteMap.get(sheetName);
	}

	@Override
	public void clearlist() {
		cellDatasToWriteList.clear();
	}

	@Override
	public void batchWrite(String sourceExcelPath, String targetExcelPath) {
		Class<ExcelUtils> ExcelUtils = ExcelUtils.class;
		try {
			Method method = ExcelUtils.getDeclaredMethod("batchWrite", String.class,String.class);
			method.invoke(sourceExcelPath, targetExcelPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] getCellNum() {
		return cellNum;
	}

	public void setCellNum(int[] cellNum) {
		this.cellNum = cellNum;
	}

	@Override
	public String toString() {
		return "WriteCollectionImp{" +
				"obj=" + obj +
				", cellNum=" + Arrays.toString(cellNum) +
				'}';
	}
}
