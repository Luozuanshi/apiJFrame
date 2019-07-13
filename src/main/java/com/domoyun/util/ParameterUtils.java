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
 * 	类描述：
 */
public class ParameterUtils {
	
	//全局数据池
	private static Map<String, String> globalDataMap = new HashMap<>();
	
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
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		//迭代所有的符合规则的字符串
		while(matcher.find()){
			String totalStr = matcher.group(0); //${mobilephone} ${pwd}  ${regname}
			String paramsName = matcher.group(1);// mobilephone pwd regname
			String paramsValue = globalDataMap.get(paramsName); //15666666666 qq123456 "pangluo"
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
		String regex = "__(\\w*?)\\(((\\w*,?)*)\\)"; 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		//获得FunctionUtils字节码对象
		Class<FunctionUtils> clazz = FunctionUtils.class;
		while(matcher.find()){
			String totalStr = matcher.group(0);
			String functionName = matcher.group(1);
			String parametersStr = matcher.group(2);
			String result  = "";
			//没有参数
			if ("".equals(parametersStr)) {
				try {
					Method method = clazz.getMethod(functionName);
					result = (String) method.invoke(null);
				} catch (Exception e) {
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
		}
		return str;
	}


	public static void main(String[] args) throws Exception {
		
		globalDataMap.put("mobliephone", "1355555555");
		String str = "{\"mobilephone\":\"${mobliephone}\",\"pwd\":\"__md5(123456)\",\"regname\":\"__getRegName(aa,bb,cc,dd)\"}";
		System.out.println(str);
		System.out.println(getCommonStr(str));
		str = getFunctionOptStr(getCommonStr(str));
		System.out.println(str);
	}

}
