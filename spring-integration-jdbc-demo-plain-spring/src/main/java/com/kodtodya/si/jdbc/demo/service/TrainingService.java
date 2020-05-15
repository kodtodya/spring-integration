package com.kodtodya.si.jdbc.demo.service;

import java.util.List;

import com.kodtodya.si.jdbc.demo.domain.Training;

public interface TrainingService {

    Training createTraining(Training training);

    List<Training> findTrainingByName(String name);

}
