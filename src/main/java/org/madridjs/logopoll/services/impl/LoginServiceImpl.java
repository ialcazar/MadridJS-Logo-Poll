package org.madridjs.logopoll.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.madridjs.logopoll.converters.UserConverter;
import org.madridjs.logopoll.daos.UserRepository;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.exceptions.EmailException;

import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.LoginService;
import org.madridjs.logopoll.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	private UserValidator userValidator;
	private UserRepository	  usersDao;
	private UserConverter	userConverter;
	
	@Inject
	public LoginServiceImpl(UserValidator userValidator, UserConverter userConverter, UserRepository usersDao) {
		this.userValidator = userValidator;
		this.userConverter = userConverter;
		this.usersDao = usersDao;
	}

	

	public UserRest login(UserRest userRest) throws EmailException{
		logger.info("Starting login");
		userValidator.validate(userRest);
		
		List<UserDto> usersDto = usersDao.findByEmail(userRest.getEmail()); 
		if(usersDto != null && usersDto.size()>0)
			throw new EmailException("Email "+userRest.getEmail()+" exists");
		
		UserDto userDto = userConverter.toDto(userRest);
		UserDto newUserDto = usersDao.save(userDto);
		
		UserRest newUserRest = userConverter.toRest(newUserDto);
		
		return newUserRest;

	}

}
