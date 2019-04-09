package com.domoyun.util;

public class FunctionUtils {

	public static String md5(String str) {
		return "ELKJKLDJXKLDJL239874982HKGJKLK";
	}

	public static String getRegName(String str1, String str2, String str3) {
		return str1 + str2 + str3;
	}

	public static String getRegName(String str1, String str2, String str3, String str4) {
		return "小蜜蜂-" + str1 + str2 + str3 + str4;
	}

	public static String getMobliePhone() {
		//去数据库查询出最大的手机号
		//手机号保证数据库不存在即可
		return "13822222222";
	}

}
