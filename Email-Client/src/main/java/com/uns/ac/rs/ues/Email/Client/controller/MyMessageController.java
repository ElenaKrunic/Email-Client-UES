package com.uns.ac.rs.ues.Email.Client.controller;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.uns.ac.rs.ues.Email.Client.dto.AccountDTO;
import com.uns.ac.rs.ues.Email.Client.dto.MyMessageDTO;
import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.MyMessage;
import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.service.AccountService;
import com.uns.ac.rs.ues.Email.Client.service.MyMessageService;
import com.uns.ac.rs.ues.Email.Client.service.UserService;

@RestController
@RequestMapping(value = "api/messages")
public class MyMessageController {
	
	@Autowired
	MyMessageService messageService; 
	
	@Autowired 
	AccountService accountService; 
	
	@Autowired
	UserService userService; 
	
	@GetMapping
	@RequestMapping(value="/{username}")
	public ResponseEntity<List<MyMessageDTO>> getMessage(@PathVariable("username") String username) {
		Account account = accountService.findByUsername(username);
		
		List<MyMessage> messages = messageService.findByAccount(account);
		List<MyMessageDTO> messagesDTO = new ArrayList<MyMessageDTO>();
		
		for(MyMessage m: messages) {
			messagesDTO.add(new MyMessageDTO(m));
		}
		
		if(messagesDTO.size() != 0) {
			return new ResponseEntity<List<MyMessageDTO>>(messagesDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<MyMessageDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<MyMessageDTO> saveMessage(@RequestBody MyMessageDTO messageDTO, @PathVariable("username") String username, UriComponentsBuilder builder) {
		MyMessage message = messageService.findBySender(messageDTO.getFrom());
		Account account = accountService.findByUsername(username);
		
		if(message != null ) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		message = messageService.save(messageDTO,account);
		
		URI uri = builder.replacePath("/messages/{id}").buildAndExpand(message.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(consumes="application/json")
	public ResponseEntity<?> changeState(@RequestBody MyMessageDTO messageDTO) {
		boolean changed = messageService.changeState(messageDTO);
		if(changed) {
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) {
		boolean message = messageService.delete(id);
		if(message) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	

}
