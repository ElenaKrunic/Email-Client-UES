package com.uns.ac.rs.ues.Email.Client.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "account_id", unique = true, nullable = false)
	private long id; 
	
	private String smtpAddress; 
	
	private int smtpPort; 
	
	private int inServerType; 
	
	private String inServerAddress; 
	
	private int inServerPort; 
	
	private String username; 
	
	private String password; 
	
	private String displayName; 
	
	//private Timestamp lastSyncTime; 
	
	private boolean active=true; 
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="account")
	private List<MyMessage> messages = new ArrayList<MyMessage>();
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user;
}
