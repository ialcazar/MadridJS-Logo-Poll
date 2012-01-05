package org.madridjs.logopoll.rest;

import java.io.Serializable;

public class VoteRest extends RestEntity implements Serializable{

	
	private static final long serialVersionUID = -108280258133453780L;
	
	private String description;
	private int count;
	private String url;
	
	
	public VoteRest(String id, String description, int count, String url) {
		super(id);
		this.description = description;
		this.count = count;
		this.url = url;
	}
	
	
	public String getDescription() {
		return description;
	}
	public int getCount() {
		return count;
	}
	public String getUrl() {
		return url;
	}
	
	

}
