package org.madridjs.logopoll.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserRest extends RestEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4485253979091440102L;
	
	
	private List<String> email;
	
	
	public UserRest() {
		super();
		
	}

	public UserRest(String id, String email) {
		super(id);
		if(this.email == null)
			this.email = new ArrayList<String>();
		this.email.add(email);
	}

	

	public UserRest(String email) {
		this(null,email);
	}

	public String getEmail() {
		if(this.email != null && this.email.get(0) != null){
			return email.get(0);
		}
		return null;
	}
}
