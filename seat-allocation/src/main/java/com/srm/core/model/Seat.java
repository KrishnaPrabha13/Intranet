package com.srm.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "Seat")
public class Seat {
	
	
	@Transient
	public static final String SEQUENCE_NAME = "Total_Seat";
 
	
	@Id
	private int seatId;
	
	@Indexed(unique = true)
	private String seatNo;
	
	@Field("department")
	private String dept;
	
	@Field("location")
	private String location;
	
	@Field("city")
	private String city;
	
//	@Field("Status")
//	private String status;
	
	@Field("date")
	@DateTimeFormat(pattern = "DD/MM/YYYY")
	private String fromDate;

	private SeatBooking seatbooking;

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	
	public SeatBooking getSeatbooking() {
		return seatbooking;
	}

	public void setSeatbooking(SeatBooking seatbooking) {
		this.seatbooking = seatbooking;
	}

	public Seat(int seatId, String seatNo, String dept, String location, String city,String fromDate) {
		super();
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.dept = dept;
		this.location = location;
		this.city = city;
//		this.status = status;
		this.fromDate = fromDate;
	}

	@Override
	public String toString() {
		return "Seat [ seatId=" + seatId + ", seatNo=" + seatNo + ", dept=" + dept + ", location=" + location + ", city=" + city
				+ ", fromDate=" + fromDate + "]";
	}
	
	
	
}
