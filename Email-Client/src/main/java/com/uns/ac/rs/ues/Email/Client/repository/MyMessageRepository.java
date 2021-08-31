package com.uns.ac.rs.ues.Email.Client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uns.ac.rs.ues.Email.Client.model.MyMessage;
import com.uns.ac.rs.ues.Email.Client.model.User;

public interface MyMessageRepository extends JpaRepository<MyMessage, Long> {

	List<MyMessage> findByUser(User user);

	MyMessage findByFrom(String from);

}
