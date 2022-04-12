package com.ine.common.interfaces.kafka;

public interface IKafkaProducer {

	void send(String topic, String payload);
}
