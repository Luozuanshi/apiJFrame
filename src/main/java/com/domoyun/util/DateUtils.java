/**
 * 
 */
package com.domoyun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *	 项目名称：apiFrame
 *	类名称：DateUtils
 * @author Test
 * @version 1.0
 * 	创建时间2019年8月7日下午4:07:36
 * 	类描述：
 */
public class DateUtils {
	
	public static String DateTime() {
	SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return dFormat.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(DateTime());
	}

}
