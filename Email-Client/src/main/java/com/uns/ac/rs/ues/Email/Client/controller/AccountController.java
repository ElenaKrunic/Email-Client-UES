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
import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.service.AccountService;
import com.uns.ac.rs.ues.Email.Client.service.UserService;

@RestController
@RequestMapping(value="/api")
public class AccountController {

	@Autowired
	private AccountService accountService; 
	
	@Autowired
	private UserService userService; 
	
	@GetMapping
	@RequestMapping(value="/accounts/{username}")
	public ResponseEntity<List<AccountDTO>> getAccount(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		
		List<Account> accounts = accountService.findByUser(user);
		List<AccountDTO> accountsDTO = new ArrayList<AccountDTO>();
		
		for(Account a: accounts) {
			accountsDTO.add(new AccountDTO(a));
		}
		
		if(accountsDTO.size() != 0) {
			return new ResponseEntity<List<AccountDTO>>(accountsDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<AccountDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO, @PathVariable("username") String username, UriComponentsBuilder builder) {
		Account account = accountService.findByUsername(accountDTO.getUsername());
		User user = userService.findByUsername(username);
		
		if(account != null && account.getUsername().equals(accountDTO.getUsername()) && account.getSmtpAddress().equals(accountDTO.getSmtpAddress())) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		account = accountService.save(accountDTO,user);
		
		URI uri = builder.replacePath("/accounts/{id}").buildAndExpand(account.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(consumes="application/json")
	public ResponseEntity<?> changeState(@RequestBody AccountDTO accountDTO) {
		boolean changed = accountService.changeState(accountDTO);
		if(changed) {
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteMessageFromAccount(@PathVariable("id") Long id) {
		boolean account = accountService.delete(id);
		if(account) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
