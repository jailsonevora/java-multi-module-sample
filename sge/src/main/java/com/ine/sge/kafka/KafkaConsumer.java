package com.ine.sge.kafka;

import com.ine.common.interfaces.kafka.MessageStorage;
import com.ine.sge.dao.IEntidadeRepository;
import com.ine.common.dto.UniverseDefaultSchema.Entidade;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

@Component
public class KafkaConsumer {

	@Autowired
	IEntidadeRepository entidadeRepository;

	private static final Logger LOG = LoggerFactory.getLogger(Entidade.class);


	@KafkaListener(topics = "${ine.kafka.topic.create.entity}", groupId = "ine-universe-group")
	@KafkaHandler
	@Transactional
	public void receiveCreateEntity(@Payload Entidade data,
	                                @Headers MessageHeaders headers) {
		//LOG.info("received Universe data='{}'", data);

		/*headers.keySet().forEach(key -> {
			LOG.info("{}: {}", key, headers.get(key));
		});*/
	}

	/*@KafkaListener(topics = "${ine.kafka.topic.update.entity}", groupId = "ine-universe-group")
	@KafkaHandler
	public void receiveUpdateEntity(@Payload Entidade data,
	                                @Headers MessageHeaders headers) {
		LOG.info("received SGE data='{}'", data);

		headers.keySet().forEach(key -> {
			LOG.info("{}: {}", key, headers.get(key));
		});
	}*/
}
