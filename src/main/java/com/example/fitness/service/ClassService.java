package com.example.fitness.service;

import com.example.fitness.model.ExerciseClass;

public interface ClassService {

	public void createClass(ExerciseClass cls);
	
	public ExerciseClass getClassById(Long id) throws ClassNotFoundException;
	
}
