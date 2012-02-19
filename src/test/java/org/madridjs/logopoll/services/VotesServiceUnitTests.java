package org.madridjs.logopoll.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.madridjs.logopoll.converters.UserConverter;
import org.madridjs.logopoll.daos.UserRepository;
import org.madridjs.logopoll.daos.VoteRepository;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.dto.VoteDto;
import org.madridjs.logopoll.exceptions.EmailException;

import org.madridjs.logopoll.rest.UserRest;
import org.madridjs.logopoll.services.impl.LoginServiceImpl;
import org.madridjs.logopoll.services.impl.VotesServiceImpl;
import org.madridjs.logopoll.validators.UserValidator;


public class VotesServiceUnitTests {
	private VotesService votesService;
	
	private UserDto userDto;
	private UserRepository usersDao;
	private VoteRepository votesDao; 
	private MailService    mailService;
	
	private final static Long USER_ID = 1l;
	private final static String USER_EMAIL = "miemail@unemail.com";
	
	@Before
	public void setUp(){
		mockUp();
		votesService = new VotesServiceImpl(usersDao,mailService);
		
	}

	private void mockUp() {
		userDto = mock(UserDto.class);
		usersDao = mock(UserRepository.class);
		mailService = mock(MailService.class);
	}
	
	@Test
	public void user_votes_one_logo_result_correct(){
		
		List<Long> myVotes = Arrays.asList(1l);
		
		
		
		when(usersDao.findOne(USER_ID)).thenReturn(userDto);
			
		
		votesService.vote(USER_ID, myVotes);
		
		verify(usersDao).findOne(USER_ID);
		verify(userDto).addVote(any(VoteDto.class));
		verify(usersDao).save(any(UserDto.class));
		verify(mailService).send(anyString(), anyString(), anyString(), anyString());
	}
	@Test
	public void user_votes_two_logo_result_correct(){
		
		List<Long> myVotes = Arrays.asList(1l,2l);
		
		
		when(usersDao.findOne(USER_ID)).thenReturn(userDto);
		
		
		
		
		votesService.vote(USER_ID, myVotes);
		
		verify(userDto,times(2)).addVote(any(VoteDto.class));
		verify(usersDao).save(any(UserDto.class));
	}
	
	@Test
	public void user_doesnt_vote_throws_exception(){
		
		List<Long> myVotes = null;
		
		try{
			votesService.vote(USER_ID, myVotes);
			fail("Expected IllegalArgumentException");
		}catch(IllegalArgumentException e){}
		
		
	}
		
}
