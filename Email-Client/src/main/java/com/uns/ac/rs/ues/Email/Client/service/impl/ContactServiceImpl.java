package com.uns.ac.rs.ues.Email.Client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uns.ac.rs.ues.Email.Client.model.Contact;
import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.repository.ContactRepository;
import com.uns.ac.rs.ues.Email.Client.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	ContactRepository contactRepository;

	@Override
	public List<Contact> findByUser(User user) {
		return contactRepository.findByUser(user);
	}

	@Override
	public Contact findById(Long id) {
		return contactRepository.getById(id);
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	} 



}
