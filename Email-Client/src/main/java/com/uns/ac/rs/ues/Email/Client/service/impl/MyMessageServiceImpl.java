package com.uns.ac.rs.ues.Email.Client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uns.ac.rs.ues.Email.Client.dto.MyMessageDTO;
import com.uns.ac.rs.ues.Email.Client.model.Account;
import com.uns.ac.rs.ues.Email.Client.model.MyMessage;
import com.uns.ac.rs.ues.Email.Client.model.User;
import com.uns.ac.rs.ues.Email.Client.repository.MyMessageRepository;
import com.uns.ac.rs.ues.Email.Client.service.MyMessageService;

@Service
public class MyMessageServiceImpl implements MyMessageService{
	
	@Autowired
	MyMessageRepository messageRepository;

	
	@Override
	public MyMessage findBySender(String from) {
		MyMessage message = messageRepository.findByFrom(from);
		return message;
	}

	@Override
	public MyMessage save(MyMessageDTO messageDTO, Account account) {
		MyMessage message = new MyMessage();
		//message.setAccount(account);
		message.setActive(true);
		message.setAttachment_location("");
		message.setBccReciver("");
		message.setCcReciver("");
		message.setContent("");
		message.setDateTime(null);
		message.setFrom("");
		message.setSubject("");
		message.setToReciver("");
		message.setUnread(true);
		return messageRepository.save(message);
	}

	@Override
	public boolean changeState(MyMessageDTO messageDTO) {
		boolean changedState = false; 
		MyMessage existsMessage = findBySender(messageDTO.getFrom()); 
		if(existsMessage != null) {
			changedState= true; 
			existsMessage.setActive(messageDTO.isActive());
			messageRepository.save(existsMessage);
		}
		
		return changedState;
	}

	@Override
	public boolean delete(Long id) {
		boolean deleted = false; 
		MyMessage message = findById(id);
		messageRepository.delete(message);
		return deleted;
	}
	
	private MyMessage findById(Long id) {
		return messageRepository.findById(id).orElseThrow();
	}

	@Override
	public List<MyMessage> findByAccount(Account account) {
		return messageRepository.findByAccount(account);
	}

	@Override
	public void save(MyMessage message) {
		MyMessage m = null; 
		try {
			m = messageRepository.save(message); 
			//addNewMessage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void addNewMessage(MyMessage m) {
		// TODO Auto-generated method stub
		
	}

}
