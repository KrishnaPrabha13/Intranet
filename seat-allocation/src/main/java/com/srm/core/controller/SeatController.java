package com.srm.core.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.srm.core.model.Seat;
import com.srm.core.service.SeatService;
import com.srm.core.service.SeqService;

@RestController
public class SeatController {


	private static final String SEQUENCE_NAME = "total seat";

	@Autowired
	private SeatService seatService;	
	
	@Autowired
	private SeqService seqService;
	
	@PostMapping("/seats/postseat")
	public ResponseEntity<?> create(@RequestBody Seat seat){
	try {
		seat.setSeatId(seqService.getSqeNumber(SEQUENCE_NAME));
		//seat.setSeatId(seqService.getSqeNumber(SEQUENCE_NAME));
		seatService.create(seat);
		return new ResponseEntity<>("Seat created", HttpStatus.CREATED);
		} catch ( Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/seats/getseat")
	public ResponseEntity<?> getAllBookings(@RequestParam(required = false) String seatNo, @RequestParam(required = false) String dept, @RequestParam(required = false) String fromDate){
		if(dept==null & fromDate==null) {
			List<Seat> seats = seatService.getAllSeat();
			System.out.println("All Seat");
			return new ResponseEntity<List<Seat>>(seats,HttpStatus.OK);
		}
		else if(dept!=null & fromDate==null) {
			List<Seat> seats = seatService.getAllSeatByDept(dept);
			System.out.println(dept);
			return new ResponseEntity<List<Seat>>(seats,HttpStatus.OK);
		}
		else if(fromDate!=null & dept!=null) {
			List<Seat> seats = seatService.findAllByFromDate(fromDate,dept);
			System.out.println(dept + " "+ fromDate);
			System.out.println(seats);
			return new ResponseEntity<List<Seat>>(seats,HttpStatus.OK);
		}
		List<Seat> seats = seatService.getAllSeatByFromDate(fromDate);
		System.out.println(fromDate);
		return new ResponseEntity<List<Seat>>(seats,HttpStatus.OK);

	}
}
