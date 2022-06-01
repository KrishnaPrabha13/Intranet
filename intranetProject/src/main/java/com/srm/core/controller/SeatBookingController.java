package com.srm.core.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srm.core.model.Seat;
import com.srm.core.model.SeatBooking;
import com.srm.core.repository.SeatBookingRepository;
import com.srm.core.service.SeatBookingService;

@RestController
public class SeatBookingController {
	
	@Autowired
	private SeatBookingService seatBookingService;
	
	@PostMapping("/booking/postBooking")
	public ResponseEntity<?> create(@RequestBody SeatBooking seatBooking){
	try {
//		Date createdate = new Date();
//		seatBooking.setCreateDate(createdate);
		//seatBooking.setModifiedDate(null);
		seatBookingService.create(seatBooking);
		return new ResponseEntity<>("seat booked", HttpStatus.CREATED);
		} catch ( Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/booking/getBooking")
	public ResponseEntity<?> getAllSeats(@RequestParam(required = false) String seatId){
		if(seatId==null) {
			List<SeatBooking> seatsBookings = seatBookingService.getAllSeats();
			return new ResponseEntity<List<SeatBooking>>(seatsBookings,HttpStatus.OK);
		}
		Optional<SeatBooking> seatBookings = seatBookingService.getBySeatNo(seatId);
		return new ResponseEntity<Optional<SeatBooking>>(seatBookings,HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/booking/modifyBooking")
	public ResponseEntity<?> modify(@RequestBody SeatBooking seatBooking, @RequestParam (required = false) String seatId){
		Optional<SeatBooking> seat = seatBookingService.getBySeatNo(seatId);
		if(seat.isPresent()) {
			seatBooking.setModifiedDate(new Date());
			seatBookingService.modify(seatBooking);
			return new ResponseEntity<>("booking modified", HttpStatus.OK);
		}
		return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/booking/cancleBooking")
	public ResponseEntity<?> delete(@RequestParam String seatId){
		try {
			seatBookingService.delete(seatId);
			return new ResponseEntity<>("Booking Deleted",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> ("No SeatId found", HttpStatus.NOT_FOUND);
		}
	}
}

