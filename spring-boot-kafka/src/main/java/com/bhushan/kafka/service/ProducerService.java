package com.bhushan.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.kafka.topic}")
    private String topic;

    @Value("${spring.kafka.emails-topic}")
    private String superObjectTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, T> kafkaTemplatesuperObject;


    public void sendMessage(String message) {
        logger.info("#### -> Publishing message -> {}", message);
        kafkaTemplate.send(topic, message);
    }


    public void sendSuperObjectMessage(T superObject) {
        logger.info("#### -> Publishing superObject :: {}", superObject);
        kafkaTemplatesuperObject.send(superObjectTopic, superObject);
    }
}
