package com.uns.ac.rs.ues.Email.Client.model;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyMessage {

	@Id                                 
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="_id", unique=true, nullable=false) 
	private long id;
	
	@Column(name="_from", unique=false, nullable=false)
	private String from;
	
	@Column(name="_to", unique=false, nullable=true)
	private String toReciver;
	
	@Column(name="_cc", unique=false, nullable=true)
	private String ccReciver;
	
	@Column(name="_bcc", unique=false, nullable=true)
	private String bccReciver;
	
	@Column(name="date_time", unique=false, nullable=false)
	private GregorianCalendar dateTime;
	
	@Column(name="_subject", unique=false, nullable=true)
	private String subject;
	
	@Column(name="content", unique=false, nullable=true, length = 10000)
	private String content;
	
	@Column(name="unread", unique=false, nullable=false)
	private boolean unread=true;
	
	@Column(name="active", unique = false, nullable = false)
	private boolean active;
	
	@Column(name="attachment_location", unique = false, nullable = false)
	private String attachment_location;
	
	@ManyToOne
	@JoinColumn(name="account_id", referencedColumnName="account_id", nullable=false)
	private Account account;


}
