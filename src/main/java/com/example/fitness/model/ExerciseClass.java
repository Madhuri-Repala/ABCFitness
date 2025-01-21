package com.example.fitness.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExerciseClass {

	    private Long id;
	    private String name;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private LocalTime startTime;
	    private int duration; // in minutes
	    private int capacity;
	    private int bookingsCount;
	    
	    public ExerciseClass(){
	    	
	    }
	    
		public ExerciseClass(Long id, String name, LocalDate startDate, LocalDate endDate, LocalTime startTime, int duration,
				int capacity, int bookingsCount) {
			super();
			this.id = id;
			this.name = name;
			this.startDate = startDate;
			this.endDate = endDate;
			this.startTime = startTime;
			this.duration = duration;
			this.capacity = capacity;
			this.bookingsCount = bookingsCount;
		}
		
		

		@Override
		public String toString() {
			return "Class [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
					+ ", startTime=" + startTime + ", duration=" + duration + ", capacity=" + capacity
					+ ", bookingsCount=" + bookingsCount + "]";
		}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		public LocalTime getStartTime() {
			return startTime;
		}
		public void setStartTime(LocalTime startTime) {
			this.startTime = startTime;
		}
		public int getDuration() {
			return duration;
		}
		public void setDuration(int duration) {
			this.duration = duration;
		}
		public int getCapacity() {
			return capacity;
		}
		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}
		public int getBookingsCount() {
			return bookingsCount;
		}
		public void setBookingsCount(int bookingsCount) {
			this.bookingsCount = bookingsCount;
		}
	    
	    
}
