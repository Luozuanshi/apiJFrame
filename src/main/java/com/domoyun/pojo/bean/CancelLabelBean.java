package com.domoyun.pojo.bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.domoyun.InterfaceAbstract.ExcelObject;
import com.domoyun.InterfaceAbstract.WriteCollection;
import com.domoyun.base.Base;
import com.domoyun.util.ExcelUtils;

/**
 * 	要写回excel数据描述
 * @author pangluo
 * @date 2018年12月3日
 * @desc 
 * @email
 */
public class CancelLabelBean extends ExcelObject implements WriteCollection{
	private String caseId;
	private String ApiId;
	private String OrderID;
	private String TrackingNumber;
	private String WayBillNumber;
	private String ChannelName;
	private String WarehouseCode;
	private String CancelResult;
	
	/**
	 * 
	 */
	public CancelLabelBean() {
	}

	public CancelLabelBean(String caseId, int[] cellNum, String orderID, String trackingNumber, String wayBillNumber,
			String channelName, String warehouseCode,String cancelResult) {
		super.setCellNum(cellNum);
		this.caseId = caseId;
		this.OrderID = orderID;
		this.TrackingNumber = trackingNumber;
		this.WayBillNumber = wayBillNumber;
		this.ChannelName = channelName;
		this.WarehouseCode = warehouseCode;
		this.CancelResult= cancelResult;
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

	public String getCaseId() {
		return caseId;
	}

	public String getApiId() {
		return ApiId;
	}

	public String getOrderID() {
		return OrderID;
	}

	public String getTrackingNumber() {
		return TrackingNumber;
	}

	public String getWayBillNumber() {
		return WayBillNumber;
	}

	public String getChannelName() {
		return ChannelName;
	}

	public String getWarehouseCode() {
		return WarehouseCode;
	}

	public String getCancelResult() {
		return CancelResult;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public void setApiId(String apiId) {
		ApiId = apiId;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		WayBillNumber = wayBillNumber;
	}

	public void setChannelName(String channelName) {
		ChannelName = channelName;
	}

	public void setWarehouseCode(String warehouseCode) {
		WarehouseCode = warehouseCode;
	}

	public void setCancelResult(String cancelResult) {
		CancelResult = cancelResult;
	}

	@Override
	public String toString() {
		return "CancelLabelBean{" +
				"caseId='" + caseId + '\'' +
				", ApiId='" + ApiId + '\'' +
				", OrderID='" + OrderID + '\'' +
				", TrackingNumber='" + TrackingNumber + '\'' +
				", WayBillNumber='" + WayBillNumber + '\'' +
				", ChannelName='" + ChannelName + '\'' +
				", WarehouseCode='" + WarehouseCode + '\'' +
				", CancelResult='" + CancelResult + '\'' +
				"} " + super.toString();
	}
}
