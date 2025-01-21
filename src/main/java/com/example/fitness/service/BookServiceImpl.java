package com.example.fitness.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.exception.BookingException;
import com.example.fitness.exception.InvalidInputException;
import com.example.fitness.model.Booking;
import com.example.fitness.model.ExerciseClass;
import com.example.fitness.repository.InMemoryRepository;


@Service
public class BookServiceImpl implements BookingService{
	
	@Autowired
	private InMemoryRepository repo;
	
	@Autowired
	private ClassService classService;

	@Override
	public List<Booking> searchBookings(String memberName, LocalDate startDate, LocalDate endDate) {
	   
		return repo.getBookings().stream()
				.filter(b -> (memberName == null || b.getMemberName().equals(memberName)) &&
                        (startDate == null || !b.getParticipationDate().isBefore(startDate)) &&
                        (endDate == null || !b.getParticipationDate().isAfter(endDate)))
                .toList();
	}

	@Override
	public void bookClass(Booking booking) throws ClassNotFoundException {
		
		ExerciseClass cls=classService.getClassById(booking.getClassId());
		
		if (booking.getParticipationDate().isBefore(LocalDate.now())) {
            throw new InvalidInputException("Participation date must be in the future");
        }
		if(booking.getParticipationDate().isAfter(cls.getEndDate())) {
			  throw new InvalidInputException("Participation date must be before class end date :"+ cls.getEndDate());
		}
        if (cls.getBookingsCount() >= cls.getCapacity()) {
            throw new BookingException("Class is at full capacity");
        }
        
        repo.addBooking(booking);
        
        cls.setBookingsCount(cls.getBookingsCount() + 1);
	}

}
