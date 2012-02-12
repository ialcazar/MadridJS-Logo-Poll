package org.madridjs.logopoll.services;


import java.util.List;

import org.madridjs.logopoll.rest.VotesRest;

public interface VotesService {

	VotesRest listAllRest();
	void vote(Long userId, List<Long> myVotes);

}
