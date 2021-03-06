package com.uns.ac.rs.ues.Email.Client.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uns.ac.rs.ues.Email.Client.dto.AccountDTO;
import com.uns.ac.rs.ues.Email.Client.dto.MyMessageDTO;
import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.MyMessage;
import com.uns.ac.rs.ues.Email.Client.model.User;

public interface AccountService {

	List<Account> findByUser(User user);

	Account findByUsername(String username);

	Account save(AccountDTO accountDTO, User user);

	boolean changeState(AccountDTO accountDTO);

	boolean delete(Long id);

	List<Account> findAllByUserId(Long id);


}
