package com.domoyun.InterfaceAbstract;

/**
 * excel所有描述sheet的基类
 * @author pangluo
 * @date 2018年12月3日
 * @desc 
 * @email
 */
public abstract class ExcelObject {
	private String sheetName;
	private int rowNum;
	private int cellNum;
	private String fileName;
	private String filePath;
	private int maxRowNum;
	private int maxCellNum;
	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * @return the rowNum
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum the rowNum to set
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * @return the cellNum
	 */
	public int getCellNum() {
		return cellNum;
	}
	/**
	 * @param cellNum the cellNum to set
	 */
	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the maxRowNum
	 */
	public int getMaxRowNum() {
		return maxRowNum;
	}
	/**
	 * @param maxRowNum the maxRowNum to set
	 */
	public void setMaxRowNum(int maxRowNum) {
		this.maxRowNum = maxRowNum;
	}
	/**
	 * @return the maxCellNum
	 */
	public int getMaxCellNum() {
		return maxCellNum;
	}
	/**
	 * @param maxCellNum the maxCellNum to set
	 */
	public void setMaxCellNum(int maxCellNum) {
		this.maxCellNum = maxCellNum;
	}
	
	
}
