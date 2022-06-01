package com.srm.core.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.srm.core.model.SeatBooking;

@Repository
public interface SeatBookingRepository extends MongoRepository<SeatBooking,String> {
	
	
	public List<SeatBooking> findBookingBySeatId(String seatId);
	
	public SeatBooking findBySeatId(String seatId);
	
	

	
}
