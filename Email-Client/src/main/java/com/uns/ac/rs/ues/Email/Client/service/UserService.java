package com.uns.ac.rs.ues.Email.Client.service;

import com.uns.ac.rs.ues.Email.Client.dto.UserDTO;
import com.uns.ac.rs.ues.Email.Client.model.User;

public interface UserService {

	User findByUsername(String username);

	User save(UserDTO userDTO);

}
