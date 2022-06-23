package com.srm.core.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.srm.core.exception.AlreadyExistException;
import com.srm.core.exception.ResourceNotFoundException;
import com.srm.core.model.SeatBooking;
import com.srm.core.service.SeatBookingService;
import com.srm.core.service.SeqService;


@CrossOrigin(origins="http://localhost:3000/")
@RestController
public class SeatBookingController {
	
	
	private static final String SEQUENCE_NAME = "total booking";

	@Autowired
	private SeatBookingService seatBookingService;
	
	@Autowired
	private SeqService seqService;
	
	@PostMapping("/booking/postbooking")
	public ResponseEntity<?> create(@RequestBody SeatBooking seatBooking){
		Optional<SeatBooking> seat = seatBookingService.getBySeatNo(seatBooking.getSeatNo());
		if(!seat.isPresent() ) {
			seatBooking.setBookingId(seqService.getSqeNumber(SEQUENCE_NAME));
			Date createdate = new Date();
			seatBooking.setCreateDate(createdate);
			seatBookingService.create(seatBooking);
			return new ResponseEntity<>("Seat Booked and seat no is: "+ seatBooking.getSeatNo(), HttpStatus.OK);
		}
		throw new AlreadyExistException("Seat Already Booked : " +seatBooking.getSeatNo());
	}
	
//	@PostMapping("/booking/postbooking")
//	public ResponseEntity<?> request(@RequestParam Integer bookingId){
//		Optional<SeatBooking> seat = seatBookingService.getByBookingId(bookingId);
//		if(!seat.isPresent() ) {
//			seatBookingService.modify(null)
//			Date createdate = new Date();
//			seatBooking.setCreateDate(createdate);
//			seatBookingService.create(seatBooking);
//			return new ResponseEntity<>("Seat Booked and seat no is: "+ seatBooking.getSeatNo(), HttpStatus.OK);
//		}
//		throw new AlreadyExistException("Seat Already Booked : " +seatBooking.getSeatNo());
//	}
	
	
	@GetMapping("/booking/getbooking")
	public ResponseEntity<?> getAllSeats(@RequestParam(required = false) String seatNo){
		if(seatNo==null) {
			List<SeatBooking> seatsBookings = seatBookingService.getAllSeats();
			return new ResponseEntity<List<SeatBooking>>(seatsBookings,HttpStatus.OK);
		}
		else if (seatNo!=null) {
			Optional<SeatBooking> seat = seatBookingService.getBySeatNo(seatNo);
			System.out.println(seat.isPresent());
			if(seat.isPresent()){
			Optional<SeatBooking> seatBookings = seatBookingService.getBySeatNo(seatNo);
			System.out.println(seatBookings);
			return new ResponseEntity<Optional<SeatBooking>>(seatBookings,HttpStatus.OK);
			}
		}
		throw new ResourceNotFoundException("No Seat.No is found: "+ seatNo);
	}
	
	
	@GetMapping("/booking/getrequest")
	public ResponseEntity<?> getAllRequest(@RequestParam (required = false) String status, @RequestParam(required = false) String approver){
		if(status ==null & approver!=null) {
			List<SeatBooking> request = seatBookingService.getAllByApprover(approver);
			return new ResponseEntity<List<SeatBooking>>(request,HttpStatus.OK);
		}
		else if(status !=null & approver!=null) {
			List<SeatBooking> request = seatBookingService.getRequest(approver, status);
			return new ResponseEntity<List<SeatBooking>>(request,HttpStatus.OK);
		}
		throw new ResourceNotFoundException("No Approver found with name: "+ approver) ;
	}
	
	
	@GetMapping("/booking/getcount")
	public ResponseEntity<?> getCount(@RequestParam (required = false) String status, @RequestParam(required = false) String approver){
		if(status ==null & approver!=null) {
			List<SeatBooking> request = seatBookingService.getAllByApprover(approver);
			return new ResponseEntity<List<SeatBooking>>(request,HttpStatus.OK);
		}
		else if(status !=null & approver!=null) {
			List<SeatBooking> request = seatBookingService.getRequest(approver, status);
			return new ResponseEntity<List<SeatBooking>>(request,HttpStatus.OK);
		}
		throw new ResourceNotFoundException("No Approver found with name: "+ approver) ;
	}
	@PutMapping("/booking/modifybooking")
	public ResponseEntity<?> modify(@RequestBody SeatBooking seatBooking, @RequestParam (required = false) Integer bookingId){
		Optional<SeatBooking> seat = seatBookingService.getByBookingId(bookingId);
		if(seat.isPresent()) {
			SeatBooking s = seat.get();
			s.setSeatNo(seatBooking.getSeatNo()!=null?seatBooking.getSeatNo():s.getSeatNo());
			s.setCreateDate(seatBooking.getCreateDate()!=null?seatBooking.getCreateDate(): s.getCreateDate());
			s.setApprover(seatBooking.getApprover()!=null?seatBooking.getApprover(): s.getApprover());
			s.setUserId(seatBooking.getUserId()!=null? seatBooking.getUserId():s.getUserId());
			s.setStatus(seatBooking.getStatus()!=null?seatBooking.getStatus():s.getStatus());
			s.setModifiedDate(new Date());
			System.out.println(s);
			seatBookingService.modify(s);
			System.out.println(seatBooking);
			return new ResponseEntity<>("Booking modified with booking id: "+ bookingId, HttpStatus.OK);
		}
		throw new ResourceNotFoundException("BookingId" + " "+ bookingId +" " + "is Not Found");
	}
	

	
	@DeleteMapping("/booking/cancelbooking")
	public ResponseEntity<?> cancel(@RequestParam Integer bookingId){
		try {
			seatBookingService.cancelbooking(bookingId);
			return new ResponseEntity<>("Booking Cancel",HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException("No bookingid found");
		}
	}
}


