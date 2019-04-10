package com.domoyun.testCase;
 
import java.io.IOException;
 
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.domoyun.util.HttpUtils;
 
 
public class post {
	/**
	 *  	main方法测试类
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://test.tms.com/V4/api/LabelPrintService/PrintLabel?type=json";
		
		String json1 = "";
		String json2 = "{\"Version\":\"0.0.0.3\",\"Data\":{\"OrderID\":\"TestFedex6069QW\",\"ParcelInformation\":{\"Weight\":\"70\",\"WeightUnit\":1,\"Length\":\"30\",\"Width\":\"20\",\"Height\":\"10\",\"SizeUnit\":1,\"ExistDangeroursGoods\":false,\"ProductInformations\":[{\"Description\":\"phonecase\",\"Quantity\":1,\"Weight\":\"1.000\",\"WeightUnit\":4,\"Value\":\"0.0\",\"Sku\":\"\",\"Remark\":\"\",\"ProductUrl\":\"\",\"HSCode\":\"testcode\"}]},\"RecipientAddress\":{\"FirstName\":\"Shelley\",\"LastName\":\"Bond \",\"Company\":null,\"StreetAddress\":\"3817 Harrison Street\",\"StreetAddress2\":\"Address2\",\"StreetAddress3\":null,\"City\":\"San Francisco\",\"State\":\"CA\",\"ZIPCode\":\"94122\",\"Country\":\"US\",\"PhoneNumber\":\"+1.4155669802\",\"IsResidential\":true},\"ChannelName\":\"FEDEX\",\"Token\":\"99999999999999999999999999999999\",\"ServiceTypeCode\":\"FEDEX_GROUND\",\"WarehouseCode\":\"CA\",\"LabelMarkText\":null,\"RedundancyField\":{\"SignatureOption\":\"\"}}}\r\n" + 
				"";
		String json3 = "{\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"imtype\":\"LTCUS\"}},{\"range\":{\"rtdatetime\":{\"gte\":1521164922000,\"lte\":1521164932000}}}]}}}";
		
		String json = json2;
		
		System.out.println(HttpUtils.HttpPostWithJson(url, json));
		
		
	}
	
}
