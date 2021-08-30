package com.uns.ac.rs.ues.Email.Client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uns.ac.rs.ues.Email.Client.dto.ContactDTO;
import com.uns.ac.rs.ues.Email.Client.model.Contact;
import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.service.ContactService;
import com.uns.ac.rs.ues.Email.Client.service.UserService;

@RestController
@RequestMapping(value="api/contacts")
public class ContactController {
	
	@Autowired 
	ContactService contactService; 
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/{username}")
	public ResponseEntity<List<ContactDTO>> getContacts(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		
		List<Contact> contacts = contactService.findByUser(user); 
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>(); 
		
		for(Contact c : contacts ) {
			contactsDTO.add(new ContactDTO(c));
		}
		
		if(contactsDTO.size() != 0) {
			return new ResponseEntity<List<ContactDTO>>(contactsDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<ContactDTO>>(HttpStatus.NOT_FOUND); 
		
	}
	
	@GetMapping(value="/{username}/{id}")
	public ResponseEntity<ContactDTO> getContact(@PathVariable("username") String username, @PathVariable("id") Long id) {
		
		Contact contact = contactService.findById(id);
		if (contact == null) {
			return new ResponseEntity<ContactDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
	
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ContactDTO> saveContact(@RequestBody ContactDTO contactDTO) {
		User user = userService.findByUsername(contactDTO.getUser()); 
		
		Contact contact = new Contact(); 
		contact.setActive(true);
		contact.setDisplayname(contactDTO.getDisplayname());
		contact.setEmail(contactDTO.getEmail());
		contact.setFirstname(contactDTO.getFirstname());
		contact.setLastname(contactDTO.getLastname());
		contact.setNote(contactDTO.getNote());
		contact.setUser(user);
		contact = contactService.save(contact); 
		
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}", consumes = "application/json")
	public ResponseEntity<ContactDTO> updateContact(@RequestBody ContactDTO contactDTO, @PathVariable("id") Long id) {
		Contact contact = contactService.findById(id);
		if (contact == null) {
			return new ResponseEntity<ContactDTO>(HttpStatus.BAD_REQUEST);
		}
		
		contact = contactService.save(contact); 
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
		Contact contact = contactService.findById(id); 
		
		if(contact != null) {
			contact.setActive(false);
			contactService.save(contact); 
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
