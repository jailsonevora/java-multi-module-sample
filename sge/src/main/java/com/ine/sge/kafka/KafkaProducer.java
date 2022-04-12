package com.ine.sge.kafka;

import com.ine.common.dto.UniverseDefaultSchema.Entidade;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
		import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Entidade> kafkaTemplateEntidade;

	@Value("${ine.kafka.topic}")
	String kafkaTopic;

	public void send(Entidade payload) {
		Message<Entidade> message = MessageBuilder
				.withPayload(payload)
				.setHeader(KafkaHeaders.TOPIC, kafkaTopic)
				.build();
		kafkaTemplateEntidade.send(message);
		System.out.println("Message from SGE to Universe");
		//System.out.println("Message SGE in byte: " + message + " sent to topic: " + kafkaTopic);
	}
}