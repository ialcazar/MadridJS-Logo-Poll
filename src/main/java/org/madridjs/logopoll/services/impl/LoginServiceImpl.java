package org.madridjs.logopoll.services.impl;

import org.madridjs.logopoll.daos.UsersDao;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.exceptions.EmailException;

import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.LoginService;
import org.madridjs.logopoll.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	private UserValidator userValidator;
	private UsersDao	  usersDao;
	
	public LoginServiceImpl(UserValidator userValidator, UsersDao usersDao) {
		this.userValidator = userValidator;
		this.usersDao = usersDao;
	}

	

	public void login(UserRest userRest) throws EmailException{
		logger.info("Starting login");
		userValidator.validate(userRest);
		
		UserDto userDto = usersDao.findOne(userRest.getEmail()); 
		if(userDto != null)
			throw new EmailException("Email "+userRest.getEmail()+" exists");

	}

}
