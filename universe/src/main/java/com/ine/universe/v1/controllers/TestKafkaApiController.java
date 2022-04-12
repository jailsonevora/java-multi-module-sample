package com.ine.universe.v1.controllers;


import com.ine.common.dto.UniverseDefaultSchema.Entidade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ine.common.interfaces.kafka.MessageStorage;

import com.ine.universe.kafka.KafkaProducer;

import javax.validation.Valid;

@RestController("TestKafkaApiV1")
@RequestMapping("/api/universe/v1")
@Api(value = "testkafkaapi", description = "Test Kafka communication between consumer and producer API")
public class TestKafkaApiController {

	private final KafkaProducer producer;
	private final MessageStorage storage;

	@Autowired
	public TestKafkaApiController(KafkaProducer producer, MessageStorage storage) {
		this.producer = producer;
		this.storage = storage;
	}

	@RequestMapping(value = "/consumer", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves message")
	public ResponseEntity<String> receive(){

		String messages = storage.toString();

		System.out.println("Receiver on topicInsert: "+messages);
		storage.clear();
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}

	@RequestMapping(value = "/producer/{topics}/{message}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Send message", notes="Send message to consumer")
	public ResponseEntity<Void> send(@Valid @PathVariable String topics, @Valid @PathVariable Entidade message){

		producer.send(message);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}