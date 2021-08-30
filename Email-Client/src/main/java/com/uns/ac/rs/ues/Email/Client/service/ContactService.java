package com.uns.ac.rs.ues.Email.Client.service;

import java.util.List;

import com.uns.ac.rs.ues.Email.Client.model.Contact;
import com.uns.ac.rs.ues.Email.Client.model.User;

public interface ContactService {

	List<Contact> findByUser(User user);

	Contact findById(Long id);

	Contact save(Contact contact);


}
