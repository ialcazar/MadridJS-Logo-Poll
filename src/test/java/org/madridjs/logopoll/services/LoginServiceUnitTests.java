package org.madridjs.logopoll.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.madridjs.logopoll.daos.UsersDao;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.exceptions.EmailException;

import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.impl.LoginServiceImpl;
import org.madridjs.logopoll.validators.UserValidator;


public class LoginServiceUnitTests {

	@Test
	public void login_a_user_when_mail_is_correct_and_doesnt_exist() {
		String email = "israel@gmail.com";
		UserRest userRest = new UserRest(email,email);
		UsersDao usersDao = mock(UsersDao.class);
		UserValidator userValidator = mock(UserValidator.class);
		
		
		LoginService loginService = new LoginServiceImpl(userValidator,usersDao);
		
		
		loginService.login(userRest);
		
		
		verify(userValidator).validate(userRest);
		verify(usersDao).findOne(userRest.getEmail());
		
	}
	
	@Test
	public void login_a_user_when_mail_is_incorrect() {
		String email = "israel@gmail.com";
		UserRest userRest = new UserRest(email,email);
		
		UserValidator userValidator = mock(UserValidator.class);
		UsersDao usersDao = mock(UsersDao.class);
		
		doThrow(new EmailException("Email is not valid")).when(userValidator).validate(userRest);
		
		
		LoginService loginService = new LoginServiceImpl(userValidator,usersDao);
		
		try{
			loginService.login(userRest);
			fail("Expecting EmailInvalidateException");
		}catch(EmailException e){}
		
		
		verify(userValidator).validate(userRest);
		
		
	}
	@Test
	public void login_a_user_when_mail_is_correct_and_exist() {
		
		String email = "israel@gmail.com";
		UserRest userRest = new UserRest(email,email);
		UserDto  userDto = new UserDto();
		
		UserValidator userValidator = mock(UserValidator.class);
		UsersDao usersDao = mock(UsersDao.class);
		
		when(usersDao.findOne(userRest.getEmail())).thenReturn(userDto);
		
		LoginService loginService = new LoginServiceImpl(userValidator,usersDao);
		
		try{
			loginService.login(userRest);
			fail("Expecting EmailExistsException");
		}catch(EmailException e){
			assertEquals("Email "+email+" exists",e.getMessage());
		}
		
		
		verify(userValidator).validate(userRest);
		verify(usersDao).findOne(userRest.getEmail());
		
		
		
	}


}
