package com.srm.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.core.model.Seat;
import com.srm.core.model.SeatBooking;
import com.srm.core.repository.SeatBookingRepository;

@Service
public class SeatBookingService {

	@Autowired
	private SeatBookingRepository seatBookingRepository;
	
	public SeatBooking create(SeatBooking seatBooking) {
		return seatBookingRepository.save(seatBooking);
	}
	
	public List<SeatBooking> getAllSeats() {
		return seatBookingRepository.findAll();	
	}
	
	public Optional<SeatBooking> getBySeatNo(String seatId){
		return seatBookingRepository.findById(seatId);
	}
	
	public SeatBooking modify(SeatBooking seatBooking) {
		return seatBookingRepository.save(seatBooking);
	}
	
	public void delete(String seatId) {
		SeatBooking b = seatBookingRepository.findBySeatId(seatId);
		seatBookingRepository.delete(b);
	}
}
