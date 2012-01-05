package org.madridjs.logopoll.rest;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class RestEntity implements Serializable {
	
	private String id;
	
	

	public RestEntity() {
		super();
	}


	public RestEntity(String id) {
		super();
		this.id = id;
	}
	
	
	
	public String getId() {
		return id;
	}


	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}

}
