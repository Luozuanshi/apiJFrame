package com.domoyun.util;
/**
 *	 项目名称：apiFrame
 *	类名称：StringUtils
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月9日下午1:16:40
 * 	类描述：字符操作类
 */
public class StringUtils {

	/** 
	 * 	 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){

		return (str== null) || ("".equals(str.replace(" ", "")));
	}
	
	
	public static void main(String[] args) {
		String str1 = null;
		String str2 = "";
		String str3 = "    ";
		String str4 = "   a  ";
		System.out.println(isEmpty(str1));
		System.out.println(isEmpty(str2));
		System.out.println(isEmpty(str3));
		System.out.println(isEmpty(str4));
	}
}
