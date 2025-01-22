package com.example.fitness.controller;

import com.example.fitness.model.Booking;
import com.example.fitness.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private ObjectMapper objectMapper;

    private Booking booking;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this); // This line ensures that mocks are initialized

        // Create the ObjectMapper for converting objects to JSON
        objectMapper = new ObjectMapper();

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();

        // Create a sample booking
        booking = new Booking();
        booking.setClassId(1L);
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.now().plusDays(1)); // setting participation date to a future date
    }

   
    

    @Test
    void testSearchBookings_Success() throws Exception {
        // Arrange: Mock the service to return a list of bookings
        Booking booking1 = new Booking();
        booking1.setClassId(1L);
        booking1.setMemberName("John Doe");
        booking1.setParticipationDate(LocalDate.now().plusDays(1));

        Booking booking2 = new Booking();
        booking2.setClassId(2L);
        booking2.setMemberName("Jane Doe");
        booking2.setParticipationDate(LocalDate.now().plusDays(2));

        List<Booking> bookings = Arrays.asList(booking1, booking2);
        when(bookingService.searchBookings(anyString(), any(), any())).thenReturn(bookings);

        // Act & Assert: Send GET request to /getBookings and check the response
        mockMvc.perform(get("/fitness/bookings/getBookings")
                .param("memberName", "John Doe")
                .param("startDate", LocalDate.now().toString())
                .param("endDate", LocalDate.now().plusDays(7).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[1].memberName").value("Jane Doe"));
    }

    @Test
    void testSearchBookings_NoResults() throws Exception {
        // Arrange: Mock the service to return an empty list
        when(bookingService.searchBookings(anyString(), any(), any())).thenReturn(Arrays.asList());

        // Act & Assert: Send GET request to /getBookings and expect an empty result
        mockMvc.perform(get("/fitness/bookings/getBookings")
                .param("memberName", "Non Existent Member"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
