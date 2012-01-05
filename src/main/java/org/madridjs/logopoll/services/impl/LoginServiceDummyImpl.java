package org.madridjs.logopoll.services.impl;


import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceDummyImpl implements LoginService{
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceDummyImpl.class);

	public void login(UserRest userRest) {
		logger.debug("Starting login with user("+userRest+")");
//		throw new RuntimeException("porque si");
	}

	

}
