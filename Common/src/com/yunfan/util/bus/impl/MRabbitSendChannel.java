package com.yunfan.util.bus.impl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.model.JsonBean;

/**
 * MRabbit队列的消息发送通道
 * @author yangxingyi
 *
 */
public class MRabbitSendChannel implements SendChannel{
	private static final long serialVersionUID = -3979193008409772360L;

	private Logger log = LoggerFactory.getLogger("RabbitMQ");
	
	private com.rabbitmq.client.Channel mChannel;
	private String mChannelName;
	
	public MRabbitSendChannel(com.rabbitmq.client.Connection pRabbitConnection, String pChannelName) {
		try{
			mChannel = pRabbitConnection.createChannel();
			mChannel.exchangeDeclare(pChannelName, "topic");
			mChannelName = pChannelName;
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void sendObject(JsonBean pJsonBean, String pRoutingKey){
		try{
			mChannel.basicPublish(mChannelName, pRoutingKey, null, pJsonBean.objectToJsonlet().getBytes());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 关闭当前信道
	 */
	@Override
	public void close() {
		try {
			mChannel.close();
		} catch (IOException | TimeoutException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		}
	}
}
