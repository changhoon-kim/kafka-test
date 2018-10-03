package com.worksmobile.kafkatest;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkatestApplication.class, args);
		
		System.out.println("Start subscribe");
		
		// Set properties
		Properties configs = new Properties();
		configs.put("bootstrap.servers", "dev-works-changhoon02.ncl.nfra.io:9092");
		configs.put("session.timeout.ms", "10000");
		configs.put("group.id", "test"); // topic
		configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		// create consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);
		consumer.subscribe(Arrays.asList("test")); // topic
		
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(500);
			for (ConsumerRecord<String, String> record : records) {
				String s = record.topic();
				System.out.println("Topic: " + s);
				if ("test".equals(s)) {
					System.out.println(record.value());
				} else {
					throw new IllegalStateException("Get message on topic " + record.topic());
				}
			}
		}
	}
}
