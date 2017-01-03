package com.yunfan.util.kafka.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import net.sf.json.JSONObject;

public class ProducerDemo {

	public static void main(String[] args) throws InterruptedException{
		 Properties props = new Properties();
		 props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "123.57.13.84:9092");
		 props.put(ProducerConfig.ACKS_CONFIG, "all");
		 props.put(ProducerConfig.RETRIES_CONFIG, 0);
		 props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		 props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		 props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		 props.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, "10000");
		 props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		 props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

		 Producer<String, String> producer = new KafkaProducer<>(props);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 JSONObject jsonObject = null;
		 for(int i = 0; i < 100; i++){
			 String date = sdf.format(new Date());
			 System.out.println(sdf.format(new Date()));
			 jsonObject = new JSONObject();
			 System.out.println(sdf.format(new Date()));
			 jsonObject.accumulate("id", i);
			 jsonObject.accumulate("value", i);
			 jsonObject.accumulate("date", date);
			 System.out.println(sdf.format(new Date()));
			 System.out.println(jsonObject.toString());
			 producer.send(new ProducerRecord<String, String>("test1", Integer.toString(i), jsonObject.toString()));
			 Thread.sleep(200);
			 JSONObject obj = JSONObject.fromObject(jsonObject.toString());
		 }
		 producer.flush();
		 producer.close();
 	}
}
