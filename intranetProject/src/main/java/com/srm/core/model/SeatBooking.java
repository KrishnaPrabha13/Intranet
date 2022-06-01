package com.srm.core.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "SeatBooking")
public class SeatBooking {
	
	@Id
	private String seatId;
	
	@Field(name = "User Id")
	private String userId;
	
	@Field(name = "Approver")
	private String approver;
	
	@Field(name = "Status")
	private String status;
	
	@CreatedDate
	@Field(name = "Created At")
	private Date createDate;
	
	@LastModifiedDate
	@Field(name = "Updated At")
	private Date modifiedDate;

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public SeatBooking(String seatId, String userId, String approver, String status, Date createDate, Date modifiedDate) {
		super();
		this.seatId = seatId;
		this.userId = userId;
		this.approver = approver;
		this.status = status;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "SeatBooking [seatId=" + seatId + ", userId=" + userId + ", approver=" + approver + ", status=" + status
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}

	
	
}
