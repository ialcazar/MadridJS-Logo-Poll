package org.madridjs.logopoll.rest;

import java.io.Serializable;

public class UserRest extends RestEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4485253979091440102L;
	
	
	private String email;
	
	
	public UserRest() {
		super();
		
	}

	public UserRest(String id, String email) {
		super(id);
		
		this.email = email;
	}

	

	public UserRest(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
