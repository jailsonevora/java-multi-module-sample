package com.ine.sge.v1.controllers;


import com.ine.sge.dao.ITipoEntidadeRepository;
import com.ine.common.dto.UniverseDefaultSchema.Entidade;
import com.ine.sge.models.TipoEntidade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ine.common.interfaces.kafka.MessageStorage;

import com.ine.sge.kafka.KafkaProducer;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController("TestKafkaApiV1")
@RequestMapping("/api/v1")
@Api(value = "testkafkaapi", description = "Test Kafka communication between consumer and producer API")
public class TestKafkaApiController {

	private final KafkaProducer producer;
	private final MessageStorage storage;
	private final ITipoEntidadeRepository tipoEntidade;

	@Autowired
	public TestKafkaApiController(KafkaProducer producer, MessageStorage storage, ITipoEntidadeRepository tipoEntidade) {
		this.producer = producer;
		this.storage = storage;
		this.tipoEntidade = tipoEntidade;
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

	@Transactional
	@RequestMapping(value = "/tipoentidades", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new entities types", notes="The newly created entities types Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody TipoEntidade tipo){

		tipo = tipoEntidade.save(tipo);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipo.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}
}
