package org.madridjs.logopoll.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.madridjs.logopoll.rest.LogoRest;
import org.madridjs.logopoll.rest.LogosRest;
import org.madridjs.logopoll.rest.VoteRest;
import org.madridjs.logopoll.rest.VotesRest;
import org.madridjs.logopoll.services.LogosService;
import org.madridjs.logopoll.services.VotesService;
import org.madridjs.logopoll.web.LogosController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VotesServiceDummyImpl implements VotesService{
	private static final Logger logger = LoggerFactory.getLogger(VotesServiceDummyImpl.class);

	public VotesRest listAllRest() {
		logger.debug("Searching Votes");
		VotesRest votesRest = null;
	
		List<VoteRest> items = new ArrayList<VoteRest>(5);
		
		items.add(new VoteRest(1,"Logo 1",10,"images/logos/logo1.png"));
		items.add(new VoteRest(1,"Logo 2",50,"images/logos/logo2.png"));
		items.add(new VoteRest(1,"Logo 3",26,"images/logos/logo3.png"));
		items.add(new VoteRest(1,"Logo 4",12,"images/logos/logo4.png"));
		items.add(new VoteRest(1,"Logo 5",23,"images/logos/logo5.png"));
		
		votesRest = new VotesRest(new Date(), items);
		
		logger.debug("Returning Votes");
		return votesRest;
	}

}
