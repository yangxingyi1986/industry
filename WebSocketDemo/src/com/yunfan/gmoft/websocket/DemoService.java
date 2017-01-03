package com.yunfan.gmoft.websocket;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

@Service
public class DemoService {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

	public DemoService(){
		SystemWebSocketHandler systemWebSocketHandler = new SystemWebSocketHandler();
		Thread t1 = new Thread(new SendDirectMsg(systemWebSocketHandler));
		Thread t2 = new Thread(new SendSparkMsg(systemWebSocketHandler));
		t1.start();
		t2.start();
	}
	
	public static void main(){
		Properties prop = new Properties();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "123.57.13.84:9092");
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
		prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
		consumer.subscribe(Arrays.asList("test2"));
		while(true){
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> tmp : records){
				System.out.printf("offset = %d  , key= %s, value=%s \n",tmp.offset(), tmp.key(), tmp.value());
			}
		}
	}
}

class SendSparkMsg implements Runnable {
	SystemWebSocketHandler systemWebSocketHandler = null;
	public SendSparkMsg(SystemWebSocketHandler systemWebSocketHandler){
		this.systemWebSocketHandler = systemWebSocketHandler;
	}
	
	public void run() {
		Properties prop = new Properties();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "123.57.13.84:9092");
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, "test-1");
		prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
		consumer.subscribe(Arrays.asList("test2"));
		while(true){
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> tmp : records){
				System.out.printf("offset = %d  , key= %s, value=%s \n",tmp.offset(), tmp.key(), tmp.value());
				if(systemWebSocketHandler != null){
					systemWebSocketHandler.sendMessageToUsers(new TextMessage(tmp.value()+ DemoService.sdf.format(new Date()) + "  spark"));
				}
			}
		}
	}
	
}


class SendDirectMsg implements Runnable {
	SystemWebSocketHandler systemWebSocketHandler = null;
	public SendDirectMsg(SystemWebSocketHandler systemWebSocketHandler){
		this.systemWebSocketHandler = systemWebSocketHandler;
	}
	
	public void run() {
		Properties prop = new Properties();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "123.57.13.84:9092");
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
		prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
		consumer.subscribe(Arrays.asList("test1"));
		while(true){
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> tmp : records){
				if(systemWebSocketHandler != null){
					systemWebSocketHandler.sendMessageToUsers(new TextMessage(tmp.value()+ DemoService.sdf.format(new Date()) + "  direct"));
				}
			}
		}
	}
	
}