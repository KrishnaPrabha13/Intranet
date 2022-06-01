package com.srm.core.model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "Seat")
public class Seat {
	
	@Field("Seat Id")
	private int seatId;
	
	@Id
	private String seatNo;
	
	@Field("City")
	private String city;
	
	@Field("Location")
	private String location;
	
	@Field("Department")
	private String dept;
	
	@Field("Status")
	private String status;
	
	
	@DateTimeFormat
	private Date fromDate;
	
	

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date localDate) {
		this.fromDate = localDate;
	}

	public Seat(int seatId, String seatNo, String city, String location, String dept, String status, Date fromDate) {
		super();
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.city = city;
		this.location = location;
		this.dept = dept;
		this.status = status;
		this.fromDate = fromDate;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatNo=" + seatNo + ", city=" + city + ", location=" + location + ", dept="
				+ dept + ", status=" + status + ", fromDate=" + fromDate + "]";
	}


	

	

	
	
	
}
