/**
 * 
 */
package com.domoyun.routine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.Md5Crypt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 *	 项目名称：apiFrame
 *	类名称：FastJson
 * @author Test
 * @version 1.0
 * 	创建时间2019年4月16日下午7:44:27
 * 	类描述：
 */
public class FastJson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlString ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xxxx.org/soap/envelope/\" xmlns:ns1=\"http://www.example.org/Ec/\">\r\n" + 
				"	<SOAP-ENV:Body>\r\n" + 
				"		<ns1:callService>\r\n" + 
				"<paramsJson>{\"product_sku\":\"API_sku_AutoTest001\",\"reference_no\":\"\",\"product_title\":\"意大利怀表\",\"product_weight\":\"0.35\",\"product_length\":\"29.70\",\"product_width\":\"21.00\",\"product_height\":\"4\",\"contain_battery\":\"0\",\"product_declared_value\":\"10.5\",\"product_declared_name\":\"MenTshirt\",\"cat_lang\":\"en\",\"cat_id_level0\":\"400001\",\"cat_id_level1\":\"500013\",\"cat_id_level2\":\"600788\",\"verify\":\"0\",\"hs_code\":\"code123\",\"warning_qty\":\"10\"}</paramsJson>			<appToken>e3b35ad93c4d3d831728ff1217d02b90</appToken>\r\n" + 
				"			<appKey>b39989ad17963d61d1c18267d3fc1605</appKey>\r\n" + 
				"			<service>createProduct</service>\r\n" + 
				"		</ns1:callService>\r\n" + 
				"	</SOAP-ENV:Body>\r\n" + 
				"</SOAP-ENV:Envelope>\r\n" + 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.example.org/Ec/\"><SOAP-ENV:Body><ns1:callServiceResponse><response>{\"ask\":\"Failure\",\"message\":\"创建商品失败，ProductSKU已存在！\",\"Error\":{\"errMessage\":\"创建商品失败，ProductSKU已存在！，\",\"errCode\":0},\"product_sku\":\"API_sku_AutoTest001\"}</response></ns1:callServiceResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>\r\n" + 
				"";
		
//		System.out.println(base64Output(xmlString));
		
		System.out.println(System.getProperty("user.dir")+"\\"+System.currentTimeMillis()+".jpg");
	}
	
	public static Map<String, String> base64Output(String actualResult,String useCaseTitle,String sheetname) {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("filepath",System.getProperty("user.dir")+"\\"+sheetname+"\\"+useCaseTitle+".pdf");
		hashMap.put("filename",useCaseTitle+".jpg");
		JSONObject obj;
		try {
			obj = JSON.parseObject(actualResult);
		} catch (Exception e) {
//			e.printStackTrace();
			hashMap.put("actualResult", actualResult);
			return hashMap;
		}
		//取出Data JsonObj
		JSONObject obj1 =JSON.parseObject(obj.getString("Data")) ;
		if (obj1 ==null) {
			hashMap.put("actualResult", actualResult);
			return hashMap;
		}
		//取出LabelImage value
		String LabelImage =obj1.getString("LabelImage"); 

		//base64写出图片
		Base64.generateImage(LabelImage, System.getProperty("user.dir")+"\\"+sheetname+"\\"+useCaseTitle+".pdf");
		//响应结果替换base64值
		String c = actualResult.toString().replace(LabelImage, "值为Base64，具体信息请查看日志");
		hashMap.put("actualResult", c);
		return hashMap;
	}

}
