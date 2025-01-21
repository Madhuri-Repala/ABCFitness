package com.example.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.model.ExerciseClass;
import com.example.fitness.service.ClassService;

@RestController
@RequestMapping("/fitness/classes")
public class ClassController {

	
	@Autowired
	private ClassService classService;
	
	
	@PostMapping("/post")
	public ResponseEntity<String>  createClass(@RequestBody ExerciseClass cls){
		
		classService.createClass(cls);
		return ResponseEntity.ok("Created Class Successfully !! ");
	}
	
	
}
