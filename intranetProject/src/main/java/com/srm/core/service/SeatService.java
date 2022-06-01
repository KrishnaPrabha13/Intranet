package com.srm.core.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.core.model.Seat;
import com.srm.core.repository.SeatRepository;

@Service
public class SeatService {

	@Autowired
	private SeatRepository seatRepository;
	
	
	public Seat create(Seat seat) {
		return seatRepository.save(seat);
	}

	
	public List<Seat> getAllSeats() {
		return seatRepository.findAll();	
	}
	
	public List<Seat> getAllBystatus(String status){
		return seatRepository.findSeatByStatus(status);
	}
	
	public List<Seat> getAllByDept(String dept){
		return seatRepository.findSeatByDept(dept);
	}
	public Optional<Seat> getBySeatNo(String seatNo){
		return seatRepository.findById(seatNo);
	}
	
//	public List<Seat> getByDate(Date fromDate){
//		return seatRepository.findByDate(fromDate);
//	}
}


