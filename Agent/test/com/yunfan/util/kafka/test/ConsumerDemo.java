package com.yunfan.util.kafka.test;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerDemo {
	public static void main(String[] args){
		Properties prop = new Properties();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.121:9092");
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
		prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
		consumer.subscribe(Arrays.asList("my-topic"));
		while(true){
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> tmp : records){
				System.out.printf("offset = %d  , key= %s, value=%s \n",tmp.offset(), tmp.key(), tmp.value());
			}
		}
	}
}
