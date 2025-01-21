package com.example.fitness.model;

import java.time.LocalDate;

public class Booking {
	
	    private Long classId;
	    private String memberName;
	    private LocalDate participationDate;
	    
	    public Booking() {
	    	
	    }
	    
	    

		public Booking(Long classId, String memberName, LocalDate participationDate) {
			super();
			this.classId = classId;
			this.memberName = memberName;
			this.participationDate = participationDate;
		}



		@Override
		public String toString() {
			return "Booking [classId=" + classId + ", memberName=" + memberName + ", participationDate="
					+ participationDate + "]";
		}



		public Long getClassId() {
			return classId;
		}

		public void setClassId(Long classId) {
			this.classId = classId;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public LocalDate getParticipationDate() {
			return participationDate;
		}

		public void setParticipationDate(LocalDate participationDate) {
			this.participationDate = participationDate;
		}
	    
	    
	

}
