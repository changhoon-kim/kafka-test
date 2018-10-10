package com.worksmobile.kafkatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.kafkatest.producer.Sender;

@RestController
public class SendDataController {

	@Autowired
	private Sender sender;

	@RequestMapping("/internal/sendData")
	public ResponseEntity<String> sendData() {

		sender.send("test message");

		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
}
