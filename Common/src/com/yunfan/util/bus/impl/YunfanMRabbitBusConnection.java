package com.yunfan.util.bus.impl;

import java.util.List;

import com.rabbitmq.client.ConnectionFactory;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.bus.YunfanBusConnection;
/**
 * 
 * @author 云帆
 *
 */
public class YunfanMRabbitBusConnection implements YunfanBusConnection {
	private static final long serialVersionUID = -2460338668241678574L;
	private com.rabbitmq.client.Connection mRabbitConnection;
	
	public YunfanMRabbitBusConnection(String pConnectHost) {
		super();
		try{
			ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost(pConnectHost);
	        mRabbitConnection = factory.newConnection();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void close(){
		try{
			mRabbitConnection.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public ReceiveChannel createReceiveChannel(String pChannelName, List<String> pBingdingKey){
		return new MRabbitReceiveChannel(mRabbitConnection, pChannelName, pBingdingKey);
	}

	public SendChannel createSendChannel(String pChannelName){
		return new MRabbitSendChannel(mRabbitConnection, pChannelName);
	}
}
