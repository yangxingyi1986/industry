package com.yunfan.util.bus.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunfan.util.bus.BusReceiver;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.model.MessageBean;

/**
 * Kafka接受消息的消费通道
 * @author yangxingyi
 *
 */
public class KafkaReceiveChannel implements ReceiveChannel {

	private Logger log = LoggerFactory.getLogger("KAFKA");
	
	transient private Consumer<String, Object> consumer = null;
	
	private boolean RECEIVE_FLAG = true;
	
	public KafkaReceiveChannel(Properties prop, String topic, List<String> bindingKeyList) {
		try{
			consumer = new KafkaConsumer<String, Object>(prop);
			consumer.subscribe(Arrays.asList(topic));
		}catch (Throwable ex){
			if(log.isErrorEnabled()){
				log.error(ex.getMessage(), ex);
			}
		}
	}
	
	/*
	 * 消费函数
	 */
	public void setBusReceiver(final BusReceiver busReceiver){
		try{
			while(RECEIVE_FLAG){
				ConsumerRecords<String, Object> records = consumer.poll(100);
				for(ConsumerRecord<String, Object> tmp : records){
					MessageBean pJsonBean = new MessageBean();
					pJsonBean.setId(tmp.key());
					pJsonBean.setValue(String.valueOf(tmp.value()));
					busReceiver.receiveObject(pJsonBean);
				}
			}
		}catch(Throwable ex){
			if(log.isErrorEnabled()){
				log.error(ex.getMessage(), ex);
			}
		}
	}

	/**
	 * 关闭通道方法
	 */
	@Override
	public void close() {
		RECEIVE_FLAG = false;
		//暂停一秒再释放资源，
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		}
		consumer.close();
	}
}
