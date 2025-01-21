package com.example.fitness.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.exception.InvalidInputException;
import com.example.fitness.model.ExerciseClass;
import com.example.fitness.repository.InMemoryRepository;


@Service
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	private InMemoryRepository repo;

	@Override
	public void createClass(ExerciseClass cls) {
		
		if(cls != null  && cls.getCapacity() < 1) {
			throw new InvalidInputException("Capacity should be atleast 1");
		}
		
		if(cls != null && cls.getEndDate().isBefore(LocalDate.now())) {
			throw new InvalidInputException("End date should be Date in future");
		}
		
		cls.setBookingsCount(0);
		
		repo.addClass(cls);
		
	}

	@Override
	public ExerciseClass getClassById(Long id) throws ClassNotFoundException {
	
		
		return repo.getClasses().stream()
				   .filter(cls->cls.getId().equals(id))
				   .findFirst()
				   .orElseThrow(() -> new ClassNotFoundException("class not found with id:"+ id));
	}

}
