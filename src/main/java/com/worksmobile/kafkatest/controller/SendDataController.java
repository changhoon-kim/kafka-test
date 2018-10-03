package com.worksmobile.kafkatest.controller;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendDataController {
	@RequestMapping("/internal/sendData")
	public ResponseEntity<String> send() {
		
		// Set properties
		Properties configs = new Properties();
		configs.put("bootstrap.servers", "dev-works-changhoon02.ncl.nfra.io:9092");
		configs.put("acks", "all"); // 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않습니다.
		configs.put("block.on.buffer.full", "true"); // 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리의 바이트수
		configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		// Create producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);
		
		// Send message
		producer.send(new ProducerRecord<String, String>("test", "test message")); // topic, text
		
		// close
		producer.flush();
		producer.close();
		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
}
