package org.madridjs.logopoll.web;



import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.rest.UserRest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:spring/test-context.xml")  
public class LoginControllerIntegrationTests {
	@Inject
	private LoginController loginController;
	
	@Test
	public void given_an_email_when_login_then_email_not_exists() {
		UserRest userRest = new UserRest("israelito@gmail.com");
		
		UserRest newUserRest = loginController.login(userRest);
		
		assertNotNull(newUserRest);
		assertNotNull(newUserRest.getId());
		assertTrue(newUserRest.getId().equals("1"));
	
	}
	
	@Test
	public void given_an_email_when_login_then_email_exists() {
		UserRest userRest = new UserRest("israel1@gmail.com");
		
		try{
			loginController.login(userRest);
			fail("Expected GeneralErrorException");
		}catch(GeneralErrorException e){
			
		}
	
		
	}

}
