package com.uns.ac.rs.ues.Email.Client.service;

import java.util.List;

import com.uns.ac.rs.ues.Email.Client.dto.MyMessageDTO;
import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.MyMessage;
import com.uns.ac.rs.ues.Email.Client.model.User;

public interface MyMessageService {

	List<MyMessage> findByUser(User user);

	MyMessage findBySender(String from);

	MyMessage save(MyMessageDTO messageDTO, Account account);

	boolean changeState(MyMessageDTO messageDTO);

	boolean delete(Long id);

}
