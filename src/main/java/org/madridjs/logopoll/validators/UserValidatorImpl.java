package org.madridjs.logopoll.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.madridjs.logopoll.exceptions.EmailException;
import org.madridjs.logopoll.rest.UserRest;

public class UserValidatorImpl implements UserValidator {
	
	private Pattern pattern;
	 private Matcher matcher;

	  private static final String EMAIL_PATTERN = 
                 "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	  public UserValidatorImpl(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	  }


	public void validate(UserRest userRest) {
		String email = userRest.getEmail();
		
		 matcher = pattern.matcher(email);
		 if(!matcher.matches()){
			 throw new EmailException("Mail ["+email+"] is not correct");
		 }

	}

}
