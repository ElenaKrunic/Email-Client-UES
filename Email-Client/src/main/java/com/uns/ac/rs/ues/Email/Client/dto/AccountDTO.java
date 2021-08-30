package com.uns.ac.rs.ues.Email.Client.dto;


import java.sql.Timestamp;

import com.uns.ac.rs.ues.Email.Client.model.Account;

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
	
	public AccountDTO(long id, String smtpAddress, int smtp, int inServerType, String inServerAddress,
			int inServerPort, String username, String password, String displayName,boolean active) {
		super();
		this.id = id;
		this.smtpAddress = smtpAddress;
		this.smtpPort = smtp;
		this.inServerType = inServerType;
		this.inServerAddress = inServerAddress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.active=active;
	}
	
	public AccountDTO(Account account) {
		this(account.getId(),account.getSmtpAddress(),account.getSmtpPort(),account.getInServerType(),
				account.getInServerAddress(),account.getInServerPort(),account.getUsername(),
				account.getPassword(),account.getDisplayName(),account.isActive());
	}

	
}
