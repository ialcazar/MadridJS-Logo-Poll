package org.madridjs.logopoll.rest;

import java.io.Serializable;
import java.util.List;

public class LogosRest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7393924157484812382L;
	
	
	private int count;
	private List<LogoRest> items;
	
	public LogosRest(int count, List<LogoRest> items) {
		super();
		this.count = count;
		this.items = items;
	}

	

	public int getCount() {
		return count;
	}

	public List<LogoRest> getItems() {
		return items;
	}
}
