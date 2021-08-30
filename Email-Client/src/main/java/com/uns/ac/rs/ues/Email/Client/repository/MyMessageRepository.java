package com.uns.ac.rs.ues.Email.Client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uns.ac.rs.ues.Email.Client.model.MyMessage;

public interface MyMessageRepository extends JpaRepository<MyMessage, Long> {

}
