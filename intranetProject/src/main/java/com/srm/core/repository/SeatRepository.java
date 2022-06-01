package com.srm.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.srm.core.model.Seat;
import com.srm.core.model.SeatBooking;

@Repository
public interface SeatRepository extends MongoRepository<Seat, String> {

	public List<Seat> findSeatByStatus(String status);
	
	public List<Seat> findSeatByDept(String dept);
	
	//public List<Seat> findByDate(Date fromDate);
	


	
}

