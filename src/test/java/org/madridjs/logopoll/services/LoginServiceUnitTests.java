package org.madridjs.logopoll.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.madridjs.logopoll.daos.UserRepository;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.exceptions.EmailException;

import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.impl.LoginServiceImpl;
import org.madridjs.logopoll.validators.UserValidator;


public class LoginServiceUnitTests {

	@Test
	public void login_a_user_when_mail_is_correct_and_doesnt_exist() {
		String email = "israelis@gmail.com";
		UserRest userRest = new UserRest(email,email);
		UserRepository usersDao = mock(UserRepository.class);
		UserValidator userValidator = mock(UserValidator.class);
		
		
		LoginService loginService = new LoginServiceImpl(userValidator,usersDao);
		
		
		loginService.login(userRest);
		
		
		verify(userValidator).validate(userRest);
		verify(usersDao).findByEmail(userRest.getEmail());
		
	}
	
	@Test
	public void login_a_user_when_mail_is_incorrect() {
		String email = "israel@gmail.com";
		UserRest userRest = new UserRest(email,email);
		
		UserValidator userValidator = mock(UserValidator.class);
		UserRepository usersDao = mock(UserRepository.class);
		
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
		List<UserDto>  usersDto = new LinkedList<UserDto>();
		UserDto userDto = new UserDto(email,email,email);
		usersDto.add(userDto);
		
		UserValidator userValidator = mock(UserValidator.class);
		UserRepository usersDao = mock(UserRepository.class);
		
		when(usersDao.findByEmail(userRest.getEmail())).thenReturn(usersDto);
		
		LoginService loginService = new LoginServiceImpl(userValidator,usersDao);
		
		try{
			loginService.login(userRest);
			fail("Expecting EmailExistsException");
		}catch(EmailException e){
			assertEquals("Email "+email+" exists",e.getMessage());
		}
		
		
		verify(userValidator).validate(userRest);
		verify(usersDao).findByEmail(userRest.getEmail());
		
		
		
	}


}
