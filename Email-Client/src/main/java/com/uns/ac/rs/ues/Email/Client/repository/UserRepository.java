package com.uns.ac.rs.ues.Email.Client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uns.ac.rs.ues.Email.Client.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

}
