package com.uns.ac.rs.ues.Email.Client.dto;


import com.uns.ac.rs.ues.Email.Client.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private long id; 
	
	private String username; 
	
	private String password; 
	
	private String firstname; 
	
	private String lastname; 
	
	private boolean active=true; 
	
	public UserDTO(User user) {
		this(user.getFirstname(), user.getId(), user.getLastname(), user.getPassword(), user.getUsername());
	}

	public UserDTO(String firstname, long id, String lastname, String password, String username) {
		this.firstname = firstname;
		this.id = id; 
		this.lastname = lastname;
		this.password = password;
		this.username = username; 
	}
}
