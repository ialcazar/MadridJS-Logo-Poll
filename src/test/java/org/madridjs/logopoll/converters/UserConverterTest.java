package org.madridjs.logopoll.converters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.exceptions.EmailException;
import org.madridjs.logopoll.rest.UserRest;

public class UserConverterTest {
	private UserConverter userConverter;
	
	@Before
	public void setUp(){
		userConverter = new UserConverterImpl();
	}
	@Test
	public void convert_rest_null_to_dto_throws_exception(){
		try{
			userConverter.toDto(null);
			fail("Expected IllegalArgumentException");
		}catch(IllegalArgumentException e){}
	}
	@Test
	public void convert_rest_with_id_to_dto(){
		UserRest userRest = new UserRest("1","israel@gmail.com");
		UserDto userDtoExpected = new UserDto(1l,"israel@gmail.com");
		
		UserDto userDto = userConverter.toDto(userRest);
		
		assertEquals(userDtoExpected.getUserId(),userDto.getUserId(),0);
		assertEquals(userDtoExpected.getEmail(),userDto.getEmail());
		
	}
	
	@Test
	public void convert_rest_without_id_to_dto(){
		UserRest userRest = new UserRest("israel@gmail.com");
		UserDto userDtoExpected = new UserDto("israel@gmail.com");
		
		UserDto userDto = userConverter.toDto(userRest);
		
		assertNull(userDto.getUserId());
		assertEquals(userDtoExpected.getEmail(),userDto.getEmail());
		
	}
	
	@Test
	public void convert_dto_null_to_rest_throws_exception(){
		try{
			userConverter.toRest(null);
			fail("Expected IllegalArgumentException");
		}catch(IllegalArgumentException e){}
	}
	@Test
	public void convert_dto_to_rest(){
		UserRest userRestExpected= new UserRest("1","israel@gmail.com");
		UserDto userDto = new UserDto(1l,"israel@gmail.com");
		
		UserRest userRest = userConverter.toRest(userDto);
		
		assertTrue(userRestExpected.getId().equals(userRest.getId()));
		assertEquals(userRestExpected.getEmail(),userRest.getEmail());
		
	}

}
