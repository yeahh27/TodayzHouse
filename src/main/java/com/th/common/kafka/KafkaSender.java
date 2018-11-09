package com.th.common.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaSender {
	
	private static Properties makeProps() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("value.serializer", StringSerializer.class);
		props.put("key.serializer", StringSerializer.class);
		return props;
	}
	
	private static KafkaProducer<String, String> makeProducer() {
		KafkaProducer<String, String> producer = new KafkaProducer<>(makeProps());
		return producer;
	}
	
	public static void send(String message) {
		KafkaProducer<String, String> producer = makeProducer();
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("SparkTopic", message);
		
		producer.send(record);
		producer.close();
	}

}
