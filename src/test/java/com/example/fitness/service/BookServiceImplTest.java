package com.example.fitness.service;

import com.example.fitness.exception.BookingException;
import com.example.fitness.exception.InvalidInputException;
import com.example.fitness.model.Booking;
import com.example.fitness.model.ExerciseClass;
import com.example.fitness.repository.InMemoryRepository;
import com.example.fitness.service.ClassService;
import com.example.fitness.service.BookServiceImpl;
import com.example.fitness.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookingService; // Service class to test

    @Mock
    private InMemoryRepository repo; // Mocked InMemoryRepository

    @Mock
    private ClassService classService; // Mocked ClassService

    @Mock
    private ExerciseClass exerciseClass; // Mocked ExerciseClass (for testing bookClass method)

    private List<Booking> bookings;

    @BeforeEach
    void setUp() {
        // Initialize mocks and the test data
        bookings = new ArrayList<>();
        Booking booking1 = new Booking(1L,"John Doe",  LocalDate.now().plusDays(2)); // Valid booking
        Booking booking2 = new Booking(1L,"Jane Smith",  LocalDate.now().plusDays(3)); // Another valid booking
        bookings.add(booking1);
        bookings.add(booking2);
        
        // Mock repository behavior
        when(repo.getBookings()).thenReturn(bookings);
    }

    // Test for the searchBookings method (search by memberName, startDate, endDate)
    @Test
    void testSearchBookings_success() {
        // Arrange
        String memberName = "John Doe";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(3);

        // Act
        List<Booking> result = bookingService.searchBookings(memberName, startDate, endDate);

        // Assert
        assertEquals(1, result.size()); // Only one booking for "John Doe"
        assertEquals("John Doe", result.get(0).getMemberName());
    }

    @Test
    void testSearchBookings_noMemberName() {
        // Arrange: Search without memberName
        String memberName = null;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(3);

        // Act
        List<Booking> result = bookingService.searchBookings(memberName, startDate, endDate);

        // Assert
        assertEquals(2, result.size()); // Both bookings are returned
    }

    @Test
    void testSearchBookings_dateRange() {
        // Arrange: Search within a specific date range
        String memberName = null;
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(2);

        // Act
        List<Booking> result = bookingService.searchBookings(memberName, startDate, endDate);

        // Assert
        assertEquals(1, result.size()); // Only one booking within the date range (John Doe)
    }






   
}
