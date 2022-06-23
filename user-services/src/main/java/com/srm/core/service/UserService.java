package com.srm.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.core.exception.ResourceNotFoundException;
import com.srm.core.model.User;
import com.srm.core.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepository;
	
	public User create (User user) {
		return userrepository.save(user);
		
	}
	public User update (User user) {
		return userrepository.save(user);
		
	}
	public List<User> getalluser() {
		return userrepository.findAll();
	}
	
	public Optional <User> getbyId(int id) throws ResourceNotFoundException{
		return userrepository.findById(id);
	}

	
}
