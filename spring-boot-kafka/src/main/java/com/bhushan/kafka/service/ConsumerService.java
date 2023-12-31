package com.bhushan.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bhushan.kafka.model.SuperObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumerService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@KafkaListener(topics = {
			"${spring.kafka.topic}" }, containerFactory = "kafkaListenerStringFactory", groupId = "group_id")
	public void consumeMessage(String message) {
		logger.info("**** -> Consumed message -> {}", message);
	}

	@KafkaListener(topics = {
			"${spring.kafka.emails-topic}" }, containerFactory = "kafkaListenerJsonFactory", groupId = "group_id")
	public void consumesuperObject(SuperObject superObject) {
		logger.info("**** -> Consumed superObject -> {}", superObject);

	}

	@KafkaListener(topics = {
			"${tpd.simple-topic-name}" }, containerFactory = "kafkaListenerStringFactory", groupId = "group_id")
	public void consumesuperObjectPartition(String message) {
		logger.info("**** -> Consumed message -> {}", message);
	}

}
