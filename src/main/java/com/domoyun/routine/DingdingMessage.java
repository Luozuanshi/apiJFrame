/**
 * 
 */
package com.domoyun.routine;
import java.io.File;
import java.util.Arrays;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMediaUploadRequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiMediaUploadResponse;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.domoyun.InterfaceAbstract.DingdingMessageInterface;
import com.taobao.api.ApiException;
import com.taobao.api.FileItem;
/**
 *	 项目名称：apiFrame
 *	类名称：DingdingMessage
 * @author Test
 * @version 1.0
 * 	创建时间2019年7月22日下午5:45:09
 * 	类描述：
 */
public class DingdingMessage implements DingdingMessageInterface {
	DingTalkClient client;                           
	OapiRobotSendRequest robotsendRequest;
	OapiRobotSendResponse robotsendResponse;
	OapiMediaUploadRequest mediaUploadrequest;
	OapiMediaUploadResponse mediaUploadResponse;
	/**
	 * 	钉钉操作类
	 * 0:获取机器人消息接口权限
	 * 1:获取钉盘文件接口权限
	 */
	public DingdingMessage(int IFStype) {
		if (IFStype==0) {
			 this.client= new DefaultDingTalkClient(ACCESS_TOKEN);
			 this.robotsendRequest = new OapiRobotSendRequest();
		}else {
			 this.client= new DefaultDingTalkClient(FILEACCESS_TOKEN);
			 this.mediaUploadrequest = new OapiMediaUploadRequest();
		}
	}


	@Override
	public void RobotText(String content) {
		robotsendRequest.setMsgtype("text");
		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
		text.setContent(content);
		robotsendRequest.setText(text);
		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
		at.setAtMobiles(Arrays.asList("15616216603"));
		robotsendRequest.setAt(at);
		try {
			robotsendResponse = client.execute(robotsendRequest);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void RobotLink(String title,String Link,String desc) {
		robotsendRequest.setMsgtype("link");
		OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
		link.setMessageUrl(Link);
		link.setPicUrl("");
		link.setTitle(title);
		link.setText(desc);
		robotsendRequest.setLink(link);
		try {
			robotsendResponse = client.execute(robotsendRequest);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void RobotMarkdown(String title,String desc) {
		robotsendRequest.setMsgtype("markdown");
		OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
		markdown.setTitle(title);
		markdown.setText(desc);
		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
		at.setAtMobiles(Arrays.asList("15616216603"));
		at.setIsAtAll("true");
		robotsendRequest.setAt(at);
		robotsendRequest.setMarkdown(markdown);
		try {
			robotsendResponse = client.execute(robotsendRequest);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void UploadRequest(String path) {
		mediaUploadrequest.setType("file");
		File file =new File(path);
		mediaUploadrequest.setMedia(new FileItem(file));
		try {
			mediaUploadResponse = client.execute(mediaUploadrequest);
			System.out.println(mediaUploadResponse.toString());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		
//		DingdingMessageInterface message =new DingdingMessage(0);
//		message.RobotText("兄弟萌");
//		message.RobotMarkdown("Markdown","# Markdown测试");
//		message.RobotLink("hello","https://domoyun.com/music","哈哈哈哈");
				
		DingdingMessageInterface message= new DingdingMessage(1);
		message.UploadRequest("D:\\Users\\Jarvan\\Desktop\\svn2\\trunk\\apiFrame\\src\\main\\java\\hibernate.cfg.xml");
		
	}

	
	
}
