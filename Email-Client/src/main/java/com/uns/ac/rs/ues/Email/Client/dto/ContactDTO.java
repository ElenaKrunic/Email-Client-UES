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
public class ContactDTO {

	private long id; 
	
	private String firstname; 
	
	private String lastname; 
	
	private String displayname; 
	
	private String email; 
	
	private String note;
	
	private boolean active=true; 
	
	private String user;
}
