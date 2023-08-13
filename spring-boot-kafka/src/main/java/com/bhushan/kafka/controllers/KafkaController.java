package com.bhushan.kafka.controllers;

import com.bhushan.kafka.model.SuperObject;
import com.bhushan.kafka.service.ProducerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProducerService<SuperObject> producerService;

	@GetMapping(value = "/publish")
	public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
		producerService.sendMessage(message);
		return "Successfully publisher message..!";
	}

	@PostMapping(value = "/publish")
	public Map<String, Object> sendObjectToKafkaTopic(@RequestBody SuperObject superObject) {
		producerService.sendSuperObjectMessage(superObject);

		Map<String, Object> map = new HashMap<>();
		map.put("message", "Successfully publisher  superObject..!");
		map.put("payload", superObject);

		return map;
	}

	@GetMapping(value = "/publish/partition")
	public String sendMessageToKafkaTopicPartition(@RequestParam("message") String message) {
		producerService.sendMessageToKafkaTopicPartition(message);
		return "Successfully publisher message..!";
	}
}
