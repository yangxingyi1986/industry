package com.yunfan.util.bus.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yunfan.util.bus.BusReceiver;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.model.CombineBean;
import com.yunfan.util.model.FileBean;
import com.yunfan.util.model.JsonBean;
import com.yunfan.util.model.MessageBean;
import com.yunfan.util.model.SampleBean;

/**
 * MRabbit接受消息的消息通道
 * @author yangxingyi
 *
 */
public class MRabbitReceiveChannel implements ReceiveChannel {
	
	private Logger log = LoggerFactory.getLogger("RabbitMQ");

	private Channel mRabbitChannel;
	private String mChannelName;
	private String mQueueName;
	
	public MRabbitReceiveChannel(com.rabbitmq.client.Connection pRabbitConnection, String pChannelName, List<String> bindingKeyList) {
		try{
			
			mRabbitChannel = pRabbitConnection.createChannel();
			mChannelName = pChannelName;
			mRabbitChannel.exchangeDeclare(pChannelName, "topic");
			mQueueName = mRabbitChannel.queueDeclare().getQueue();
	
		    for (String bindingKey : bindingKeyList) {
		    	mRabbitChannel.queueBind(mQueueName, mChannelName, bindingKey);
		    }
		    
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
	 * 回调接收者函数
	 */
	public void setBusReceiver(final BusReceiver busReceiver){
		
		try{
			Consumer consumer = new DefaultConsumer(mRabbitChannel) {
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope,
		                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
					JsonBean jsonBean = null;
					switch (busReceiver.getJsonType()){
					case SAMPLE:
						jsonBean = gson.fromJson(new String(body), SampleBean.class);  
						break;
					case MESSAGE:
						jsonBean = gson.fromJson(new String(body), MessageBean.class);
						break;
					case FILE:
						jsonBean = gson.fromJson(new String(body), FileBean.class); 
						break;
					case COMBINE:
						jsonBean = gson.fromJson(new String(body), CombineBean.class); 
						break;
					}
					busReceiver.receiveObject(jsonBean);
				}
			};
			
			mRabbitChannel.basicConsume(mQueueName, true, consumer);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			mRabbitChannel.close();
		} catch (IOException | TimeoutException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		}
	}
}
