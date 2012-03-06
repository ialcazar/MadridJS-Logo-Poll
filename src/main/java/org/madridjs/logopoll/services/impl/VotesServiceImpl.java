package org.madridjs.logopoll.services.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.mail.MethodNotSupportedException;

import org.madridjs.logopoll.daos.UserRepository;
import org.madridjs.logopoll.daos.VoteRepository;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.dto.VoteDto;
import org.madridjs.logopoll.exceptions.NotVotesFoundException;
import org.madridjs.logopoll.exceptions.UserNotFoundException;
import org.madridjs.logopoll.rest.VotesRest;
import org.madridjs.logopoll.services.MailService;
import org.madridjs.logopoll.services.VotesService;
import org.madridjs.logopoll.web.VotesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class VotesServiceImpl implements VotesService {
	private static final Logger logger = LoggerFactory.getLogger(VotesServiceImpl.class);
	private UserRepository usersDao;
	private MailService mailService;
	
	@Inject
	public VotesServiceImpl(UserRepository usersDao, MailService mailService) {
		
		this.usersDao = usersDao;
		this.mailService = mailService;
	}

	public VotesRest listAllRest() throws MethodNotSupportedException {
		if(true)
			throw new MethodNotSupportedException("Method not supported");
		return null;
	}
	
	public void vote(Long userId, List<Long> myVotes) {
		String currentTime = null;
		logger.info("Starting vote with userId:"+userId+",myVotes:"+myVotes);
		if(myVotes == null)
			throw new IllegalArgumentException("Votes list is null");
		
		UserDto userDto = usersDao.findOne(userId);
		logger.debug("Retrieved userId:"+userId+",userDto:"+userDto);
		for(Long voteId: myVotes){
			VoteDto voteDto = new VoteDto(voteId);
			userDto.addVote(voteDto);
			
		}
		
		currentTime = getCurrentTimeStampMillis();
		userDto.setTimeStamp(currentTime);
		usersDao.save(userDto);
		String body = createBody(userDto);
		String subject = createSubject();
		mailService.send("madridjavascript@gmail.com", userDto.getEmail(), subject, body);
		

	}
	
	private String getCurrentTimeStampMillis(){
		return String.valueOf(System.currentTimeMillis());
	}

	private String createSubject() {
		return "[Madrid.js][Votacion] Confirma tu voto";
	}

	private String createBody(UserDto userDto) {
		return "Gracias por participar.\n Necesitamos que confirmes tu voto visitando la siguiente dirección: http://poll.madridjs.org/confirm?id="+userDto.getTimeStamp()+"&i="+userDto.getUserId();
		
	}

	@Override
	public void confirm(UserDto userDto) {
		logger.info("Starting confirm vote ["+userDto+"]");
		logger.debug("Finding user by TimeStamp and Id");
		UserDto myUserDto = usersDao.findByTimeStampsAndUserId(userDto.getTimeStamp(), userDto.getUserId());
		
		if(myUserDto == null)
			throw new UserNotFoundException("User not found "+userDto);
		
		Set<VoteDto> votes = myUserDto.getVotes();
		
		if(votes == null || votes.size() == 0)
			throw new NotVotesFoundException("There are votes for this userId "+ userDto.getUserId());
		
		logger.debug("Obtained "+votes.size()+" Votes");
		
		for(VoteDto vote:votes){
			vote.addCount();
		}
		
		usersDao.save(myUserDto);
		logger.debug("User Saved "+myUserDto);
		
		
	}

}
