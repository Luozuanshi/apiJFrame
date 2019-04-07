package com.lemon.api.auto.util;

public class StringUtils {

	/**
	 * 是否为空
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
