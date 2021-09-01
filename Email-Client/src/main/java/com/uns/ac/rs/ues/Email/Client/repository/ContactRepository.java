package com.uns.ac.rs.ues.Email.Client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uns.ac.rs.ues.Email.Client.model.Contact;
import com.uns.ac.rs.ues.Email.Client.model.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

	List<Contact> findByUser(User user);

}
