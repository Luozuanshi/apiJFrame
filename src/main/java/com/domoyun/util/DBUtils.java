package com.domoyun.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

/**
 * JDBC工具类
 * @author pangluo
 * @date 2018年11月16日
 * @desc  提供通用性增删改查方法
 * @email
 */
public class DBUtils {
	//hard code-->硬编码、写死的代码--》耦合度比较高，不利于维护--》数据分离、数据解耦、数据抽取
	private static String user;

	private static String password;

	private static String url;

	/**
	 * 通过静态块：
	 * 	保证注册驱动，以及读取数据库连接信息的操作只运行一遍
	 */
	static {
		try {
			Properties properties = new Properties();
			properties.load(DBUtils.class.getResourceAsStream("/config/jdbc.properties"));
			String driverPath = properties.getProperty("jdbc.driver");
			Class.forName(driverPath);

			url = properties.getProperty("jdbc.url");//连接字符串
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得数据库连接的方法
	 * @return
	 */
	private static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 痛点:
	 * 1：当进行增、删、改操作时，sql各不相同、参数不确定--->通用性
	 * 执行增删改得操作：
	 * 		1）sql语句：insert into member(regname,pwd,mobilephone) values(?,?,?);
	 * 		2）参数：占位符对应的参数值--》不确定类型、不确定个数--》调和(用object类型，)
	 * @param sql 要执行的增、删、改 sql语句
	 * @param params  对应占位符的可变参数列表
	 * @throws Exception
	 */
	public static void execute(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			//占位符参数设值
			int length = params.length;
			for (int i = 0; i < length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			close(conn, pstmt);
		}
	}


	/**
	 * 1：查询的sql不一样
	 * 2：查询的参数不一样
	 * 3：结果集的记录数、字段数是不确定的 -->容器（数据载体）
	 * 		0条或多条记录--》ArrayList
	 * 		每条记录有多个字段，并且字段的名称不确定--》HashMap
	 * 
	 * 		-->List<Map<String,String>>
	 * 
	 * 		excel一行 = properties文件 = 数据库的一行 = xml = json  = java对象  = HashMap （key -- value）
	 * @param sql  要执行的查询sql
	 * @param params 占位符对应的参数列表
	 * @return
	 * @throws Exception
	 */
	public static List<LinkedHashMap<String, String>> select(String sql, Object... params) {
		//创建一个List<Map<String, String>>容器，存放所有的记录
		List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			//设置占位符参数
			int length = params.length;
			for (int i = 0; i < length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			//结果集
			resultSet = pstmt.executeQuery();
			//获得结果集的元数据(描述数据数据)
			ResultSetMetaData metaData = resultSet.getMetaData();
			//获得列数
			int columnCount = metaData.getColumnCount();

			//遍历每一条记录
			while (resultSet.next()) {
				//创建一个map对象，保存每一行记录
				LinkedHashMap<String, String> rowDataMap = new LinkedHashMap<>();
				//解析每条记录的值
				for (int i = 1; i <= columnCount; i++) {
					//获得没一列的值
					String value = resultSet.getString(i);
//					//获得字段名，作为key
//					String columnName = metaData.getColumnName(i);
					//采用别名作为key
					String columnName = metaData.getColumnLabel(i);
					//把没一个字段对应的值存放到map中间
					rowDataMap.put(columnName, value);
				}
				//把每一行记录对应的map添加到list
				dataList.add(rowDataMap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//6:释放资源
			close(conn, pstmt, resultSet);
		}

		return dataList;

	}

	/**
	 * 关闭
	 * @param conn 连接
	 * @param pstmt 陈述对象
	 * @param resultSet 结果集
	 */
	private static void close(Connection conn, PreparedStatement pstmt, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(conn, pstmt);
	}
	

	/**
	 * 关闭资源
	 * @param conn 连接
	 * @param pstmt 陈述对象
	 */
	private static void close(Connection conn, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//example
	public static void main(String[] args) throws Exception {

		//1：插入：
//		String sql1 = "insert into member(regname,pwd,mobilephone,leaveAmount) values(?,?,?,?);";
//		DBUtils.execute(sql1, "Jack","654321","13777777777",100.00);

		//2:修改
//		String sql2 = "update member set leaveAmount=? where id=?;";
//		DBUtils.execute(sql2, 10.0, 1);
		
		//3：删除
//		String sql3 = "delete from member where id=? and regname=?";
//		DBUtils.execute(sql3, 1);
		
		//4:查询
		String sql4 = "select id,mobilephone,regTime from member where id<?;";
		String sql5 ="select id as id,mobilephone as mobilephone,regTime as regtime from member where id<?;";
		sql5 ="SELECT * from goodcang_settlement_center_test.bil_shipperbill_income_101_201812;";
		
		String sql6 ="INSERT INTO `goodcang_settlement_center_test`.`bil_income`(`id`, `sheet`, `arrival_date`, `customer_id`, `og_id`, `bs_id`, `fk_code`, `unit_code`, `ic_amount`, `currency_code`, `ic_createdate`, `st_id_creator`, `charge_node`, `serve_hawbcode`, `refer_hawbcode`, `shipper_hawbcode`, `destination_countrycode`, `product_code`) VALUES (?, '201904', '2019-04-15 01:53:57', 5, 6, 10267843, 'E1', 'KG', 4.50, 'USD', '2019-04-15 01:54:06', 0, 1, '786642341392', 'TestID11KH6000N1', 'YDCN1904150001044', 'US', 'PK0013');";
		List<LinkedHashMap<String, String>> dataList = select(sql5);

		long startTime =  System.currentTimeMillis();
		for (int i = 2910334; i < 2920334; i++) {
			execute(sql6,i );
			System.out.println("执行");
		}
		long endTime =  System.currentTimeMillis();
		long usedTime = (endTime-startTime)/1000;
		System.out.println(usedTime);
//		for (LinkedHashMap<String, String> map : dataList) {
//			System.out.println(map);
//		}
		
		
		
		
	}


}
