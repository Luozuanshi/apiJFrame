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
	private String OrderID;
	private String TrackingNumber;
	private String WayBillNumber;
	private String ChannelName;
	private String WarehouseCode;
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

	
	
	/**
	 * @param sheetName
	 * @param caseId
	 * @param cellNum
	 * @param result
	 * @param assertresult
	 * @param filepath
	 * @param fileName
	 * @param orderID
	 * @param trackingNumber
	 * @param wayBillNumber
	 * @param channelName
	 * @param warehouseCode
	 */
	public CellData(String sheetName, String caseId, int[] cellNum, String result, String assertresult, String filepath,
			String fileName, String orderID, String trackingNumber, String wayBillNumber, String channelName,
			String warehouseCode) {
		super();
		this.sheetName = sheetName;
		this.caseId = caseId;
		this.cellNum = cellNum;
		this.result = result;
		this.assertresult = assertresult;
		this.filepath = filepath;
		this.fileName = fileName;
		this.OrderID = orderID;
		this.TrackingNumber = trackingNumber;
		this.WayBillNumber = wayBillNumber;
		this.ChannelName = channelName;
		this.WarehouseCode = warehouseCode;
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
	
	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getTrackingNumber() {
		return TrackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}

	public String getWayBillNumber() {
		return WayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		WayBillNumber = wayBillNumber;
	}

	public String getChannelName() {
		return ChannelName;
	}

	public void setChannelName(String channelName) {
		ChannelName = channelName;
	}

	public String getWarehouseCode() {
		return WarehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		WarehouseCode = warehouseCode;
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
				+ fileName + ", OrderID=" + OrderID + ", TrackingNumber=" + TrackingNumber + ", WayBillNumber="
				+ WayBillNumber + ", ChannelName=" + ChannelName + ", WarehouseCode=" + WarehouseCode + "]\n";
	}
	
}
