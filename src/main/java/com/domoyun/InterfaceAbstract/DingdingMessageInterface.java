/**
 * 
 */
package com.domoyun.InterfaceAbstract;

/**
 *	 项目名称：apiFrame
 *	类名称：DingdingMessageInterface
 * @author Test
 * @version 1.0
 * 	创建时间2019年7月22日下午5:37:10
 * 	类描述：
 */
public interface DingdingMessageInterface {
	String ACCESS_TOKEN="https://oapi.dingtalk.com/robot/send?access_token=42fc9f98be6b165a6b8faa69b2693dc47d53a69a90ec99818059776b5d3593c8";
	String FILEACCESS_TOKEN="https://oapi.dingtalk.com/media/upload?access_token=42fc9f98be6b165a6b8faa69b2693dc47d53a69a90ec99818059776b5d3593c8";
	
	public void RobotText(String content);
	public void RobotLink(String title,String link,String desc);
	public void RobotMarkdown(String title,String desc) ;
	public void UploadRequest(String path);
	
	
}
