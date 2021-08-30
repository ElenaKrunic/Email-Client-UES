package com.uns.ac.rs.ues.Email.Client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.User;

public interface AccountRepository extends JpaRepository<Account, Long>{

	List<Account> findByUser(User user);

	Account findByUsername(String username);

}
