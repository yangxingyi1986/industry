package com.yunfan.util.bus.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.bus.YunfanBusConnection;

/**
 * kafka消息链接的一个工厂实现
 * @author yangxingyi
 *
 */
public class YunfanKafkaBusConnection implements YunfanBusConnection {
	private static final long serialVersionUID = 356963500515273316L;

	private Logger log = LoggerFactory.getLogger(YunfanKafkaBusConnection.class);
	
	private Properties kafkaConsumerConf = null;
	
	private Properties kafkaProducerConf = null;
	
	/**
	 * 可能在一个application中存在多个发送信道或接受信道
	 */
	transient private List<ReceiveChannel> rChannelList = new ArrayList<ReceiveChannel>();
	
	transient private List<SendChannel> sChannelList = new ArrayList<SendChannel>();
	
	/**
	 * pConnectHost的字符串结构为IP:PORT, ps:192.168.24.129:9092
	 * @param pConnectHost
	 */
	public YunfanKafkaBusConnection(String pConnectHost) {
		try{
			kafkaConsumerConf = new Properties();
			kafkaConsumerConf.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, pConnectHost);
			kafkaConsumerConf.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
			kafkaConsumerConf.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
			kafkaConsumerConf.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
			kafkaConsumerConf.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
			kafkaConsumerConf.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
			kafkaConsumerConf.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
			

			kafkaProducerConf = new Properties();
			kafkaProducerConf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, pConnectHost);
			kafkaProducerConf.put(ProducerConfig.ACKS_CONFIG, "1");
			kafkaProducerConf.put(ProducerConfig.RETRIES_CONFIG, 0);
			kafkaProducerConf.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
			kafkaProducerConf.put(ProducerConfig.LINGER_MS_CONFIG, 1);
			kafkaProducerConf.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
			kafkaProducerConf.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, "10000");
			kafkaProducerConf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
			kafkaProducerConf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 暂时未用到，后续可能会考虑将消费者的链接放在此处释放
	 */
	@Override
	public void close() {
		//TODO 后续可能会考虑将消费者的链接放在此处释放
		for(ReceiveChannel channel : rChannelList){
			try{
				channel.close();
			}catch(Throwable t){
				log.error(t.getMessage(), t);
			}
		}
		for(SendChannel channel : sChannelList){
			try{
				channel.close();
			}catch(Throwable t){
				if(log.isErrorEnabled()){
					log.error(t.getMessage(), t);
				}
			}
		}
	}

	/**
	 * pBingdingKey暂时针对kafka未使用，但是RabbitMQ用到了
	 */
	@Override
	public ReceiveChannel createReceiveChannel(String topic, List<String> pBingdingKey) {
		ReceiveChannel channel = new KafkaReceiveChannel(kafkaConsumerConf, topic, pBingdingKey);
		rChannelList.add(channel);
		return channel;
	}

	/**
	 * 获取发送消息即生产者的信道
	 */
	@Override
	public SendChannel createSendChannel(String topic) {
		SendChannel channel = new KafkaSendChannel(kafkaProducerConf, topic);
		sChannelList.add(channel);
		return channel;
	}

}
