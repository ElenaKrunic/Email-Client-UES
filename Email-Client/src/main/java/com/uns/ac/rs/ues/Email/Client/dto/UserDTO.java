package com.uns.ac.rs.ues.Email.Client.dto;


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
}
