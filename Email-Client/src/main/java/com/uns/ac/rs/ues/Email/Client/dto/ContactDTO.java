package com.uns.ac.rs.ues.Email.Client.dto;


import com.uns.ac.rs.ues.Email.Client.model.Contact;

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
	
	public ContactDTO(Contact c) {
		this(c.getId(), c.getDisplayname(), c.getEmail(), c.getFirstname(), c.getLastname(), c.getNote());
	}

	public ContactDTO(long id, String displayname, String email, String firstname, String lastname,
			String note) {
		this.id = id; 
		this.displayname = displayname; 
		this.email = email; 
		this.firstname = firstname;
		this.lastname = lastname; 
		this.note = note;
	}
}
