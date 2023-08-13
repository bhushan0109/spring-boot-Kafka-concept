package com.bhushan.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.bhushan.kafka.model.SuperObject;

@Service
public class ProducerService<T> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${spring.kafka.topic}")
	private String topic;

	@Value("${spring.kafka.emails-topic}")
	private String superObjectTopic;

	@Value("${tpd.simple-topic-name}")
	private String simple_topic_name;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, T> kafkaTemplateSuperObject;

	public void sendMessage(String message) {
		kafkaStringSend(message);
	}

	public void sendSuperObjectMessage(T superObject) {
		//kafkaTemplateSuperObject.send(superObjectTopic,superObject);
		kafkaJsonObjectSend(superObject);
	}

	public void sendMessageToKafkaTopicPartition(String message) {
		for (int i = 0; i < 100000; i++) {
			String name = i + "_" + message;
			kafkaStringSend(name);
		}

	}

	@Async
	public void kafkaStringSend(String name) {
		// run the background process
		logger.info("#### ->kafkaStringSend Publishing  message -> {}", name);

		// run the background process
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(simple_topic_name, name);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onFailure(Throwable ex) {
				logger.error(ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("MESSAGE SEND SUCCESSFULLY");
			}

		});

	}

	@Async
	public void kafkaJsonObjectSend(T superObject) {
		// run the background process
		logger.info("#### ->kafkaJsonObjectSend Publishing  message -> {}", superObject);
		ListenableFuture<SendResult<String, T>> listenableFuture = kafkaTemplateSuperObject.send(superObjectTopic,
				superObject);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {
			@Override
			public void onFailure(Throwable ex) {
				logger.error(ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, T> result) {
				logger.info("MESSAGE SEND SUCCESSFULLY");
			}

		});

	}

}