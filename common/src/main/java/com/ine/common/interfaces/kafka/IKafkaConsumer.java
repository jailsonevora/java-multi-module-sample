package com.ine.common.interfaces.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public interface IKafkaConsumer {

	void receiveTopicInsert(/*ConsumerRecord<?, ?> */ String consumerRecord);

	void receiveTopicUpdate(ConsumerRecord<?, ?> consumerRecord);

	void receiveTopicDelete(ConsumerRecord<?, ?> consumerRecord);
}
