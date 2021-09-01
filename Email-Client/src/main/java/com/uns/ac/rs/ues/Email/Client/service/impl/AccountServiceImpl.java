package com.uns.ac.rs.ues.Email.Client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uns.ac.rs.ues.Email.Client.dto.AccountDTO;
import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.repository.AccountRepository;
import com.uns.ac.rs.ues.Email.Client.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Account> findByUser(User user) {
		return accountRepository.findByUser(user);
	}

	@Override
	public Account findByUsername(String username) {
		Account account = accountRepository.findByUsername(username);
		return account;
	}

	@Override
	public Account save(AccountDTO accountDTO, User user) {
		Account account = new Account(); 
		account.setActive(true);
		account.setDisplayName(accountDTO.getUsername()+"@"+accountDTO.getSmtpAddress());
		account.setInServerAddress("");
		account.setInServerPort(0);
		account.setInServerType((short) 0);
		account.setPassword(accountDTO.getPassword());
		account.setSmtpAddress(accountDTO.getSmtpAddress());
		account.setSmtpPort(0);
		account.setUsername(accountDTO.getUsername());
		account.setUser(user);
		return accountRepository.save(account);
	}

	@Override
	public boolean changeState(AccountDTO accountDTO) {
		boolean changedState = false; 
		Account existsAccount = accountRepository.findByUsername(accountDTO.getUsername()); 
		if(existsAccount != null) {
			changedState= true; 
			existsAccount.setActive(accountDTO.isActive());
			accountRepository.save(existsAccount);
		}
		
		return changedState;
	}

	@Override
	public boolean delete(Long id) {
		boolean deleted = false; 
		Account account = findById(id);
		accountRepository.delete(account);
		return deleted;
	}

	private Account findById(Long id) {
		return accountRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Account> findAllByUserId(Long korisnikID) {
		List<Account> accounts = accountRepository.findByUserId(korisnikID);
		return accounts;
	}

}
