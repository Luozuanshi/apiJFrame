/**
 * 
 */
package com.domoyun.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domoyun.pojo.CellData;
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
	 * 	要写的cell数据池
	 * cellDatasToWriteList临时中转数据用的对象集合-list<CellData>
	 * cellDatasToWriteMap以键值对的形式存储各个sheet对象集合list<CellData>
	 */
	static List<CellData> cellDatasToWriteList = new ArrayList<>();
	static Map<String,List<CellData>>  cellDatasToWriteMap = new HashMap<String, List<CellData>>();
	/**
	 * 	添加要回写的数据
	 * @param cellData
	 */
	void addCellData(CellData cellData);

	/**
	 * 	取出所有收集的数据
	 * @return
	 */
	List<CellData> getCellData();
	
	/**
	 * 获得不同sheetName要写的celldata数据
	 * @return
	 */
	List<CellData> getCellDatasToWriteList(String sheetName);
	
	/**
	 * 通过getCellData()取出所有收集的对象集合数据，put进cellDatasToWriteMap
	 * @param sheetName
	 */
	void putmap(String sheetName);
	
	void clearlist();
	
}

class savaAndWriteData implements WriteCollection{

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

	@Override
	public List<CellData> getCellDatasToWriteList(String sheetName) {
		return cellDatasToWriteMap.get(sheetName);
	}

	@Override
	public void putmap(String sheetName) {
		cellDatasToWriteMap.put(sheetName, ExcelUtils.getCellData());
		ExcelUtils.clearlist();
	}

	@Override
	public void clearlist() {
		cellDatasToWriteList.clear();
	}
	
	
}



interface ApplePredicate{
	public boolean test(String a);
}
interface ApplePredicatea{
	public boolean test(String a);
}
interface ApplePredicateb{
	public boolean test(String a);
}
