package com.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User newUser(User user) {
		user.setDate(LocalDate.now());
		return userRepo.save(user);
	}
	
	

	
}
