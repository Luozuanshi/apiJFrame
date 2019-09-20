/**
 * 
 */
package com.domoyun.DAO.hibernate.LabelrequestRecord;

import java.util.Date;

/**
   * 项目名称：apiFrame
   *类名称：LableRequestRecord
 * @author Administrator
 * @version 1.0
   * 创建时间2019年7月12日下午12:35:28
   * 类描述
 */
public class LabelRequestRecord {
	Long id;
	String OrderID;
	String ProductName;
	String ChannelName;
	Long SerialPackageNumber;
	String TrackingNumber;
	String DeliveryMethod;
	Double Weight;
	long WeightUnit;
	Double Length;
	Double Width;
	Double Height;
	Long SizeUnit;
	String ShipFirstName;
	String ShipLastName;
	String ShipCompany;
	String ShipStreetAddress;
	String ShipStreetAddress2;
	String ShipCity;
	String ShipState;
	String ShipZIPCode;
	String ShipCountry;
	String ShipPhoneNumber;
	Boolean ShipIsResidential;
	String ShipToFirstName;
	String ShipToLastName;
	String ShipToCompany;
	String ShipToStreetAddress;
	String ShipToStreetAddress2;
	String ShipToStreetAddress3;
	String ShipToCity;
	String ShipToState;
	String ShipToZIPCode;
	String ShipToCountry;
	String ShipToPhoneNumber;
	Boolean ShipToIsResidential;
	Date CreateDate;
	Long Created;
	String PackageNumber;
	Byte LabelStatus;
	Byte SyncFailCount;
	Byte SyncStatus;
	Byte PrintLabelType;
	Long ChannelAccountInfoID;
	Boolean IsMultiPrint;
	String RedundancyField;
	Character MainID;
	Long BoxNum;
	Short LabelType;
	String timestamp;
	String LabelAccountId;
	String WayBillNumber;
	String ShipToPhoneExtension;
	String WarehouseCode;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the orderID
	 */
	public String getOrderID() {
		return OrderID;
	}



	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}



	/**
	 * @return the productName
	 */
	public String getProductName() {
		return ProductName;
	}



	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		ProductName = productName;
	}



	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return ChannelName;
	}



	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		ChannelName = channelName;
	}



	/**
	 * @return the serialPackageNumber
	 */
	public Long getSerialPackageNumber() {
		return SerialPackageNumber;
	}



	/**
	 * @param serialPackageNumber the serialPackageNumber to set
	 */
	public void setSerialPackageNumber(Long serialPackageNumber) {
		SerialPackageNumber = serialPackageNumber;
	}



	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return TrackingNumber;
	}



	/**
	 * @param trackingNumber the trackingNumber to set
	 */
	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}



	/**
	 * @return the deliveryMethod
	 */
	public String getDeliveryMethod() {
		return DeliveryMethod;
	}



	/**
	 * @param deliveryMethod the deliveryMethod to set
	 */
	public void setDeliveryMethod(String deliveryMethod) {
		DeliveryMethod = deliveryMethod;
	}



	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return Weight;
	}



	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		Weight = weight;
	}



	/**
	 * @return the weightUnit
	 */
	public long getWeightUnit() {
		return WeightUnit;
	}



	/**
	 * @param weightUnit the weightUnit to set
	 */
	public void setWeightUnit(long weightUnit) {
		WeightUnit = weightUnit;
	}



	/**
	 * @return the length
	 */
	public Double getLength() {
		return Length;
	}



	/**
	 * @param length the length to set
	 */
	public void setLength(Double length) {
		Length = length;
	}



	/**
	 * @return the width
	 */
	public Double getWidth() {
		return Width;
	}



	/**
	 * @param width the width to set
	 */
	public void setWidth(Double width) {
		Width = width;
	}



	/**
	 * @return the height
	 */
	public Double getHeight() {
		return Height;
	}



	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		Height = height;
	}



	/**
	 * @return the sizeUnit
	 */
	public Long getSizeUnit() {
		return SizeUnit;
	}



	/**
	 * @param sizeUnit the sizeUnit to set
	 */
	public void setSizeUnit(Long sizeUnit) {
		SizeUnit = sizeUnit;
	}



	/**
	 * @return the shipFirstName
	 */
	public String getShipFirstName() {
		return ShipFirstName;
	}



	/**
	 * @param shipFirstName the shipFirstName to set
	 */
	public void setShipFirstName(String shipFirstName) {
		ShipFirstName = shipFirstName;
	}



	/**
	 * @return the shipLastName
	 */
	public String getShipLastName() {
		return ShipLastName;
	}



	/**
	 * @param shipLastName the shipLastName to set
	 */
	public void setShipLastName(String shipLastName) {
		ShipLastName = shipLastName;
	}



	/**
	 * @return the shipCompany
	 */
	public String getShipCompany() {
		return ShipCompany;
	}



	/**
	 * @param shipCompany the shipCompany to set
	 */
	public void setShipCompany(String shipCompany) {
		ShipCompany = shipCompany;
	}



	/**
	 * @return the shipStreetAddress
	 */
	public String getShipStreetAddress() {
		return ShipStreetAddress;
	}



	/**
	 * @param shipStreetAddress the shipStreetAddress to set
	 */
	public void setShipStreetAddress(String shipStreetAddress) {
		ShipStreetAddress = shipStreetAddress;
	}



	/**
	 * @return the shipStreetAddress2
	 */
	public String getShipStreetAddress2() {
		return ShipStreetAddress2;
	}



	/**
	 * @param shipStreetAddress2 the shipStreetAddress2 to set
	 */
	public void setShipStreetAddress2(String shipStreetAddress2) {
		ShipStreetAddress2 = shipStreetAddress2;
	}



	/**
	 * @return the shipCity
	 */
	public String getShipCity() {
		return ShipCity;
	}



	/**
	 * @param shipCity the shipCity to set
	 */
	public void setShipCity(String shipCity) {
		ShipCity = shipCity;
	}



	/**
	 * @return the shipState
	 */
	public String getShipState() {
		return ShipState;
	}



	/**
	 * @param shipState the shipState to set
	 */
	public void setShipState(String shipState) {
		ShipState = shipState;
	}



	/**
	 * @return the shipZIPCode
	 */
	public String getShipZIPCode() {
		return ShipZIPCode;
	}



	/**
	 * @param shipZIPCode the shipZIPCode to set
	 */
	public void setShipZIPCode(String shipZIPCode) {
		ShipZIPCode = shipZIPCode;
	}



	/**
	 * @return the shipCountry
	 */
	public String getShipCountry() {
		return ShipCountry;
	}



	/**
	 * @param shipCountry the shipCountry to set
	 */
	public void setShipCountry(String shipCountry) {
		ShipCountry = shipCountry;
	}



	/**
	 * @return the shipPhoneNumber
	 */
	public String getShipPhoneNumber() {
		return ShipPhoneNumber;
	}



	/**
	 * @param shipPhoneNumber the shipPhoneNumber to set
	 */
	public void setShipPhoneNumber(String shipPhoneNumber) {
		ShipPhoneNumber = shipPhoneNumber;
	}



	/**
	 * @return the shipIsResidential
	 */
	public Boolean getShipIsResidential() {
		return ShipIsResidential;
	}



	/**
	 * @param shipIsResidential the shipIsResidential to set
	 */
	public void setShipIsResidential(Boolean shipIsResidential) {
		ShipIsResidential = shipIsResidential;
	}



	/**
	 * @return the shipToFirstName
	 */
	public String getShipToFirstName() {
		return ShipToFirstName;
	}



	/**
	 * @param shipToFirstName the shipToFirstName to set
	 */
	public void setShipToFirstName(String shipToFirstName) {
		ShipToFirstName = shipToFirstName;
	}



	/**
	 * @return the shipToLastName
	 */
	public String getShipToLastName() {
		return ShipToLastName;
	}



	/**
	 * @param shipToLastName the shipToLastName to set
	 */
	public void setShipToLastName(String shipToLastName) {
		ShipToLastName = shipToLastName;
	}



	/**
	 * @return the shipToCompany
	 */
	public String getShipToCompany() {
		return ShipToCompany;
	}



	/**
	 * @param shipToCompany the shipToCompany to set
	 */
	public void setShipToCompany(String shipToCompany) {
		ShipToCompany = shipToCompany;
	}



	/**
	 * @return the shipToStreetAddress
	 */
	public String getShipToStreetAddress() {
		return ShipToStreetAddress;
	}



	/**
	 * @param shipToStreetAddress the shipToStreetAddress to set
	 */
	public void setShipToStreetAddress(String shipToStreetAddress) {
		ShipToStreetAddress = shipToStreetAddress;
	}



	/**
	 * @return the shipToStreetAddress2
	 */
	public String getShipToStreetAddress2() {
		return ShipToStreetAddress2;
	}



	/**
	 * @param shipToStreetAddress2 the shipToStreetAddress2 to set
	 */
	public void setShipToStreetAddress2(String shipToStreetAddress2) {
		ShipToStreetAddress2 = shipToStreetAddress2;
	}



	/**
	 * @return the shipToStreetAddress3
	 */
	public String getShipToStreetAddress3() {
		return ShipToStreetAddress3;
	}



	/**
	 * @param shipToStreetAddress3 the shipToStreetAddress3 to set
	 */
	public void setShipToStreetAddress3(String shipToStreetAddress3) {
		ShipToStreetAddress3 = shipToStreetAddress3;
	}



	/**
	 * @return the shipToCity
	 */
	public String getShipToCity() {
		return ShipToCity;
	}



	/**
	 * @param shipToCity the shipToCity to set
	 */
	public void setShipToCity(String shipToCity) {
		ShipToCity = shipToCity;
	}



	/**
	 * @return the shipToState
	 */
	public String getShipToState() {
		return ShipToState;
	}



	/**
	 * @param shipToState the shipToState to set
	 */
	public void setShipToState(String shipToState) {
		ShipToState = shipToState;
	}



	/**
	 * @return the shipToZIPCode
	 */
	public String getShipToZIPCode() {
		return ShipToZIPCode;
	}



	/**
	 * @param shipToZIPCode the shipToZIPCode to set
	 */
	public void setShipToZIPCode(String shipToZIPCode) {
		ShipToZIPCode = shipToZIPCode;
	}



	/**
	 * @return the shipToCountry
	 */
	public String getShipToCountry() {
		return ShipToCountry;
	}



	/**
	 * @param shipToCountry the shipToCountry to set
	 */
	public void setShipToCountry(String shipToCountry) {
		ShipToCountry = shipToCountry;
	}



	/**
	 * @return the shipToPhoneNumber
	 */
	public String getShipToPhoneNumber() {
		return ShipToPhoneNumber;
	}



	/**
	 * @param shipToPhoneNumber the shipToPhoneNumber to set
	 */
	public void setShipToPhoneNumber(String shipToPhoneNumber) {
		ShipToPhoneNumber = shipToPhoneNumber;
	}



	/**
	 * @return the shipToIsResidential
	 */
	public Boolean getShipToIsResidential() {
		return ShipToIsResidential;
	}



	/**
	 * @param shipToIsResidential the shipToIsResidential to set
	 */
	public void setShipToIsResidential(Boolean shipToIsResidential) {
		ShipToIsResidential = shipToIsResidential;
	}



	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return CreateDate;
	}



	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}



	/**
	 * @return the created
	 */
	public Long getCreated() {
		return Created;
	}



	/**
	 * @param created the created to set
	 */
	public void setCreated(Long created) {
		Created = created;
	}



	/**
	 * @return the packageNumber
	 */
	public String getPackageNumber() {
		return PackageNumber;
	}



	/**
	 * @param packageNumber the packageNumber to set
	 */
	public void setPackageNumber(String packageNumber) {
		PackageNumber = packageNumber;
	}



	/**
	 * @return the labelStatus
	 */
	public Byte getLabelStatus() {
		return LabelStatus;
	}



	/**
	 * @param labelStatus the labelStatus to set
	 */
	public void setLabelStatus(Byte labelStatus) {
		LabelStatus = labelStatus;
	}



	/**
	 * @return the syncFailCount
	 */
	public Byte getSyncFailCount() {
		return SyncFailCount;
	}



	/**
	 * @param syncFailCount the syncFailCount to set
	 */
	public void setSyncFailCount(Byte syncFailCount) {
		SyncFailCount = syncFailCount;
	}



	/**
	 * @return the syncStatus
	 */
	public Byte getSyncStatus() {
		return SyncStatus;
	}



	/**
	 * @param syncStatus the syncStatus to set
	 */
	public void setSyncStatus(Byte syncStatus) {
		SyncStatus = syncStatus;
	}



	/**
	 * @return the printLabelType
	 */
	public Byte getPrintLabelType() {
		return PrintLabelType;
	}



	/**
	 * @param printLabelType the printLabelType to set
	 */
	public void setPrintLabelType(Byte printLabelType) {
		PrintLabelType = printLabelType;
	}



	/**
	 * @return the channelAccountInfoID
	 */
	public Long getChannelAccountInfoID() {
		return ChannelAccountInfoID;
	}



	/**
	 * @param channelAccountInfoID the channelAccountInfoID to set
	 */
	public void setChannelAccountInfoID(Long channelAccountInfoID) {
		ChannelAccountInfoID = channelAccountInfoID;
	}



	/**
	 * @return the isMultiPrint
	 */
	public Boolean getIsMultiPrint() {
		return IsMultiPrint;
	}



	/**
	 * @param isMultiPrint the isMultiPrint to set
	 */
	public void setIsMultiPrint(Boolean isMultiPrint) {
		IsMultiPrint = isMultiPrint;
	}



	/**
	 * @return the redundancyField
	 */
	public String getRedundancyField() {
		return RedundancyField;
	}



	/**
	 * @param redundancyField the redundancyField to set
	 */
	public void setRedundancyField(String redundancyField) {
		RedundancyField = redundancyField;
	}



	/**
	 * @return the mainID
	 */
	public Character getMainID() {
		return MainID;
	}



	/**
	 * @param mainID the mainID to set
	 */
	public void setMainID(Character mainID) {
		MainID = mainID;
	}



	/**
	 * @return the boxNum
	 */
	public Long getBoxNum() {
		return BoxNum;
	}



	/**
	 * @param boxNum the boxNum to set
	 */
	public void setBoxNum(Long boxNum) {
		BoxNum = boxNum;
	}



	/**
	 * @return the labelType
	 */
	public Short getLabelType() {
		return LabelType;
	}



	/**
	 * @param labelType the labelType to set
	 */
	public void setLabelType(Short labelType) {
		LabelType = labelType;
	}



	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}



	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	/**
	 * @return the labelAccountId
	 */
	public String getLabelAccountId() {
		return LabelAccountId;
	}



	/**
	 * @param labelAccountId the labelAccountId to set
	 */
	public void setLabelAccountId(String labelAccountId) {
		LabelAccountId = labelAccountId;
	}



	/**
	 * @return the wayBillNumber
	 */
	public String getWayBillNumber() {
		return WayBillNumber;
	}



	/**
	 * @param wayBillNumber the wayBillNumber to set
	 */
	public void setWayBillNumber(String wayBillNumber) {
		WayBillNumber = wayBillNumber;
	}



	/**
	 * @return the shipToPhoneExtension
	 */
	public String getShipToPhoneExtension() {
		return ShipToPhoneExtension;
	}



	/**
	 * @param shipToPhoneExtension the shipToPhoneExtension to set
	 */
	public void setShipToPhoneExtension(String shipToPhoneExtension) {
		ShipToPhoneExtension = shipToPhoneExtension;
	}



	/**
	 * @return the warehouseCode
	 */
	public String getWarehouseCode() {
		return WarehouseCode;
	}



	/**
	 * @param warehouseCode the warehouseCode to set
	 */
	public void setWarehouseCode(String warehouseCode) {
		WarehouseCode = warehouseCode;
	}



	@Override
	public String toString() {
		return "LableRequestRecord [id=" + id + ", OrderID=" + OrderID + ", ProductName=" + ProductName
				+ ", ChannelName=" + ChannelName + ", SerialPackageNumber=" + SerialPackageNumber + ", TrackingNumber="
				+ TrackingNumber + ", DeliveryMethod=" + DeliveryMethod + ", Weight=" + Weight + ", WeightUnit="
				+ WeightUnit + ", Length=" + Length + ", Width=" + Width + ", Height=" + Height + ", SizeUnit="
				+ SizeUnit + ", ShipFirstName=" + ShipFirstName + ", ShipLastName=" + ShipLastName + ", ShipCompany="
				+ ShipCompany + ", ShipStreetAddress=" + ShipStreetAddress + ", ShipStreetAddress2="
				+ ShipStreetAddress2 + ", ShipCity=" + ShipCity + ", ShipState=" + ShipState + ", ShipZIPCode="
				+ ShipZIPCode + ", ShipCountry=" + ShipCountry + ", ShipPhoneNumber=" + ShipPhoneNumber
				+ ", ShipIsResidential=" + ShipIsResidential + ", ShipToFirstName=" + ShipToFirstName
				+ ", ShipToLastName=" + ShipToLastName + ", ShipToCompany=" + ShipToCompany + ", ShipToStreetAddress="
				+ ShipToStreetAddress + ", ShipToStreetAddress2=" + ShipToStreetAddress2 + ", ShipToStreetAddress3="
				+ ShipToStreetAddress3 + ", ShipToCity=" + ShipToCity + ", ShipToState=" + ShipToState
				+ ", ShipToZIPCode=" + ShipToZIPCode + ", ShipToCountry=" + ShipToCountry + ", ShipToPhoneNumber="
				+ ShipToPhoneNumber + ", ShipToIsResidential=" + ShipToIsResidential + ", CreateDate=" + CreateDate
				+ ", Created=" + Created + ", PackageNumber=" + PackageNumber + ", LabelStatus=" + LabelStatus
				+ ", SyncFailCount=" + SyncFailCount + ", SyncStatus=" + SyncStatus + ", PrintLabelType="
				+ PrintLabelType + ", ChannelAccountInfoID=" + ChannelAccountInfoID + ", IsMultiPrint=" + IsMultiPrint
				+ ", RedundancyField=" + RedundancyField + ", MainID=" + MainID + ", BoxNum=" + BoxNum + ", LabelType="
				+ LabelType + ", timestamp=" + timestamp + ", LabelAccountId=" + LabelAccountId + ", WayBillNumber="
				+ WayBillNumber + ", ShipToPhoneExtension=" + ShipToPhoneExtension + ", WarehouseCode=" + WarehouseCode
				+ "]";
	}
	
}
