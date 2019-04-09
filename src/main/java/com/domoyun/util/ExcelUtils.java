package com.domoyun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import com.domoyun.pojo.ApiDetail;
import com.domoyun.pojo.CellData;
import com.domoyun.pojo.ExcelObject;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	/**
	 * 要写的cell数据池
	 */
	private static List<CellData> cellDatasToWriteList = new ArrayList<>();
	
	/**
	 * 添加要回写的数据
	 * @param cellData
	 */
	public static void addCellData(CellData cellData) {
		cellDatasToWriteList.add(cellData);
	}
	
	
	/**
	 * 获得所有要写的celldata数据
	 * @return
	 */
	public static List<CellData> getCellDatasToWriteList() {
		return cellDatasToWriteList;
	}



	/**
	 * 读取excel
	 * @param excelPath 路径
	 * @param sheetNum sheet的编号
	 * @param clazz pojo类的字节码对象
	 * @return
	 */
	public static List<? extends ExcelObject> readExcel(String excelPath, int sheetNum, Class<? extends ExcelObject> clazz) {
		//容器创建出来
		List<ExcelObject> objList = new ArrayList<>();
		
		try {
			InputStream inp = ExcelUtils.class.getResourceAsStream(excelPath);
			//获得工作簿对象
			Workbook workbook = WorkbookFactory.create(inp);
			//获得第一个sheet
			Sheet sheet = workbook.getSheetAt(sheetNum - 1);
			//遍历--》思路应该怎么样？？
			//通过遍历拿到所有的行--》通过遍历拿到每一行的列
			//获得最后的行号(行的索引，从0开始)
			int lastRowNum = sheet.getLastRowNum();
			//获得最大的列号
			Row firstRow = sheet.getRow(0);
			//获得最大的列数
			int lastCellNum = firstRow.getLastCellNum();
			
			//创建一个数组，保存表头
			String[] columnNameArray = new String[lastCellNum];
			
			//获得表头--遍历第一行的每一列
			for(int k = 0; k< lastCellNum; k++){
				//获得当前行的每一列
				Cell cell = firstRow.getCell(k, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//设置列的类型
				cell.setCellType(CellType.STRING);
				//获得该列的值
				String columnName = cell.getStringCellValue();
				//放到容器中去
				columnNameArray[k] = columnName;
			}

			//遍历每一行(i相当于行索引,第一行不要)
			for (int i = 1; i <= lastRowNum; i++) {
				//通过字节码对象实例化一个对象：
				ExcelObject obj = clazz.newInstance();
				//设置行号
				obj.setRowNum(i + 1);
				//获得索引对应的行
				Row row = sheet.getRow(i);
				// 遍历每一列（j相当于列号）
				for (int j = 0; j < lastCellNum; j++) {
					//获得当前行的每一列
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//设置列的类型
					cell.setCellType(CellType.STRING);
					//获得该列的值
					String cellValue = cell.getStringCellValue();
					//给apiinfo的各个属性进行设值
					//获得此列的表头
					String columnName = columnNameArray[j];
					//得到setter方法的名称
					String setterMethodName = "set" + columnName.substring(0, columnName.indexOf("("));
					//得到setter方法
					Method setterMethod = clazz.getMethod(setterMethodName, String.class);
					
					//原始字符串的参数的替换
					String commonStr = ParameterUtils.getCommonStr(cellValue);
					
					//反射调用该方法
//					setterMethod.invoke(obj, cellValue);
					setterMethod.invoke(obj, commonStr);
				}
//				System.out.println(apiInfo);
				//添加到容器
				objList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return objList;
	}

	
	@Deprecated //过期了
	public static List<ApiDetail> readExcel3(String excelPath, int sheetNum) {
		
		//得到ApiInfo的字节码对象
		Class clazz = ApiDetail.class;
		
		List<ApiDetail> apiInfoList = new ArrayList<>();
		
		try {
			InputStream inp = ExcelUtils.class.getResourceAsStream(excelPath);
			//获得工作簿对象
			Workbook workbook = WorkbookFactory.create(inp);
			//获得第一个sheet
			Sheet sheet = workbook.getSheetAt(sheetNum - 1);
			//遍历--》思路应该怎么样？？
			//通过遍历拿到所有的行--》通过遍历拿到每一行的列
			//获得最后的行号(行的索引，从0开始)
			int lastRowNum = sheet.getLastRowNum();
			//获得最大的列号
			Row firstRow = sheet.getRow(0);
			//获得最大的列数
			int lastCellNum = firstRow.getLastCellNum();
			
			//创建一个数组，保存表头
			String[] columnNameArray = new String[lastCellNum];
			
			//获得表头--遍历第一行的每一列
			for(int k = 0; k< lastCellNum; k++){
				//获得当前行的每一列
				Cell cell = firstRow.getCell(k, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//设置列的类型
				cell.setCellType(CellType.STRING);
				//获得该列的值
				String columnName = cell.getStringCellValue();
				//放到容器中去
				columnNameArray[k] = columnName;
			}

			//遍历每一行(i相当于行号,第一行不要)
			for (int i = 1; i <= lastRowNum; i++) {
				ApiDetail apiDetail = new ApiDetail();
				//获得索引对应的行
				Row row = sheet.getRow(i);
				// 遍历每一列（j相当于列号）
				for (int j = 0; j < lastCellNum; j++) {
					//获得当前行的每一列
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//设置列的类型
					cell.setCellType(CellType.STRING);
					//获得该列的值
					String cellValue = cell.getStringCellValue();
					//给apiinfo的各个属性进行设值
					//获得此列的表头
					String columnName = columnNameArray[j];
					//得到setter方法的名称
					String setterMethodName = "set" + columnName.substring(0, columnName.indexOf("("));
					//得到setter方法
					Method setterMethod = clazz.getMethod(setterMethodName, String.class);
					//反射调用该方法
					setterMethod.invoke(apiDetail, cellValue);
				}
				System.out.println(apiDetail);
				apiInfoList.add(apiDetail);
			}
		}catch(Exception e){
			
		}
		return apiInfoList;
	}
	/**
	 * 
	 * @param excelPath  excel的路径
	 * @param sheetNum  sheet编号，从1开始,传1表示第1个sheet，即索引为0的sheet
	 * @return
	 */
	public static Object[][] readExcel(String excelPath, int sheetNum) {
		//创建一个Object类型的二维数组，保存从excel解析出来的行列数据
		Object[][] datas = null;
		try {
			InputStream inp = ExcelUtils.class.getResourceAsStream(excelPath);
			//获得工作簿对象
			Workbook workbook = WorkbookFactory.create(inp);
			//获得第一个sheet
			Sheet sheet = workbook.getSheetAt(sheetNum - 1);
			//遍历--》思路应该怎么样？？
			//通过遍历拿到所有的行--》通过遍历拿到每一行的列
			//获得最后的行号(行的索引，从0开始)
			int lastRowNum = sheet.getLastRowNum();
			//获得最大的列号
			Row firstRow = sheet.getRow(0);
			//获得最大的列数
			int lastCellNum = firstRow.getLastCellNum();

			datas = new Object[lastRowNum][];
			//遍历每一行(i相当于行号,第一行不要)
			for (int i = 1; i <= lastRowNum; i++) {
				//获得索引对应的行
				Row row = sheet.getRow(i);
				//创建一个一维数组，保存该行的所有列信息
				Object[] cellValueArray = new Object[lastCellNum];
				// 遍历每一列（j相当于列号）
				for (int j = 0; j < lastCellNum; j++) {
					//获得当前行的每一列
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//设置列的类型
					cell.setCellType(CellType.STRING);
					//获得该列的值
					String cellValue = cell.getStringCellValue();
					//把当前列的数据添加到cellValueArray
					cellValueArray[j] = cellValue;
				}
				//每遍历一行，要把该行对应的一维数组添加进去
				datas[i - 1] = cellValueArray;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datas;
	}

	//example :一定要在工具类写一个main方法--》方便调试和后期的维护
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		/*Object[][] datas = readExcel("/api.xlsx", 2);
		for (Object[] cellValueArray : datas) {
			for (Object cellValue : cellValueArray) {
				System.out.print("["+cellValue + "]    ");
			}
			System.out.println();
		}*/
		
		/*String jsonStr = "{\"mobilephone\":\"13517315669\",\"pwd\":\"123456\",\"regname\":\"柠檬班\"}";
		Map<String, String> dataMap = (Map<String, String>) JSONObject.parse(jsonStr);
		Set<String> keySet = dataMap.keySet();
		for (String key : keySet) {
			System.out.println(key+":" + dataMap.get(key));
		}*/
		
//		 readExcel2("/api.xlsx", 1);
		
//		 readExcel3("/api.xlsx", 2);
		
		List<ApiDetail> objList = (List<ApiDetail>) readExcel("/api.xlsx",2, ApiDetail.class);
		for (Object obj : objList) {
			System.out.println(obj);
		}
		
		//测试写回
//		writeExcel("/api.xlsx", 2, "1", 5, "");
	}

	/**
	 * 写回数据到excel
	 * 性能问题：
	 * 	1：如果写1000次数据，就要io操作1000次
	 * 		--》只读写一次可以ok了（把所有的要写的相关信息先收集起来，所有测试用例执行完毕之后再写）
	 * 		往第1行第1列写入aaa
	 * 		往第1行第2列写入bbb
	 * 		往第2行第1列写入ccc
	 * 		往第2行第5列写入ddd
	 * 		往第3行第10列写入eee
	 *  2：如果caseId对应的行比较靠后，前面遍历次数会非常多（已解决）
	 *  	--》告诉我一个caseId，就能拿到对应的行（rowNum）
	 * @param excelPath
	 * @param sheetNum
	 * @param caseId
	 * @param cellNum
	 * @param result
	 */
	@Deprecated
	public static void writeExcel(String excelPath, int sheetNum, String caseId, int cellNum, String result) {
		InputStream inp  = null;
		Workbook workbook  = null;
		OutputStream outputStream = null;
		try {
			
//			inp = ExcelUtils.class.getResourceAsStream(excelPath);
			inp = new FileInputStream(new File(excelPath));
			//获得工作簿对象
			workbook = WorkbookFactory.create(inp);
			//获得对应编号的sheet
			Sheet sheet = workbook.getSheetAt(sheetNum - 1);
			//获得最大的行号
			int lastRowNum = sheet.getLastRowNum();
			
			//得到行号
			int rowNum = ApiUtils.getRowNumByCaseId(caseId);
			//得到对应的行
			Row row = sheet.getRow(rowNum - 1);
			//拿到要写数据的列
			Cell cellToWrite = row.getCell(cellNum - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cellToWrite.setCellType(CellType.STRING);
			cellToWrite.setCellValue(result);
			
			//第一种方式：遍历每一行
		/*	for(int i=1;i<=lastRowNum;i++){
				//拿到当前的行
				Row row = sheet.getRow(i);
				//拿到当前行的第一列
				Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				//获得当前列的数据
				String firstCellValue = cell.getStringCellValue();
				//判断第一列的数据是不是caseId
				//如果caseId等于第一列数据--》就是这一行
				if (caseId.equals(firstCellValue)) {
					Cell cellToWrite = row.getCell(cellNum - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cellToWrite.setCellType(CellType.STRING);
					cellToWrite.setCellValue(result);
					//已经匹配上了，跳出循环
					break;
				}
			}*/
			
			 
			outputStream = new FileOutputStream(new File(excelPath));
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream !=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (inp != null) {
				try {
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 批量回写
	 * @param string
	 * @param i
	 */
	public static void batchWrite(String sourceExcelPath,String targetExcelPath, int sheetNum) {
		InputStream inp  = null;
		Workbook workbook  = null;
		OutputStream outputStream = null;
		try {
			
			inp = ExcelUtils.class.getResourceAsStream(sourceExcelPath);
			//获得工作簿对象
			workbook = WorkbookFactory.create(inp);
			//获得对应编号的sheet
			Sheet sheet = workbook.getSheetAt(sheetNum - 1);
			//获得最大的行号
			int lastRowNum = sheet.getLastRowNum();
			
			//拿出所有要回写的数据
			List<CellData> cellDataToWriteList = ExcelUtils.getCellDatasToWriteList();
			for (CellData cellData : cellDataToWriteList) {
				//得到行号
				int rowNum = ApiUtils.getRowNumByCaseId(cellData.getCaseId());
				//得到对应的行
				Row row = sheet.getRow(rowNum - 1);
				//拿到要写数据的列
				Cell cellToWrite = row.getCell(cellData.getCellNum() - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToWrite.setCellType(CellType.STRING);
				cellToWrite.setCellValue(cellData.getResult());
			}
			
			
			//第一种方式：遍历每一行
		/*	for(int i=1;i<=lastRowNum;i++){
				//拿到当前的行
				Row row = sheet.getRow(i);
				//拿到当前行的第一列
				Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				//获得当前列的数据
				String firstCellValue = cell.getStringCellValue();
				//判断第一列的数据是不是caseId
				//如果caseId等于第一列数据--》就是这一行
				if (caseId.equals(firstCellValue)) {
					Cell cellToWrite = row.getCell(cellNum - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cellToWrite.setCellType(CellType.STRING);
					cellToWrite.setCellValue(result);
					//已经匹配上了，跳出循环
					break;
				}
			}*/
			 
			outputStream = new FileOutputStream(new File(targetExcelPath));
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream !=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (inp != null) {
				try {
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
