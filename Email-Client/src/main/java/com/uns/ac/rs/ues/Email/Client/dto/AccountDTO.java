package com.uns.ac.rs.ues.Email.Client.dto;


import java.sql.Timestamp;

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
public class AccountDTO {

	private long id; 
	
	private String smtpAddress; 
	
	private int smtpPort; 
	
	private int inServerType; 
	
	private String inServerAddress; 
	
	private int inServerPort; 
	
	private String username; 
	
	private String password; 
	
	private String displayName; 
	
	private Timestamp lastSyncTime; 
	
	private boolean active=true; 
	
	private String user;
}
