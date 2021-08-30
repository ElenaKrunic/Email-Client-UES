package com.uns.ac.rs.ues.Email.Client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.repository.UserRepository;
import com.uns.ac.rs.ues.Email.Client.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
