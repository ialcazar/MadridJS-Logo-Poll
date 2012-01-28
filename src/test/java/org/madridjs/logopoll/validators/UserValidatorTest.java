package org.madridjs.logopoll.validators;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import org.madridjs.logopoll.exceptions.EmailException;
import org.madridjs.logopoll.rest.UserRest;

public class UserValidatorTest {
	private UserValidator userValidator;
	@Test
	public void validate_an_email_correct(){
		String[] emails = {"israelalcazar@gmail.com","a.b.c@delicios.de","israel.alcazar@gmail.com"};
		UserRest userRest = mock(UserRest.class);
		userValidator = new UserValidatorImpl();
		
		
		for(String email:emails){
			when(userRest.getEmail()).thenReturn(email);
			
			userValidator.validate(userRest);
		
		}
		verify(userRest,times(emails.length)).getEmail();

	}
	
	
	@Test
	public void validate_an_email_incorrect(){
		
		String[] emails = {"israelalcazargmail.com","a.b.c.@delicios.de","israel.alcazar@gmail"};

		
		UserRest userRest = mock(UserRest.class);
		userValidator = new UserValidatorImpl();
		for(String email:emails){
			when(userRest.getEmail()).thenReturn(email);
		
		
			try{
				userValidator.validate(userRest);
				fail("Expecting EmailInvalidateException");
			}catch(EmailException e){
				assertEquals("Mail ["+email+"] is not correct",e.getMessage());
			}
		}
		verify(userRest,times(emails.length)).getEmail();
		
	}

}
