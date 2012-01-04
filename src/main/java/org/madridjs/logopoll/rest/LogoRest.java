package org.madridjs.logopoll.rest;

import java.io.Serializable;

public class LogoRest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7393924157484812382L;
	
	
	private int    id;
	private String description;
	private String url;
	
	
	
	
	public LogoRest(int id, String description, String url) {
		super();
		this.id = id;
		this.description = description;
		this.url = url;
	}


	public int getId() {
		return id;
	}




	public String getDescription() {
		return description;
	}




	public String getUrl() {
		return url;
	}

}
