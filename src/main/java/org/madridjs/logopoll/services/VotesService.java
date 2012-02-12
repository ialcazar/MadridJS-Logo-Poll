package org.madridjs.logopoll.services;


import java.util.List;

import javax.mail.MethodNotSupportedException;

import org.madridjs.logopoll.rest.VotesRest;

public interface VotesService {

	VotesRest listAllRest() throws MethodNotSupportedException;
	void vote(Long userId, List<Long> myVotes) ;

}
