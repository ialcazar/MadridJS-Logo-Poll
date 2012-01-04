package org.madridjs.logopoll.rest;

import java.io.Serializable;

public class VoteRest implements Serializable{

	
	private static final long serialVersionUID = -108280258133453780L;
	private int id;
	private String description;
	private int count;
	private String url;
	
	
	public VoteRest(int id, String description, int count, String url) {
		super();
		this.id = id;
		this.description = description;
		this.count = count;
		this.url = url;
	}
	
	public int getId() {
		return id;
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
