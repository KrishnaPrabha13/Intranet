package com.srm.core.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.srm.core.model.Seat;
import com.srm.core.service.SeatService;

@RestController
public class SeatController {

	@Autowired
	private SeatService seatService;
	
	@PostMapping("/seats/postSeat")
	public ResponseEntity<?> create(@RequestBody Seat seat,@RequestParam Date fromDate){
	try {
//		String pattern = "DD/MM/YYYY";
//		DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
//		LocalDate now = LocalDate.now();
//		System.out.println(now);
//		String date = now.format(df);
//		System.out.println(date);
		
//		String pattern = "DD/MM/YYYY  HH:MM:SS";
		
//		SimpleDateFormat df = new SimpleDateFormat("DD/MM/YYYY");
//	
//		Date date=df.parse(df.format(new Date()));
//		Date todayDate = Calendar.getInstance().getTime();
//		String date = df.format(todayDate);
//		seat.setFromDate(date);
		
		
		LocalDate date=LocalDate.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-mm-yyyy");
//		
//		seat.setFromDate(date);
		System.out.println(date);
		
		Date d=new Date();
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		System.out.println(localDate);
		seat.setFromDate(d);
		seatService.create(seat);
		return new ResponseEntity<>("seat booked and  seat no is: " + " "+ seat.getSeatNo(), HttpStatus.CREATED);
		} catch ( Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@GetMapping("/seats/getSeat")
//	public ResponseEntity<?> getSeat(@RequestParam String seatNo){
//		Optional<Seat> seat = seatService.getBySeatNo(seatNo);
//		return new ResponseEntity<Optional<Seat>>(seat,HttpStatus.OK);
//	}
	@GetMapping("/seats/getSeats")
	public ResponseEntity<?> getAllSeats(@RequestParam(required = false) String seatNo, @RequestParam(required = false)String status, @RequestParam(required = false)String dept,@RequestParam(required = false)Date date){
		System.out.println(status+dept+date);
		if(dept==null) {
			List<Seat> seats = seatService.getAllSeats();
			return new ResponseEntity<List<Seat>>(seats,HttpStatus.OK);
		}
		else if(date==null) {
		List<Seat> seat = seatService.getAllByDept(dept);
		return new ResponseEntity<List<Seat>>(seat,HttpStatus.OK);
		}else {
		List<Seat> seat1 = seatService.getSeats(dept,date);
		return new ResponseEntity<List<Seat>>(seat1,HttpStatus.OK);
		}
	
	}
}
