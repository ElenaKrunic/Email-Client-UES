package com.uns.ac.rs.ues.Email.Client.model;

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
@Table(name="contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "contact_id", unique = true, nullable = false)
	private long id; 
	
	private String firstname; 
	
	private String lastname; 
	
	private String displayname; 
	
	private String email; 
	
	private String note;
	
	private boolean active=true; 
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user; 
}
