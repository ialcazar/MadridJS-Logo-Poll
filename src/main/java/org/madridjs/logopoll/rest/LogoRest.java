package org.madridjs.logopoll.rest;

import java.io.Serializable;

public class LogoRest extends RestEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7393924157484812382L;
	
	
	
	private String description;
	private String url;
	
	
	
	
	public LogoRest(String id, String description, String url) {
		super(id);
		
		this.description = description;
		this.url = url;
	}



	public String getDescription() {
		return description;
	}




	public String getUrl() {
		return url;
	}

}
