package com.domoyun.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *	 项目名称：apiFrame
 *	类名称：StringUtils
 * @author Test
 * @version 1.0
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
	/**
	 * 	去除空格回车制表符
	 * @param str
	 * @return
	 */
	public static String replace(String str) {
	    String destination = "";
	    if (str!=null) {
	        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	        Matcher m = p.matcher(str);
	        destination = m.replaceAll("");
	    }
	    return destination;
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
