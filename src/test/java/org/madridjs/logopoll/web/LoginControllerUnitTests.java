package org.madridjs.logopoll.web;


import static org.mockito.Mockito.*;

import org.junit.Test;
import org.madridjs.logopoll.services.LoginService;

public class LoginControllerUnitTests {

	@Test
	public void given_an_email_when_login_then_result_ok() {
		String email = "israelalcazar@gmail.com";
		LoginService 	loginService 	= mock(LoginService.class);
		LoginController loginController = new LoginController(loginService);
		
		
		loginController.login(email);
		
		verify(loginService).login(email);
	}

}
