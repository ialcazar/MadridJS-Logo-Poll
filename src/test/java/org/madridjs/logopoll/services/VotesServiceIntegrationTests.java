package org.madridjs.logopoll.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:spring/test-context.xml") 
@Transactional
public class VotesServiceIntegrationTests {
	@Inject
	private VotesService votesService;
	
	
	private final static Long USER_ID = 100l;
	
	@Test
	public void user_votes_one_logo_result_correct(){
		
		List<Long> myVotes = Arrays.asList(1l);
		
		votesService.vote(USER_ID, myVotes);
		
	}
	@Test
	@Ignore
	public void user_votes_two_logo_result_correct(){
		
		
	}
	
//	@Test
//	public void user_doesnt_vote_throws_exception(){
//		
//		List<Long> myVotes = null;
//		
//		votesService = new VotesServiceImpl(votesDao,usersDao);
//		try{
//			votesService.vote(USER_ID, myVotes);
//			fail("Expected IllegalArgumentException");
//		}catch(IllegalArgumentException e){}
//		
//		
//	}
		
}
