package com.ine.universe.kafka;


import com.ine.common.dto.UniverseDefaultSchema.Entidade;
import com.ine.universe.dao.IEntidadeRepository;
import com.ine.universe.util.BindingUniverseDefaultSchemaToUniverse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class KafkaConsumer {

	@Autowired
	IEntidadeRepository entidadeRepository;

	private static final Logger LOG = LoggerFactory.getLogger(Entidade.class);


	@KafkaListener(topics = "${ine.kafka.topic.create.entity}", groupId = "ine-sge-group")
	@KafkaHandler
	@Transactional
	public void receiveCreateEntity(@Payload Entidade data,
	                                @Headers MessageHeaders headers) {
		LOG.info("received from SGE data");
		//LOG.info("received from SGE data='{}'", data);
		/*headers.keySet().forEach(key -> {
					LOG.info("{}: {}", key, headers.get(key));
				});*/

		com.ine.universe.models.Entidade recivedEntidade = new com.ine.universe.models.Entidade();

		entidadeRepository.save(BindingUniverseDefaultSchemaToUniverse.Binding(recivedEntidade, data));
	}

	/*@KafkaListener(topics = "${ine.kafka.topic.update.entity}", groupId = "ine-sge-group")
	@KafkaHandler
	public void receiveUpdateEntity(@Payload Entidade data,
	                                @Headers MessageHeaders headers) {
		LOG.info("received SGE data='{}'", data);

		headers.keySet().forEach(key -> {
			LOG.info("{}: {}", key, headers.get(key));
		});
	}*/
}
