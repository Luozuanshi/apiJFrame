package com.domoyun.routine;

import java.util.Arrays;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMediaUploadRequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiMediaUploadResponse;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import com.taobao.api.FileItem;*/

public class dingdingtalk {
	
	/**
	 * 
	 */
	public static void dingdingtalk(String title,String text) {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=fe666d60ef1fbb60d951db1dc779c8dfa65c147606c34017383bac1da8b74c5a");
		OapiRobotSendRequest request = new OapiRobotSendRequest();
//		request.setMsgtype("text");
//		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//		text.setContent("测试文本消息");
//		request.setText(text);
//		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//		at.setAtMobiles(Arrays.asList("13261303345"));
//		request.setAt(at);
//
//		request.setMsgtype("link");
//		OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//		link.setMessageUrl("https://www.dingtalk.com/");
//		link.setPicUrl("");
//		link.setTitle("时代的火车向前开");
//		link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
//		        "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
//		request.setLink(link);

		request.setMsgtype("markdown");
		OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
		markdown.setTitle(title);
		markdown.setText(text);
		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
		at.setAtMobiles(Arrays.asList("15616216603","13927973086","18827517141"));
		at.setIsAtAll("true");
		request.setAt(at);
		request.setMarkdown(markdown);
		try {
			OapiRobotSendResponse response = client.execute(request);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * main方法测试类
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	
	  public static void main(String[] args)  {
//			DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=fe666d60ef1fbb60d951db1dc779c8dfa65c147606c34017383bac1da8b74c5a");
//			OapiRobotSendRequest request = new OapiRobotSendRequest();
//			request.setMsgtype("text");
//			OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//			text.setContent("测试文本消息");
//			request.setText(text);
//			OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//			at.setAtMobiles(Arrays.asList("15616216603","13927973086","18827517141"));
//			request.setAt(at);
//			try {
//				OapiRobotSendResponse response = client.execute(request);
//			} catch (ApiException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			dingdingtalk("当天的取消单", "![测试](http://img03.sogoucdn.com/app/a/100520021/4064a5d087583c058a69f9635e02b6b8)");
		}
		
	  
	 

}
