package org.madridjs.logopoll.web;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.madridjs.logopoll.exceptions.EmailExistsException;
import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.services.LoginService;


public class LogosControllerUnitTests {

	@Test
	public void given_an_email_when_login_then_result_ok() {
		String email = "israelalcazar@gmail.com";
		LoginService 	loginService 	= mock(LoginService.class);
		LoginController loginController = new LoginController(loginService);
		
		
		loginController.login(email);
		
		verify(loginService).login(email);
	}
	
	@Test
	public void given_an_email_when_login_then_email_exists() {
		String email = "israelalcazar@gmail.com";
		LoginService 	loginService 	= mock(LoginService.class);
		LoginController loginController = new LoginController(loginService);
		
		doThrow(new EmailExistsException()).when(loginService).login(email);  
		
		
		try{
			loginController.login(email);
			fail("Expected GeneralErrorException");
		}catch(GeneralErrorException e){
			
		}
		
		verify(loginService).login(email);
	}

}
