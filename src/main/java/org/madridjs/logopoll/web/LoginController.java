package org.madridjs.logopoll.web;

import javax.inject.Inject;

import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LoginController {
	
	private LoginService loginService;
	
	@Inject
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void login(String email) {
		try{
			loginService.login(email);
		
		}catch(Throwable e){
			throw new GeneralErrorException(e);
		}
	}

}
