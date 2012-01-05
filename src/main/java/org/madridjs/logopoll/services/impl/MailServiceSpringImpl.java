package org.madridjs.logopoll.services.impl;

import javax.inject.Inject;

import org.madridjs.logopoll.services.MailService;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailServiceSpringImpl implements MailService{
	
	private MailSender mailSender;
	private MailMessage mailMessage;
	
	@Inject
	public MailServiceSpringImpl(MailSender mailSender, MailMessage mailMessage) {
		this.mailSender = mailSender;
		this.mailMessage = mailMessage;
	}
	
	

	public void send(String from, String to, String subject, String body) {
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		
		mailSender.send((SimpleMailMessage)mailMessage);
		
	}

	
}
