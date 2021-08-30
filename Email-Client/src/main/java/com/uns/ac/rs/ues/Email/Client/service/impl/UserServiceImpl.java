package com.uns.ac.rs.ues.Email.Client.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.uns.ac.rs.ues.Email.Client.dto.UserDTO;
import com.uns.ac.rs.ues.Email.Client.model.Account;
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

	@Override
	public User save(UserDTO userDTO) {
		User user = new User();
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setAccounts(new ArrayList<Account>());
		return userRepository.save(user);
	}

}
