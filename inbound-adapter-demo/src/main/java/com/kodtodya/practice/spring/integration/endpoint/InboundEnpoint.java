package com.kodtodya.practice.spring.integration.endpoint;

import java.util.List;

import javax.annotation.Resource;

import com.kodtodya.practice.spring.integration.model.Training;
import com.kodtodya.practice.spring.integration.services.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class InboundEnpoint {
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	TrainingService trainingService;

	public Message<?> get(Message<?> msg) {
		log.info("GET method");
		List<Training> trainingList = trainingService.getAll();
		return MessageBuilder.withPayload(trainingList).copyHeadersIfAbsent(msg.getHeaders())
				.setHeader("http_statusCode", HttpStatus.OK).build();
	}
	
	public void post(Message<Training> msg){
		log.info("POST method");
		trainingService.insert(msg.getPayload());
	}
	
	public void put(Message<Training> msg){
		log.info("PUT method");
		trainingService.change(msg.getPayload());
	}
	
	public void delete(Message<String>msg){
		log.info("DELETE method");
		int id = Integer.valueOf(msg.getPayload());
		trainingService.delete(id);
	}
}
