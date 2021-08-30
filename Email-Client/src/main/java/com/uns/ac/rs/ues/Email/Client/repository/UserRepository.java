package com.uns.ac.rs.ues.Email.Client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uns.ac.rs.ues.Email.Client.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
