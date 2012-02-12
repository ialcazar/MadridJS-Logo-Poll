package org.madridjs.logopoll.services.impl;

import java.util.List;

import org.madridjs.logopoll.daos.UserRepository;
import org.madridjs.logopoll.daos.VoteRepository;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.dto.VoteDto;
import org.madridjs.logopoll.rest.VotesRest;
import org.madridjs.logopoll.services.VotesService;

public class VotesServiceImpl implements VotesService {
	private VoteRepository votesDao;
	private UserRepository usersDao;
	
	public VotesServiceImpl(VoteRepository votesDao, UserRepository usersDao) {
		this.votesDao = votesDao;
		this.usersDao = usersDao;
	}

	public VotesRest listAllRest() {
		// TODO Auto-generated method stub
		return null;
	}

	public void vote(Long userId, List<Long> myVotes) {
		if(myVotes == null)
			throw new IllegalArgumentException("Votes list is null");
		
		UserDto userDto = usersDao.findOne(userId);
		
		for(Long voteId: myVotes){
			VoteDto voteDto = new VoteDto(voteId);
			voteDto.addUser(userDto);
		
			votesDao.save(voteDto);
		}
		

	}

}
