package org.madridjs.logopoll.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.madridjs.logopoll.services.impl.MailServiceSpringImpl;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailServiceUnitTests {

	@Test
	public void send_text_email() {
		String from = "madridjavascript@gmail.com";
		String to   = "israelalcazar@gmail.com";
		String subject = "[madrid.js][logo poll] Testing";
		String body = "Mail Body";
		
		SimpleMailMessage mailMessage = mock(SimpleMailMessage.class);
		MailSender mailSender = mock(MailSender.class);
		
		MailService mailService = new MailServiceSpringImpl(mailSender,mailMessage);
		
		
		mailService.send(from,to,subject,body);
		
		
		verify(mailMessage).setFrom(from);
		verify(mailMessage).setTo(to);
		verify(mailMessage).setSubject(subject);
		verify(mailMessage).setText(body);
		verify(mailSender).send(mailMessage);
		
	}

}
