package org.madridjs.logopoll.services;



import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:spring/test-context.xml")  
public class MailServiceIntegrationTests {
	@Inject
	private MailService mailService;
	
	@Test
	public void send_text_email() {
		String from = "madridjavascript@gmail.com";
		String to   = "israelalcazar@gmail.com";
		String subject = "[madrid.js][logo poll] Testing";
		String body = "ESto es un mensaje de prueba";
		
		mailService.send(from,to,subject,body);
	
	}

}
