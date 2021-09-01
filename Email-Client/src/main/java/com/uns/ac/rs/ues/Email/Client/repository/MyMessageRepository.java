package com.uns.ac.rs.ues.Email.Client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.MyMessage;
import com.uns.ac.rs.ues.Email.Client.model.User;

@Repository
public interface MyMessageRepository extends JpaRepository<MyMessage, Long> {

	MyMessage findByFrom(String from);

	List<MyMessage> findByAccount(Account account);

}
