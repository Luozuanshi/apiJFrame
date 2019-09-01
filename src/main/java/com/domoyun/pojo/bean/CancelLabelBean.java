package com.domoyun.pojo.bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.domoyun.InterfaceAbstract.ExcelObject;
import com.domoyun.InterfaceAbstract.WriteCollection;
import com.domoyun.InterfaceAbstract.WriteCollectionImp;
import com.domoyun.base.Base;
import com.domoyun.util.ExcelUtils;

/**
 * 	要写回excel数据描述
 * @author pangluo
 * @date 2018年12月3日
 * @desc 
 * @email
 */
public class CancelLabelBean extends WriteCollectionImp {
	private String caseId;
	private String ApiId;
	private String WTOrderID;
	private String WTTrackingNumber;
	private String WTWayBillNumber;
	private String WTChannelName;
	private String WTWarehouseCode;
	private String WTCancelResult;

	public CancelLabelBean() {
		super();
	}


	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getApiId() {
		return ApiId;
	}

	public void setApiId(String apiId) {
		ApiId = apiId;
	}

	public String getWTOrderID() {
		return WTOrderID;
	}

	public void setWTOrderID(String WTOrderID) {
		this.WTOrderID = WTOrderID;
	}

	public String getWTTrackingNumber() {
		return WTTrackingNumber;
	}

	public void setWTTrackingNumber(String WTTrackingNumber) {
		this.WTTrackingNumber = WTTrackingNumber;
	}

	public String getWTWayBillNumber() {
		return WTWayBillNumber;
	}

	public void setWTWayBillNumber(String WTWayBillNumber) {
		this.WTWayBillNumber = WTWayBillNumber;
	}

	public String getWTChannelName() {
		return WTChannelName;
	}

	public void setWTChannelName(String WTChannelName) {
		this.WTChannelName = WTChannelName;
	}

	public String getWTWarehouseCode() {
		return WTWarehouseCode;
	}

	public void setWTWarehouseCode(String WTWarehouseCode) {
		this.WTWarehouseCode = WTWarehouseCode;
	}

	public String getWTCancelResult() {
		return WTCancelResult;
	}

	public void setWTCancelResult(String WTCancelResult) {
		this.WTCancelResult = WTCancelResult;
	}

	public CancelLabelBean(String caseId, String apiId, String orderID, String WTTrackingNumber, String WTWayBillNumber, String WTChannelName, String WTWarehouseCode, String WTCancelResult) {
		this.caseId = caseId;
		this.ApiId = apiId;
		this.WTOrderID = orderID;
		this.WTTrackingNumber = WTTrackingNumber;
		this.WTWayBillNumber = WTWayBillNumber;
		this.WTChannelName = WTChannelName;
		this.WTWarehouseCode = WTWarehouseCode;
		this.WTCancelResult = WTCancelResult;
	}


	@Override
	public String toString() {
		return "CancelLabelBean{" +
				"caseId='" + caseId + '\'' +
				", ApiId='" + ApiId + '\'' +
				", OrderID='" + WTOrderID + '\'' +
				", TrackingNumber='" + WTTrackingNumber + '\'' +
				", WayBillNumber='" + WTWayBillNumber + '\'' +
				", ChannelName='" + WTChannelName + '\'' +
				", WarehouseCode='" + WTWarehouseCode + '\'' +
				", CancelResult='" + WTCancelResult + '\'' +
				"} " + super.toString();
	}
}
