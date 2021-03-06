/***
package com.uns.ac.rs.ues.Email.Client.mail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.projekat.demo.dto.ContactDTO;
import com.projekat.demo.dto.EmailDTO;
import com.projekat.demo.dto.MMessageDTO;
import com.projekat.demo.entity.Account;
import com.projekat.demo.entity.Attachment;
import com.projekat.demo.entity.Folder;
import com.projekat.demo.entity.MMessage;
import com.projekat.demo.entity.Rule;
import com.projekat.demo.entity.Tag;
import com.projekat.demo.service.FolderService;
import com.projekat.demo.service.MessageService;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.MailSSLSocketFactory;
import com.uns.ac.rs.ues.Email.Client.model.MyMessage;

import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * Izvori za javaMail Api : 
 *  https://www.javatpoint.com/java-mail-api-tutorial
 * 	https://www.codejava.net/* 
 *  https://www.tutorialspoint.com/javamail_api/index.htm
 * @author KrunicElena 
 *

@Component
public class MailAPI {
	
	
	/*
	private final String TMP_PATH = Objects.requireNonNull(getClass()
            .getClassLoader()
            .getResource(""))
            .getPath().substring(1)
            + "tmp" + File.separator;
	
	@Autowired
	private FolderService folderService;
	
	/**
	 * 
	 * @param account povezuje nalog i korisnika 
	 * @return
	 
	public boolean connectAccountToUser(com.uns.ac.rs.ues.Email.Client.model.Account account) {
		
		String protocol = ""; 
		
		if(account.getInServerType() == 0) {
			protocol = "imap"; 
		} else if(account.getInServerType() == 1) {
			protocol = "pop3"; 
		}
		
	try {
	        Properties properties = new Properties();
	    	properties.put("mail.store.protocol", protocol);
	        properties.put("mail.imap.host", account.getInServerAddress());
	        properties.put("mail.imap.port", account.getInServerPort());
	        properties.put("mail.imap.starttls.enable", "true");
	    	Session session = Session.getDefaultInstance(properties);
	    	Store store = session.getStore(protocol + "s");

	        store.connect(account.getInServerAddress(), account.getUsername(), account.getPassword());
	        return true;
        
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for protocol: " + protocol);
			ex.printStackTrace();
			return false;
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
			return false;
		}
	}
	/*
	public boolean sendMessage(MMessage message) {
        String EMAIL_TO = message.getTo();
        String EMAIL_CC = message.getCc();
        String EMAIL_BCC = message.getBcc();
        String EMAIL_FROM = message.getFrom();
 
        Account account = message.getAccount();
        String username = account.getUsername();
        String password = account.getPassword();
        
        // Parametri za mail server
        Properties properties = new Properties();
        properties.put("mail.smtp.socketFactory.port", account.getSmtpPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.host", account.getSmtpAddress());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", account.getSmtpPort());
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "true");
 
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lelekrunic1@gmail.com", "pereCvetka!8");
            }
        });
 
        try {
            MimeMessage APImessage = new MimeMessage(session);
 
            APImessage.setFrom(new InternetAddress(EMAIL_FROM));
            APImessage.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
            APImessage.setRecipients(javax.mail.Message.RecipientType.CC, InternetAddress.parse(EMAIL_CC));
            APImessage.setRecipients(javax.mail.Message.RecipientType.BCC, InternetAddress.parse(EMAIL_BCC));
            APImessage.setSubject(message.getSubject());
            
            // Postavljanje sadrzaja poruke i attachmenta
            Multipart multipart = new MimeMultipart();

            // Postavljanje teksta
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(message.getContent(), "text/plain");
            multipart.addBodyPart(textPart);

            // Postavljanje attachmenta
            for (Attachment attachment : message.getAttachments()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                
                attachmentPart.setContent(Base64.decodeBase64(attachment.getData()), attachment.getMimeType());
                attachmentPart.setFileName(attachment.getName());
                
                multipart.addBodyPart(attachmentPart);
            }

            APImessage.setContent(multipart);

            Transport.send(APImessage);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
	}
	
	public List<MyMessage> loadMessages(com.uns.ac.rs.ues.Email.Client.model.Account account) {
		
		ArrayList<MyMessage> messages = new ArrayList<MyMessage>();
	
		Store store = null;
		javax.mail.Folder APIinbox = null;
		
        try {
            Properties properties = new Properties();
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
  		  	sf.setTrustAllHosts(true);
  		  	properties.put("mail.imaps.ssl.trust", "*");
  		  	properties.put("mail.imaps.ssl.socketFactory", sf);
  		  	properties.setProperty("mail.store.protocol", "imaps");
		  	properties.put("mail.imap.host", account.getInServerAddress());
		  	properties.put("mail.imap.port", account.getInServerPort());
		  	properties.put("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		  	properties.setProperty("mail.imap.socketFactory.fallback", "false");
		  	properties.setProperty("mail.imap.socketFactory.port",
		  	String.valueOf(account.getInServerPort()));
            
            Session session = Session.getDefaultInstance(properties, null);

            store = session.getStore("imaps");
            store.connect("imap.googlemail.com", 993, account.getUsername(), account.getPassword());
            
            APIinbox = store.getFolder("inbox");
            APIinbox.open(javax.mail.Folder.READ_ONLY);
            
            //Folder inbox = folderService.findInbox(account);
            
            javax.mail.Message[] APImessages = APIinbox.getMessages();
 
            for (javax.mail.Message APImessage : APImessages) {
            	if (account.getLastSyncTime() == null || APImessage.getSentDate().after(account.getLastSyncTime())) {
		            MMessage message = new MMessage();
		            
		            message.setFrom(APImessage.getFrom()[0].toString());
		
		            Address[] APIto = APImessage.getRecipients(RecipientType.TO);
		            if (APIto != null) {
			            List<EmailDTO> to = new ArrayList<EmailDTO>();
			            for (Address address : APIto) {
			            	to.add(new EmailDTO(address.toString()));
			            }
			            message.setTo(MMessageDTO.recipientsToString(to));
		            } else {
		            	message.setTo(null);
		            }
		            
		            Address[] APIcc = APImessage.getRecipients(RecipientType.CC);
		            if (APIcc != null) {
			            List<EmailDTO> cc = new ArrayList<EmailDTO>();
			            for (Address address : APIcc) {
			            	cc.add(new EmailDTO(address.toString()));
			            }
			            message.setCc(MMessageDTO.recipientsToString(cc));
		            } else {
		            	message.setCc(null);
		            }
		            
		            Address[] APIbcc = APImessage.getRecipients(RecipientType.BCC);
		            if (APIbcc != null) {
			            List<EmailDTO> bcc = new ArrayList<EmailDTO>();
			            for (Address address : APIbcc) {
			            	bcc.add(new EmailDTO(address.toString()));
			            }
			            message.setBcc(MMessageDTO.recipientsToString(bcc));
		            } else {
		            	message.setBcc(null);
		            }
		            
		            Date timestamp = APImessage.getSentDate();
		            message.setDateTime(new Timestamp(timestamp.getTime()));
		            message.setSubject(APImessage.getSubject());
		            //setMessageContent(APImessage, message);
		            //message.setTags(new Set<Tag>());
		           // message.setTags(new Set<Tag>());
		            //message.setFolder(inbox);
		            message.setAccount(account);
		            message.setUnread(true);
		            ArrayList<Attachment> attachments = new ArrayList<Attachment>();
		            
		            MimeMultipart multipart = (MimeMultipart) APImessage.getContent();
		            
		            ArrayList<BodyPart> parts = extractParts(multipart);

		            for (int i = 0; i < parts.size(); i++) {
		                MimeBodyPart bodyPart = (MimeBodyPart) parts.get(i);

		                byte[] bytes;
		                
		                if (bodyPart.isMimeType("text/plain")) {
		                	message.setContent((String) bodyPart.getContent());
		                	continue;
		                } else if (bodyPart.isMimeType("image/*")) {
		                	BASE64DecoderStream base64DecoderStream = (BASE64DecoderStream) bodyPart.getContent();
		                    bytes = IOUtils.toByteArray(base64DecoderStream);
		                } else {
		                	InputStream is = bodyPart.getInputStream();
			                bytes = IOUtils.toByteArray(is);		                	
		                }
		                		                
		                Attachment attachment = new Attachment();
		                attachment.setName(bodyPart.getFileName());
		                attachment.setData(Base64.encodeBase64String(bytes));
		                
		                attachment.setMimeType("image/*");
		                attachment.setMessage(message);
		                
		                attachments.add(attachment);
		            }
		            
		            message.setAttachments(attachments);
		            
		            messages.add(message);
            	}
	        }
        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
        	try {
        		APIinbox.close();
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	}
        	
        	try {
        		store.close();
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	}
        }
        
        return messages;
	}
	
	


	public boolean sendMessage(MyMessage message)  {
		JavaMailSenderImpl mailSender = getJavaMailSender(message.getAccount());
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage,true);
			
			helper.setFrom(message.getFrom());
			helper.setSubject(message.getSubject());
			helper.setText(message.getContent());
			helper.setCc(message.getCcReciver());
			helper.setTo(message.getToReciver());
			helper.setBcc(message.getBccReciver());
			
			mailSender.send(mimeMessage);
			
			return true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private static JavaMailSenderImpl getJavaMailSender(com.uns.ac.rs.ues.Email.Client.model.Account account) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(account.getSmtpAddress());
		mailSender.setPort(account.getSmtpPort());
		
		mailSender.setUsername(account.getUsername());
		mailSender.setPassword(account.getPassword());
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		return mailSender;
	}
	
	// Pomocna metoda za izvlacenje attachmenta iz API-jeve poruke
		private ArrayList<BodyPart> extractParts(MimeMultipart multipart) {
			ArrayList<BodyPart> parts = new ArrayList<BodyPart>();
			
			try {
				for (int i = 0; i < multipart.getCount(); i++) {
		            MimeBodyPart bodyPart = (MimeBodyPart) multipart.getBodyPart(i);
		            if (bodyPart.isMimeType("text/plain")) {
		            	parts.add(bodyPart);
		            } else if (bodyPart.isMimeType("image/*")) {
		            	parts.add(bodyPart);
		            } else if (bodyPart.isMimeType("multipart/*")) {
		            	for (BodyPart part : extractParts((MimeMultipart)bodyPart.getContent())) {
		            		parts.add(part);
		            	}
		            }
		        }
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return parts;
		}
}

***/
