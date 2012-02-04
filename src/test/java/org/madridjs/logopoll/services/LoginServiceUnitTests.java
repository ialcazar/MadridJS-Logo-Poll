package org.madridjs.logopoll.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.madridjs.logopoll.converters.UserConverter;
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
		UserRest userRest = new UserRest(email);
		UserRest newUserRest = new UserRest("1",email);

		UserDto  userDto  = new UserDto(email);
		UserDto  newUserDto  = new UserDto(1l,email);


		UserRepository usersDao = mock(UserRepository.class);
		UserValidator userValidator = mock(UserValidator.class);
		UserConverter userConverter = mock(UserConverter.class);
		
		
		when(userConverter.toDto(userRest)).thenReturn(userDto);
		when(usersDao.save(userDto)).thenReturn(newUserDto);
		when(userConverter.toRest(userDto)).thenReturn(newUserRest);
		
		
		LoginService loginService = new LoginServiceImpl(userValidator,userConverter,usersDao);
		
		
		newUserRest = loginService.login(userRest);
		
		
		verify(userValidator).validate(userRest);
		verify(usersDao).findByEmail(userRest.getEmail());
		verify(userConverter).toDto(userRest);
		verify(usersDao).save(userDto);
		verify(userConverter).toRest(newUserDto);
		
	}
	
	@Test
	public void login_a_user_when_mail_is_incorrect() {
		String email = "israel@gmail.com";
		UserRest userRest = new UserRest(email,email);
		UserConverter userConverter = mock(UserConverter.class);
		UserValidator userValidator = mock(UserValidator.class);
		UserRepository usersDao = mock(UserRepository.class);
		
		doThrow(new EmailException("Email is not valid")).when(userValidator).validate(userRest);
		
		
		LoginService loginService = new LoginServiceImpl(userValidator,userConverter,usersDao);
		
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
		UserDto userDto = new UserDto(email);
		usersDto.add(userDto);
		
		UserConverter userConverter = mock(UserConverter.class);
		UserValidator userValidator = mock(UserValidator.class);
		UserRepository usersDao = mock(UserRepository.class);
		
		when(usersDao.findByEmail(userRest.getEmail())).thenReturn(usersDto);
		
		LoginService loginService = new LoginServiceImpl(userValidator,userConverter,usersDao);
		
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
