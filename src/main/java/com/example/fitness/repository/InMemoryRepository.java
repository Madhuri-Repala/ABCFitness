package com.example.fitness.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.fitness.model.Booking;
import com.example.fitness.model.ExerciseClass;

@Repository
public class InMemoryRepository {

	private List<ExerciseClass> classes = new ArrayList<>();
	private List<Booking> bookings = new ArrayList<>();

	public List<ExerciseClass> getClasses() {
		return this.classes;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void addClass(ExerciseClass cls) {
		classes.add(cls);
	}

	public void addBooking(Booking booking) {
		bookings.add(booking);
	}

}
