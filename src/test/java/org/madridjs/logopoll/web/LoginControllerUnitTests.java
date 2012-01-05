package org.madridjs.logopoll.web;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.madridjs.logopoll.exceptions.EmailExistsException;
import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.LoginService;


public class LoginControllerUnitTests {

	@Test
	public void given_an_email_when_login_then_result_ok() {
		
		UserRest        userRest		= mock(UserRest.class);
		LoginService 	loginService 	= mock(LoginService.class);
		LoginController loginController = new LoginController(loginService);
		
		
		loginController.login(userRest);
		
		verify(loginService).login(userRest);
	}
	
	@Test
	public void given_an_email_when_login_then_email_exists() {
		
		
		UserRest        userRest		= mock(UserRest.class);
		LoginService 	loginService 	= mock(LoginService.class);
		LoginController loginController = new LoginController(loginService);
		
		doThrow(new EmailExistsException()).when(loginService).login(userRest);  
		
		
		try{
			loginController.login(userRest);
			fail("Expected GeneralErrorException");
		}catch(GeneralErrorException e){
			
		}
		
		verify(loginService).login(userRest);
	}

}
