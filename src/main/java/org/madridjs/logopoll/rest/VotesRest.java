package org.madridjs.logopoll.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class VotesRest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8098957913631434058L;
	private Date date;
	private List<String> votes;
	
	
	
	public VotesRest() {
		super();
	}
	public VotesRest(List<String> votes) {
		super();
		this.votes = votes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<String> getVotes() {
		return votes;
	}
	
	public void setVotes(List<String> votes) {
		this.votes = votes;
	}
	
	
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}

}
