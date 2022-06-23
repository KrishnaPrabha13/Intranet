package com.srm.core.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.srm.core.exception.ResourceNotFoundException;
import com.srm.core.model.User;
import com.srm.core.service.UserService;


@RestController
public class UserController {
	@Autowired
	private UserService userservice;
	
	@PostMapping("/adduser")
	public ResponseEntity<?>create(@RequestBody User user){
		try {
			userservice.create(user);
			return new ResponseEntity<>("User Added",HttpStatus.CREATED);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/users")
	public ResponseEntity<?> getalluser(@RequestParam(required = false)Integer id){
		if(id==null) {
			System.out.println("");
			List<User> user= userservice.getalluser();
			return new ResponseEntity<List<User>>(user,HttpStatus.OK);
		}
		else if(id!=null) {
			Optional<User> user = userservice.getbyId(id);
			System.out.println(user.isPresent());
			if(user.isPresent()){
			Optional<User> users = userservice.getbyId(id);
			System.out.println(user);
			return new ResponseEntity<Optional<User>>(users,HttpStatus.OK);
			}
		}
		throw new ResourceNotFoundException("User id is not found");
	}
}
	
