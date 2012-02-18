package org.madridjs.logopoll.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.mail.MethodNotSupportedException;

import org.madridjs.logopoll.daos.UserRepository;
import org.madridjs.logopoll.daos.VoteRepository;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.dto.VoteDto;
import org.madridjs.logopoll.rest.VotesRest;
import org.madridjs.logopoll.services.VotesService;
import org.madridjs.logopoll.web.VotesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VotesServiceImpl implements VotesService {
	private static final Logger logger = LoggerFactory.getLogger(VotesServiceImpl.class);
	private VoteRepository votesDao;
	private UserRepository usersDao;
	
	@Inject
	public VotesServiceImpl(VoteRepository votesDao, UserRepository usersDao) {
		this.votesDao = votesDao;
		this.usersDao = usersDao;
	}

	public VotesRest listAllRest() throws MethodNotSupportedException {
		if(true)
			throw new MethodNotSupportedException("Method not supported");
		return null;
	}
	
	public void vote(Long userId, List<Long> myVotes) {
		logger.debug("Starting vote with userId:"+userId+",myVotes:"+myVotes);
		if(myVotes == null)
			throw new IllegalArgumentException("Votes list is null");
		
		UserDto userDto = usersDao.findOne(userId);
		logger.debug("Retrieved userId:"+userId+",userDto:"+userDto);
		for(Long voteId: myVotes){
			VoteDto voteDto = new VoteDto(voteId);
			userDto.addVote(voteDto);
		
			
		}
		usersDao.save(userDto);

	}

}
