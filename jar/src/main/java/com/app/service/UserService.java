package com.app.service;

import java.util.List;

import com.app.entities.User;

public interface UserService {
	List<User> findAllUsers();
	User newUser(User user); 
}
