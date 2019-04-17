package com.domoyun.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.domoyun.base.Configure;
import com.domoyun.pojo.ApiDetail;
import com.domoyun.pojo.CellData;
import com.domoyun.pojo.ExcelObject;


/**
 * sheet程序是从0开始的 项目名称：apiFrame 类名称：ExcelUtils
 * 
 * @author Test
 * @version 1.0 创建时间2019年4月10日下午2:55:25 类描述：
 */
public class ExcelUtils {
	/**
	 * 要写的cell数据池
	 * cellDatasToWriteList临时中转数据用的对象集合-list<CellData>
	 * cellDatasToWriteMap以键值对的形式存储各个sheet对象集合list<CellData>
	 */
	private static List<CellData> cellDatasToWriteList = new ArrayList<>();
	private static Map<String,List<CellData>>  cellDatasToWriteMap = new HashMap<String, List<CellData>>();
	
	/**
	 * 	添加要回写的数据
	 * @param cellData
	 */
	public static void addCellData(CellData cellData) {
		cellDatasToWriteList.add(cellData);
	}

	/**
	 * 取出所有收集的数据
	 * @return
	 */
	public static List<CellData> getCellData() {
		List<CellData> tempCellDatas = new ArrayList<>();
		for(int i=0;i<cellDatasToWriteList.size();i++){
			CellData example = cellDatasToWriteList.get(i);
			tempCellDatas.add(example);
	    }
		return tempCellDatas;
	}
	
	/**
	 * 获得不同sheetName要写的celldata数据
	 * @return
	 */
	public static List<CellData> getCellDatasToWriteList(String sheetName) {
		return cellDatasToWriteMap.get(sheetName);
	}
	
	/**
	 * 通过getCellData()取出所有收集的对象集合数据，put进cellDatasToWriteMap
	 * @param sheetName
	 */
	public static void putmap(String sheetName) {
		cellDatasToWriteMap.put(sheetName, ExcelUtils.getCellData());
		ExcelUtils.clearlist();
	}
	
	/**
	 * 清除cellDatasToWriteList（临时中转数据用的对象集合-list<CellData>）
	 */
	public static void clearlist() {
		cellDatasToWriteList.clear();
	}

	//单元格读取
    public String readExcelCell(int rowIndex, int cellIndex){
    	rowIndex-=1;
    	cellIndex-=1;
    	InputStream inp = ExcelUtils.class.getResourceAsStream("/apibatch.xlsx");
		// 获得工作簿对象
		Workbook workbook =null;
		try {
			workbook = WorkbookFactory.create(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获得第一个sheet
		Sheet sheet = workbook.getSheetAt(0);
		
    	Row row = sheet.getRow(rowIndex);
        if (row == null) {
            System.out.println("第"+rowIndex+"行不存在");
            return  null;
        }
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
        	System.out.println("第"+cellIndex+"列不存在");
            return  null;
        }
        cell.setCellType(CellType.STRING);
        String cellvalue = cell.getStringCellValue();
        return cellvalue;
    }
	
	
	/**
	 * 读取excel
	 * @param excelPath 路径
	 * @param sheetNum  sheet的编号
	 * @param clazz     pojo类的字节码对象
	 * @return
	 */
	public static List<? extends ExcelObject> readExcel(String excelPath, String sheetName,
			Class<? extends ExcelObject> clazz) {
		// 容器创建出来
		List<ExcelObject> objList = new ArrayList<>();

		try {
			InputStream inp = ExcelUtils.class.getResourceAsStream(excelPath);
			// 获得工作簿对象
			Workbook workbook = WorkbookFactory.create(inp);
			// 获得第一个sheet
			Sheet sheet = workbook.getSheet(sheetName);
			// 遍历--》思路应该怎么样？？
			// 通过遍历拿到所有的行--》通过遍历拿到每一行的列
			// 获得最后的行号(行的索引，从0开始)
			int lastRowNum = sheet.getLastRowNum();
			// 获得最大的列号
			Row firstRow = sheet.getRow(0);
			// 获得最大的列数
			int lastCellNum = firstRow.getLastCellNum();

			// 创建一个数组，保存表头
			String[] columnNameArray = new String[lastCellNum];

			// 获得表头--遍历第一行的每一列
			for (int k = 0; k < lastCellNum; k++) {
				// 获得当前行的每一列
				Cell cell = firstRow.getCell(k, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				// 设置列的类型
				cell.setCellType(CellType.STRING);
				// 获得该列的值
				String columnName =cell.getStringCellValue();
				// 放到容器中去
				columnNameArray[k] = columnName;
			}

			// 遍历每一行(i相当于行索引,第一行不要)
			for (int i = 1; i <= lastRowNum; i++) {
				// 通过字节码对象实例化一个对象：
				ExcelObject obj = clazz.newInstance();
				// 设置行号
				obj.setRowNum(i + 1);
				// 获得索引对应的行
				Row row = sheet.getRow(i);
				// 遍历每一列（j相当于列号）
				for (int j = 0; j < lastCellNum; j++) {
					// 获得当前行的每一列
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					// 设置列的类型
					cell.setCellType(CellType.STRING);
					// 获得该列的值
					String cellValue = StringUtils.replace(cell.getStringCellValue());
					// 给apiinfo的各个属性进行设值
					// 获得此列的表头
					String columnName = columnNameArray[j];
					// 得到setter方法的名称
					String setterMethodName = "set" + columnName.substring(0, columnName.indexOf("("));
					// 得到setter方法
					Method setterMethod = clazz.getMethod(setterMethodName, String.class);

					// 原始字符串的参数的替换
					String commonStr = ParameterUtils.getCommonStr(cellValue);
 
					// 反射调用该方法
//					setterMethod.invoke(obj, cellValue);
					setterMethod.invoke(obj, commonStr);
				}
//				System.out.println(apiInfo);
				// 添加到容器
				objList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objList;
	}



	/**
	 * 
	 * @param excelPath excel的路径
	 * @param sheetNum  sheet编号，从1开始,传1表示第1个sheet，即索引为0的sheet
	 * @return
	 */
	public static Object[][] readExcel(String excelPath, int sheetNum) {
		// 创建一个Object类型的二维数组，保存从excel解析出来的行列数据
		Object[][] datas = null;
		try {
			InputStream inp = ExcelUtils.class.getResourceAsStream(excelPath);
			// 获得工作簿对象
			Workbook workbook = WorkbookFactory.create(inp);
			// 获得第一个sheet
			Sheet sheet = workbook.getSheetAt(sheetNum - 1);
			// 遍历--》思路应该怎么样？？
			// 通过遍历拿到所有的行--》通过遍历拿到每一行的列
			// 获得最后的行号(行的索引，从0开始)
			int lastRowNum = sheet.getLastRowNum();
			// 获得最大的列号
			Row firstRow = sheet.getRow(0);
			// 获得最大的列数
			int lastCellNum = firstRow.getLastCellNum();

			datas = new Object[lastRowNum][];
			// 遍历每一行(i相当于行号,第一行不要)
			for (int i = 1; i <= lastRowNum; i++) {
				// 获得索引对应的行
				Row row = sheet.getRow(i);
				// 创建一个一维数组，保存该行的所有列信息
				Object[] cellValueArray = new Object[lastCellNum];
				// 遍历每一列（j相当于列号）
				for (int j = 0; j < lastCellNum; j++) {
					// 获得当前行的每一列
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					// 设置列的类型
					cell.setCellType(CellType.STRING);
					// 获得该列的值
					String cellValue = cell.getStringCellValue();
					// 把当前列的数据添加到cellValueArray
					cellValueArray[j] = cellValue;
				}
				// 每遍历一行，要把该行对应的一维数组添加进去
				datas[i - 1] = cellValueArray;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datas;
	}

	
	
	
	
	
	
	
	// example :一定要在工具类写一个main方法--》方便调试和后期的维护
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		/*
		 * Object[][] datas = readExcel("/api.xlsx", 2); for (Object[] cellValueArray :
		 * datas) { for (Object cellValue : cellValueArray) {
		 * System.out.print("["+cellValue + "]    "); } System.out.println(); }
		 */

		ExcelUtils reader = new ExcelUtils();
		System.out.println(reader.readExcelCell(2, 6)); 
		
		/*
		 * String jsonStr =
		 * "{\"mobilephone\":\"13517315669\",\"pwd\":\"123456\",\"regname\":\"柠檬班\"}";
		 * Map<String, String> dataMap = (Map<String, String>)
		 * JSONObject.parse(jsonStr); Set<String> keySet = dataMap.keySet(); for (String
		 * key : keySet) { System.out.println(key+":" + dataMap.get(key)); }
		 */

//		 readExcel2("/api.xlsx", 1);

//		 readExcel3("/api.xlsx", 2);

//		List<ApiDetail> objList = (List<ApiDetail>) readExcel("/api.xlsx", "request_data", ApiDetail.class);
//		for (Object obj : objList) {
//			System.out.println(obj);
//		}

		// 测试写回
//		writeExcel("/api.xlsx", 2, "1", 5, "");
	}

	/**
	 * 写回数据到excel 性能问题： 1：如果写1000次数据，就要io操作1000次
	 * --》只读写一次可以ok了（把所有的要写的相关信息先收集起来，所有测试用例执行完毕之后再写） 往第1行第1列写入aaa 往第1行第2列写入bbb
	 * 往第2行第1列写入ccc 往第2行第5列写入ddd 往第3行第10列写入eee 2：如果caseId对应的行比较靠后，前面遍历次数会非常多（已解决）
	 * --》告诉我一个caseId，就能拿到对应的行（rowNum）
	 * 
	 * @param excelPath
	 * @param sheetNum
	 * @param caseId
	 * @param cellNum
	 * @param result
	 */

	/**
	 *	 批量回写
	 * 
	 * @param string
	 * @param i
	 */
	public static void batchWrite(String sourceExcelPath, String targetExcelPath) {
		InputStream inp = null;
		Workbook workbook = null;
		OutputStream outputStream = null;
		try {

			inp = ExcelUtils.class.getResourceAsStream(sourceExcelPath);
			// 获得工作簿对象
			workbook = WorkbookFactory.create(inp);
			// 获得对应编号的sheet
			System.out.println(cellDatasToWriteMap.keySet().toString());
				for (String sheeName : cellDatasToWriteMap.keySet()) {
//					System.out.println(sheeName+cellDatasToWriteMap.get(sheeName));
					Sheet sheet = workbook.getSheet(sheeName);
					// 获得最大的行号
					int lastRowNum = sheet.getLastRowNum();

					// 拿出所有要回写的数据
					List<CellData> cellDataToWriteMapCellDatas = cellDatasToWriteMap.get(sheeName);
//					System.out.println(cellDataToWriteList);
					for (CellData cellData : cellDataToWriteMapCellDatas) {
						int rowNum = Configure.getRowNumByCaseId(cellData);
						
//						System.out.println(rowNum);
						Row row = sheet.getRow(rowNum );
						
						Cell cellToWrite6 = row.getCell(cellData.getCellNum()[0] - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
						cellToWrite6.setCellType(CellType.STRING);
						cellToWrite6.setCellValue(cellData.getResult());
						
						Cell cellToWrite7 = row.getCell(cellData.getCellNum()[1] - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
						cellToWrite7.setCellType(CellType.STRING);
						cellToWrite7.setCellValue(cellData.getAssertresult());

						
						Cell cellToWrite8 = row.getCell(cellData.getCellNum()[2] - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
						cellToWrite8.setCellType(CellType.FORMULA);
						cellToWrite8.setCellFormula("HYPERLINK(\"" + "D:\\Users\\Jarvan\\Pictures\\Camera Roll\\941498c7300d458c8db53a2664ce49f6.jpg"+ "\",\"" + "image"+ "\")");
						
					}
				
				}
				
				/*
				 * HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
				 * HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1,
				 * (short) 5, 8); // 图片字节流 ByteArrayOutputStream byteArrayOut = new
				 * ByteArrayOutputStream(); BufferedImage bufferImg = ImageIO.read(new
				 * File("D:\\Users\\Jarvan\\Pictures\\Camera Roll\\941498c7300d458c8db53a2664ce49f6.jpg"
				 * )); ImageIO.write(bufferImg, "jpg", byteArrayOut); byte[] imgtypes =
				 * byteArrayOut.toByteArray();
				 * 
				 * 在工作簿中添加一张图片，返回图片的索引，base 1
				 * 
				 * @param pictureType 图片类型 PICTURE_TYPE_JPEG|PICTURE_TYPE_PNG
				 * 
				 * int puctureIndex = workbook.addPicture(byteArrayOut.toByteArray(),
				 * HSSFWorkbook.PICTURE_TYPE_JPEG); // 创建图片 patriarch.createPicture(anchor,
				 * puctureIndex);
				 */

			// 第一种方式：遍历每一行
			/*
			 * for(int i=1;i<=lastRowNum;i++){ //拿到当前的行 Row row = sheet.getRow(i);
			 * //拿到当前行的第一列 Cell cell = row.getCell(0,
			 * MissingCellPolicy.CREATE_NULL_AS_BLANK); cell.setCellType(CellType.STRING);
			 * //获得当前列的数据 String firstCellValue = cell.getStringCellValue();
			 * //判断第一列的数据是不是caseId //如果caseId等于第一列数据--》就是这一行 if
			 * (caseId.equals(firstCellValue)) { Cell cellToWrite = row.getCell(cellNum - 1,
			 * MissingCellPolicy.CREATE_NULL_AS_BLANK);
			 * cellToWrite.setCellType(CellType.STRING); cellToWrite.setCellValue(result);
			 * //已经匹配上了，跳出循环 break; } }
			 */

			outputStream = new FileOutputStream(new File(targetExcelPath));
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
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
