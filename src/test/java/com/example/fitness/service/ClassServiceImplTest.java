package com.example.fitness.service;


import com.example.fitness.exception.InvalidInputException;
import com.example.fitness.model.ExerciseClass;
import com.example.fitness.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClassServiceImplTest {

    @Mock
    private InMemoryRepository repo;

    @InjectMocks
    private ClassServiceImpl classService;

    private ExerciseClass validClass;
    private ExerciseClass invalidClass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Create test data
        validClass = new ExerciseClass();
        validClass.setId(1L);
        validClass.setName("Yoga");
        validClass.setStartDate(LocalDate.now().plusDays(1));
        validClass.setEndDate(LocalDate.now().plusDays(5));
        validClass.setStartTime(LocalTime.of(10, 0));
        validClass.setDuration(60);
        validClass.setCapacity(10);
        validClass.setBookingsCount(0);

        invalidClass = new ExerciseClass();
        invalidClass.setId(2L);
        invalidClass.setName("Yoga");
        invalidClass.setStartDate(LocalDate.now().plusDays(1));
        invalidClass.setEndDate(LocalDate.now().minusDays(1)); // Invalid end date
        invalidClass.setStartTime(LocalTime.of(10, 0));
        invalidClass.setDuration(60);
        invalidClass.setCapacity(0); // Invalid capacity
        invalidClass.setBookingsCount(0);
    }

    @Test
    void testCreateClass_ValidInput() {
        // Arrange: valid class input
        when(repo.getClasses()).thenReturn(java.util.Collections.emptyList());

        // Act
        classService.createClass(validClass);

        // Assert
        verify(repo, times(1)).addClass(validClass); // Verify addClass is called once
    }

    @Test
    void testCreateClass_InvalidCapacity() {
        // Arrange: Invalid class with capacity less than 1
        ExerciseClass invalidCapacityClass = new ExerciseClass();
        invalidCapacityClass.setId(3L);
        invalidCapacityClass.setName("Yoga");
        invalidCapacityClass.setStartDate(LocalDate.now().plusDays(1));
        invalidCapacityClass.setEndDate(LocalDate.now().plusDays(5));
        invalidCapacityClass.setStartTime(LocalTime.of(10, 0));
        invalidCapacityClass.setDuration(60);
        invalidCapacityClass.setCapacity(0); // Invalid capacity
        invalidCapacityClass.setBookingsCount(0);

        // Act & Assert: Expect InvalidInputException for invalid capacity
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> classService.createClass(invalidCapacityClass));
        assertEquals("Capacity should be atleast 1", exception.getMessage());
    }

    @Test
    void testCreateClass_InvalidEndDate() {
        // Arrange: Invalid class with end date in the past
        ExerciseClass invalidEndDateClass = new ExerciseClass();
        invalidEndDateClass.setId(4L);
        invalidEndDateClass.setName("Yoga");
        invalidEndDateClass.setStartDate(LocalDate.now().plusDays(1));
        invalidEndDateClass.setEndDate(LocalDate.now().minusDays(1)); // Invalid end date
        invalidEndDateClass.setStartTime(LocalTime.of(10, 0));
        invalidEndDateClass.setDuration(60);
        invalidEndDateClass.setCapacity(10);
        invalidEndDateClass.setBookingsCount(0);

        // Act & Assert: Expect InvalidInputException for past end date
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> classService.createClass(invalidEndDateClass));
        assertEquals("End date should be Date in future", exception.getMessage());
    }

    @Test
    void testGetClassById_ClassExists() throws ClassNotFoundException {
        // Arrange: Mock the repository to return a class when searched by ID
        when(repo.getClasses()).thenReturn(java.util.Collections.singletonList(validClass));

        // Act: Fetch the class by ID
        ExerciseClass fetchedClass = classService.getClassById(1L);

        // Assert: Check that the correct class is fetched
        assertEquals(validClass, fetchedClass);
    }

    @Test
    void testGetClassById_ClassNotFound() {
        // Arrange: Repository returns no classes
        when(repo.getClasses()).thenReturn(java.util.Collections.emptyList());

        // Act & Assert: Expect ClassNotFoundException when the class is not found by ID
        ClassNotFoundException exception = assertThrows(ClassNotFoundException.class, () -> classService.getClassById(999L));
        assertEquals("class not found with id:999", exception.getMessage());
    }
}
