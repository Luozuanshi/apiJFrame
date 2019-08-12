package com.domoyun.InterfaceAbstract;

import com.domoyun.pojo.bean.CancelLabelBean;
import com.domoyun.util.ExcelUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class WriteCollectionImp implements WriteCollection{
	Object obj;

	WriteCollectionImp(Object obj){
		this.obj =obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	@Override
	public void addData(WriteCollection WriteCollection) {
		cellDatasToWriteList.add(WriteCollection);
	}

	@Override
	public List<CancelLabelBean> getData() {
		List<CancelLabelBean> tempCellDatas = new ArrayList<>();
		for(int i=0;i<cellDatasToWriteList.size();i++){
			CancelLabelBean example = (CancelLabelBean) cellDatasToWriteList.get(i);
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
	public List<CancelLabelBean> getDatasToWriteList(String sheetName) {
		return (List<CancelLabelBean>) cellDatasToWriteMap.get(sheetName);
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
}
