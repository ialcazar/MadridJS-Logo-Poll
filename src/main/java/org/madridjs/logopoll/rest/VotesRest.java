package org.madridjs.logopoll.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VotesRest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8098957913631434058L;
	private Date date;
	private List<VoteRest> items;
	
	public VotesRest(Date date, List<VoteRest> items) {
		super();
		this.date = date;
		this.items = items;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getDate() {
		return date;
	}
	public List<VoteRest> getItems() {
		return items;
	}
	
	
	
	

}
