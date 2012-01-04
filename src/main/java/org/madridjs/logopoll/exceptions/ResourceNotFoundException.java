package org.madridjs.logopoll.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 7208461975325498518L;

	public ResourceNotFoundException() {
		
	}

	public ResourceNotFoundException(String arg0) {
		super(arg0);
		
	}

	public ResourceNotFoundException(Throwable arg0) {
		super(arg0);
		
	}

	public ResourceNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
