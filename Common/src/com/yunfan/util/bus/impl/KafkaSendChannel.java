package com.yunfan.util.bus.impl;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.model.JsonBean;

/**
 * MRabbit队列的消息发送通道
 * @author yangxingyi
 *
 */
public class KafkaSendChannel implements SendChannel{
	private static final long serialVersionUID = 1334712507646211158L;
	private Logger log = LoggerFactory.getLogger("KAFKA");
	transient private Producer<String, Object> producer;
	private String topic;
	
	public KafkaSendChannel(Properties props, String topic) {
		try{
			producer = new KafkaProducer<String, Object>(props);
			this.topic = topic; 
		}catch (Throwable ex){
			if(log.isErrorEnabled()){
				log.error(ex.getMessage(), ex);
			}
		}
	}
	
	/**
	 * 
	 */
	public void sendObject(JsonBean pJsonBean, String pRoutingKey){
		try{
//			mChannel.basicPublish(mChannelName, pRoutingKey, null, pJsonBean.objectToJsonlet().getBytes());
			producer.send(new ProducerRecord<String, Object>(topic, null, pJsonBean.objectToJsonlet()));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 关闭当前链接的信道
	 */
	@Override
	public void close() {
		producer.flush();
		producer.close();
	}
}
