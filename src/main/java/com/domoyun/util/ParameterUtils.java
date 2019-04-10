package com.domoyun.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *	自定义变量参数化,
 *	 项目名称：apiFrame
 *	类名称：ParameterUtils
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月9日下午1:17:06
 * 	类描述：
 */
public class ParameterUtils {
	
	//全局数据池
	public static Map<String, String> globalDataMap = new HashMap<>();
	
	/**
	 *	 添加数据到全局数据池中间
	 * @param key
	 * @param value
	 */
	public static void addGlobalData(String key,String value){
		globalDataMap.put(key, value);
	}
	
	/**
	 * 	从全局数据池提取出对应值
	 * @param key
	 * @return
	 */
	public static String getGlobalData(String key){
		return globalDataMap.get(key);
	}
	
	/**
	 * 	对字符串进行正则匹配，把提取出的符合规则的字符串替换为对应的参数的值
	 * @param cellValue
	 * @return
	 */
	public static String getCommonStr(String str) {
		String regex = "\\$\\{(.*?)\\}"; //贪婪模式 匹配 ${}里面的内容
		//编译正则表达式，得到模式
		Pattern pattern = Pattern.compile(regex);
		//进行匹配
		Matcher matcher = pattern.matcher(str);
		//迭代所有的符合规则的字符串
		while(matcher.find()){
			//group 0是检索到完整符合规则的字符串
			String totalStr = matcher.group(0); //${mobilephone} ${pwd}  ${regname}
			//将参数名提取出来
			String paramsName = matcher.group(1);// mobilephone pwd regname
			//以参数名对应的值从全局数据池拿出来--》
			String paramsValue = globalDataMap.get(paramsName); //15666666666 qq123456 "pangluo"
			//把完整的字符串替换为参数对应的值
			str = str.replace(totalStr, paramsValue); 
		}
//		return str;
		return getFunctionOptStr(str);
	}
	
	/**
	 * 	通过反射调用FunctionUtils中间的方法，得到处理字符串的值
	 *	 替换方法参数
	 * @param str
	 * @return
	 */
	public static String getFunctionOptStr(String str){
		//__getMobliePhone()  __md5(123456) __getRegName(aa,bb,cc)
		//正则表达式： __(\w*?)\(((\w*,?)*)\)
		String regex = "__(\\w*?)\\(((\\w*,?)*)\\)"; 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		//获得FunctionUtils字节码对象
		Class<FunctionUtils> clazz = FunctionUtils.class;
		while(matcher.find()){
			//完整的方法参数
			String totalStr = matcher.group(0);
			//拿到方法名
			String functionName = matcher.group(1);
			//拿到所有参数
			String parametersStr = matcher.group(2);
			String result  = "";
			//没有参数
			if ("".equals(parametersStr)) {
				try {
					Method method = clazz.getMethod(functionName);
					result = (String) method.invoke(null);
				} catch (Exception e) {
					//记录你的日志
					e.printStackTrace();
				}
			}
			//有参数
			else{
				//参数值的数组
				String[] parameterArray = parametersStr.split(",");
				//获得参数值的格式
				int parametersNum = parameterArray.length;
				//定义一个字节码对象数组
				Class[] parameterTypes = new Class[parameterArray.length];
				for(int i=0;i<parametersNum;i++){
					parameterTypes[i] = String.class;
				}
				try {
					//拿到方法名去反射调用FunctionUtils中对应的方法
					Method method = clazz.getMethod(functionName, parameterTypes);
					//反射调用
					result = (String) method.invoke(null, parameterArray);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			str = str.replace(totalStr, result);
//			System.out.println("反射调用函数，替换后：");
//			System.out.println(str);
		}
		return str;
	}


	public static void main(String[] args) throws Exception {
//		example1();
//		example2();
		
		globalDataMap.put("mobliephone", "1355555555");
		String str = "{\"mobilephone\":\"${mobliephone}\",\"pwd\":\"__md5(123456)\",\"regname\":\"__getRegName(aa,bb,cc,dd)\"}";
//		System.out.println(getCommonStr(str));
		str = getFunctionOptStr(getCommonStr(str));
		System.out.println(str);
	}

	private static void example2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String str = "{\"mobilephone\":\"__getMobliePhone()\",\"pwd\":\"__md5(123456)\",\"regname\":\"__getRegName(aa,bb,cc,dd)\"}";
		System.out.println(str);
//		String str = "{\"mobilephone\":\"13822222222\",\"pwd\":\"ELKJKLDJXKLDJL239874982HKGJKLK\",\"regname\":\"aabbcc\"}";
		
		//__getMobliePhone()  __md5(123456) __getRegName(aa,bb,cc)
		//正则表达式： __(\w*?)\(((\w*,?)*)\)
		
		String regex = "__(\\w*?)\\(((\\w*,?)*)\\)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		//获得FunctionUtils字节码对象
		Class<FunctionUtils> clazz = FunctionUtils.class;
		while(matcher.find()){
			//完整的方法参数
			String totalStr = matcher.group(0);
			//拿到方法名
			String functionName = matcher.group(1);
			//拿到所有参数
			String parametersStr = matcher.group(2);
			String result  = "";
			//没有参数
			if ("".equals(parametersStr)) {
				Method method = clazz.getMethod(functionName);
				result = (String) method.invoke(null);
			}
			//有参数
			else{
				//参数值的数组
				String[] parameterArray = parametersStr.split(",");
				//获得参数值的格式
				int parametersNum = parameterArray.length;
				//定义一个字节码对象数组
				Class[] parameterTypes = new Class[parameterArray.length];
				for(int i=0;i<parametersNum;i++){
					parameterTypes[i] = String.class;
				}
				
				//拿到方法名去反射调用FunctionUtils中对应的方法
				Method method = clazz.getMethod(functionName, parameterTypes);
				//反射调用
				result = (String) method.invoke(null, parameterArray);
			}
			
			str = str.replace(totalStr, result);
			System.out.println("反射调用函数，替换后：");
			System.out.println(str);
		}
	}

	private static void example1() {
		//1：删除数据库 --》
		//2：操作excel，全局替换
		//3：程序查询出最大的手机号+1--》新手机号---》把excel中间数据进行替换（参数化--》接口的关联）
		//从数据库查询出新的手机号--》内存--》变量 
		globalDataMap.put("mobilephone", "13666666666");
		globalDataMap.put("pwd", "abcedf");
		globalDataMap.put("regname", "tom");
		
		String requestData = "{\"mobilephone\":\"${mobilephone}\",\"pwd\":\"${pwd}\",\"regname\":\"${regname}\"}";
		System.out.println(getCommonStr(requestData));
		

		// ${mobilephone} --->${变量名}
		//从excel解析出来的数据，把其中的参数替换为内存中间对应变量的值
		/*String requestData = "{\"mobilephone\":\"${mobilephone}\",\"pwd\":\"${pwd}\",\"regname\":\"${regname}\"}";
//		String requestData = "{\"mobilephone\":\"13555555555\",\"pwd\":\"abcdef\",\"regname\":\"柠檬班\"}";
		System.out.println(requestData);
		//检索、提取符合规则的字符串内容--》正则表达式
		//${变量名} ---》${mobilephone}   ${pwd}  ${xxx}
		//\$\{.*\}
		
		String regex = "\\$\\{(.*?)\\}"; //贪婪模式
		//编译正则表达式，得到模式
		Pattern pattern = Pattern.compile(regex);
		//进行匹配
		Matcher matcher = pattern.matcher(requestData);
		//迭代所有的符合规则的字符串
		while(matcher.find()){
			//group 0是检索到完整符合规则的字符串
			String totalStr = matcher.group(0); //${mobilephone}  ${pwd}  ${regname}
//			System.out.println(totalStr);
			//将参数名提取出来
			String paramsName = matcher.group(1);
//			System.out.println(paramsName);
			//以参数名对应的值从全局数据池拿出来--》
			String paramsValue = globalDataMap.get(paramsName);
//			System.out.println(paramsValue);
			//把完整的字符串替换为参数对应的值
			requestData = requestData.replace(totalStr, paramsValue);
		}
		System.out.println("替换后");
		System.out.println(requestData);*/
	}
}
