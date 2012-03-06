package org.madridjs.logopoll.exceptions;

public class UserNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7219825810479259442L;

	public UserNotFoundException(String arg0) {
		super(arg0);
		
	}

	public UserNotFoundException(Throwable arg0) {
		super(arg0);
		
	}

	public UserNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

}
