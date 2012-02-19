package org.madridjs.logopoll.services.impl;

import javax.inject.Inject;

import org.madridjs.logopoll.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailServiceSpringImpl implements MailService{
	private static final Logger logger = LoggerFactory.getLogger(MailServiceSpringImpl.class);
 
	
	private MailSender mailSender;
	private MailMessage mailMessage;
	
	@Inject
	public MailServiceSpringImpl(MailSender mailSender, MailMessage mailMessage) {
		this.mailSender = mailSender;
		this.mailMessage = mailMessage;
	}
	
	

	public void send(String from, String to, String subject, String body) {
		logger.info("Creating Message from:"+from+":to:"+to+":subject:"+subject+":body:"+body);
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		
		JavaMailSenderImpl jmsi = (JavaMailSenderImpl)mailSender;
		

		logger.info("Sending Message [host="+jmsi.getHost()+",port="+jmsi.getPort()+",username="+jmsi.getUsername()+",password="+jmsi.getPassword());
		mailSender.send((SimpleMailMessage)mailMessage);
		
	}

	
}
