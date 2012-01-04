package org.madridjs.logopoll.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class GeneralErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 7208461975325498518L;

	public GeneralErrorException() {
		
	}

	public GeneralErrorException(String arg0) {
		super(arg0);
		
	}

	public GeneralErrorException(Throwable arg0) {
		super(arg0);
		
	}

	public GeneralErrorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
