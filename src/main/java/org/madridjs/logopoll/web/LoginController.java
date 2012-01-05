package org.madridjs.logopoll.web;

import javax.inject.Inject;

import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService;
	
	@Inject
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void login(@RequestBody UserRest user) {
		logger.info("Starting login process for user("+user+")");
		try{
			loginService.login(user);
		
		}catch(Throwable e){
			throw new GeneralErrorException(e);
		}
	}

}
