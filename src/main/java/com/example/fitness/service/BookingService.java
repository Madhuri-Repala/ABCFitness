package com.example.fitness.service;

import java.time.LocalDate;
import java.util.List;

import com.example.fitness.model.Booking;

public interface BookingService {

	  public List<Booking> searchBookings(String memberName, LocalDate startDate, LocalDate endDate);
	  
	  public  void bookClass(Booking booking) throws ClassNotFoundException;
}
