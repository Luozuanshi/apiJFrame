/**
 * 
 */
package com.domoyun.InterfaceAbstract;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domoyun.pojo.bean.CancelLabelBean;
import com.domoyun.pojo.bean.PrintLabelBean;
import com.domoyun.util.ExcelUtils;

/**
 *	 项目名称：apiFrame
 *	类名称：WriteCollection
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月30日下午1:48:17
 * 	类描述：
 */
public interface WriteCollection {
	
	/**
	 * 收集器
	 * cellDatasToWriteList临时中转数据用的对象集合-list<CellData>
	 * 数据池
	 * cellDatasToWriteMap以键值对的形式存储各个sheet对象集合list<CellData>
	 */
	static List<WriteCollection> cellDatasToWriteList = new ArrayList<>();
	static Map<String,List<? extends WriteCollection>>  cellDatasToWriteMap = new HashMap<String, List<? extends WriteCollection>>();
	/**
	 * 	添加要回写的数据
	 * @param cellData
	 */
	void addData(WriteCollection cellData);

	/**
	 * 	取出所有收集的数据
	 * @return
	 */
	List<WriteCollection> getData();
	
	/**
	 *  通过getData()取出所有收集的对象集合，put进cellDatasToWriteMap
	 *@param sheetName
	 */
	void putmap(String sheetName);
	
	/**
	 *  获得不同sheetName要写的celldata数据
	 * @return
	 */
	List<WriteCollection> getDatasToWriteList(String sheetName);
	
	/**
	 * 清空数据收集器
	 */
	void clearlist();
	
	public void batchWrite(String sourceExcelPath,String targetExcelPath);
}


