package com.yunfan.util.bus;

import java.io.Serializable;
import java.util.List;
/**
 * 云帆业务总线，即数据读存的总线
 * @comment 暂时仅包括两种实现，kafka和rabbitMq
 * @author 云帆
 *
 */
public interface YunfanBusConnection extends Serializable {

	/**
	 * 关闭方法，将connection释放
	 */
	public void close();
	
	/**
	 * 创建接受者的消费通道
	 * @param pChannelName
	 * @param pBingdingKey
	 * @return
	 */
	public ReceiveChannel createReceiveChannel(String pChannelName, List<String> pBingdingKey);

	
	/**
	 * 创建发送者的生产通道
	 * @param pChannelName
	 * @return
	 */
	public SendChannel createSendChannel(String pChannelName);
}
