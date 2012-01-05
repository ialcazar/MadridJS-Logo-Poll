package org.madridjs.logopoll.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.madridjs.logopoll.rest.LogoRest;
import org.madridjs.logopoll.rest.LogosRest;
import org.madridjs.logopoll.services.LogosService;
import org.madridjs.logopoll.web.LogosController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogosServiceDummyImpl implements LogosService{
	private static final Logger logger = LoggerFactory.getLogger(LogosServiceDummyImpl.class);

	public LogosRest listAllRest() {
		logger.debug("Searching Logos");
		LogosRest logosRest = null;
	
		List<LogoRest> items = new ArrayList<LogoRest>(5);
		
		items.add(new LogoRest("1","Logo 1","images/logos/logo1.png"));
		items.add(new LogoRest("2","Logo 2","images/logos/logo2.png"));
		items.add(new LogoRest("3","Logo 3","images/logos/logo3.png"));
		items.add(new LogoRest("4","Logo 4","images/logos/logo4.png"));
		items.add(new LogoRest("5","Logo 5","images/logos/logo5.png"));
		
		logosRest = new LogosRest(items.size(), items);
		
		logger.debug("Returning Logos");
		return logosRest;
	}

}
