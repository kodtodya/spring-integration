package com.kodtodya.practice.spring.integration.services;

import com.kodtodya.practice.spring.integration.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainingService {
	Map<Integer, Training> trainingStorage = new HashMap<Integer, Training>();
	
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@PostConstruct
	public void init(){
		Training java11 = new Training(1, "Java-11", "OOPs, logic building", 2);
		Training kafka = new Training(2, "Kafka", "Java-11, JMS, Spring, Spring Boot", 3);

		trainingStorage.put(java11.getId(), java11);
		trainingStorage.put(kafka.getId(), kafka);
	}
	
	public void insert(Training training){
		trainingStorage.put(training.getId(), training);
		log.info("Training after POST:");
		for (Map.Entry<Integer, Training> entry : trainingStorage.entrySet()) {
			log.info(entry.getValue().toString());
		}
	}
	
	public List<Training> getAll(){
		
		List<Training> result = trainingStorage.entrySet().stream()
		        .map(entry -> entry.getValue())
		        .collect(Collectors.toList());
		
		return result;
	}
	
	public void delete(int id){
		try{
			trainingStorage.remove(id);
		}catch(Exception e){
		}

	}
	
	public void change(Training newTraining){
		trainingStorage.put(newTraining.getId(), newTraining);
		log.info("Training after PUT:");
		for (Map.Entry<Integer, Training> entry : trainingStorage.entrySet()) {
			log.info(entry.getValue().toString());
		}
	}
}