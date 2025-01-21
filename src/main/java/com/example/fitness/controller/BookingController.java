package com.example.fitness.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.model.Booking;
import com.example.fitness.service.BookingService;

@RestController()
@RequestMapping("/fitness/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/createBooking")
	public ResponseEntity<String> createBooking(@RequestBody Booking booking ) throws ClassNotFoundException{
		
		bookingService.bookClass(booking);
		return ResponseEntity.ok(" Booking done sucessfully !!");
		
	}
	
	@GetMapping("/getBookings")
    public ResponseEntity<List<Booking>> searchBookings(@RequestParam(required = false) String memberName,
                                                         @RequestParam(required = false) LocalDate startDate,
                                                         @RequestParam(required = false) LocalDate endDate) {
        List<Booking> bookings = bookingService.searchBookings(memberName, startDate, endDate);
        return ResponseEntity.ok(bookings); 

    }
}  
