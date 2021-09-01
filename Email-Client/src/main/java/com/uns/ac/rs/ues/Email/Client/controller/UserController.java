package com.uns.ac.rs.ues.Email.Client.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.uns.ac.rs.ues.Email.Client.dto.UserDTO;
import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.service.UserService;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

	@Autowired
	UserService userService; 
	
	static Long korisnikID; 
	/*
	@PostMapping(consumes = "application/json", value= "/login")
	public ResponseEntity<UserDTO> login (@RequestBody UserDTO userDTO) {
		User user = userService.findByUsername(userDTO.getUsername()); 
		
		System.out.println("User ")
		
		if(user.getPassword().equals(userDTO.getPassword())) {
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		}
		
		return ResponseEntity.notFound().build();
	}
	*/
	
	@PostMapping("/login")
	public ResponseEntity<?> ulogujKorisnika(@RequestBody UserDTO userDTO) {
		
		User existsUser = userService.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
		
		System.out.println("Korisnik koji se loguje na sistem ------------------------------>\n" + existsUser.getUsername() + " " + existsUser.getPassword());
		korisnikID = existsUser.getId();
		return new ResponseEntity<UserDTO>(new UserDTO(existsUser),HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
		User user = new User(); 
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		
		userService.save(userDTO); 
		
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED); 
	}	
	
	
	
	@PostMapping(consumes = "application/json", value = "/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO, UriComponentsBuilder builder) {
		User user = userService.findByUsername(userDTO.getUsername()); 
		
		if(user != null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		user = userService.save(userDTO);
		URI uri = builder.replacePath("/users/{id}").buildAndExpand(user.getId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	@RequestMapping(value = "/{username}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username){
		User user = userService.findByUsername(username); 
		if(user != null) {
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		}
		
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "/{username}", consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("username") String username) {
		User user = userService.findByUsername(username); 
		if(user !=null) {
			user.setFirstname(userDTO.getFirstname());
			user.setLastname(userDTO.getLastname());
			user.setPassword(userDTO.getPassword());
			
			user = userService.save(userDTO);
			
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		}
		
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
	
	
}
