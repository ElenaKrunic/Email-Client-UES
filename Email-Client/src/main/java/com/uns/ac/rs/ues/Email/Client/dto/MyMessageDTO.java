package com.uns.ac.rs.ues.Email.Client.dto;

import java.util.GregorianCalendar;


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
public class MyMessageDTO {

	private long id;
	
	private String from;
	
	private String toReciver;
	
	private String ccReciver;
	
	private String bccReciver;
	
	private GregorianCalendar dateTime;
	
	private String subject;
	
	private String content;
	
	private boolean unread=true;
	
	private boolean active;
	
	//private String account;
}
