package org.madridjs.logopoll.services;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:spring/test-context.xml") 
public class VotesServiceIntegrationTests {
	@Inject
	private VotesService votesService;
	
	
	private final static Long USER_ID = 100l;
	
	@Test
	public void user_votes_one_logo_result_correct(){
		
		List<Long> myVotes = Arrays.asList(1l);
		
		votesService.vote(USER_ID, myVotes);
		
		//TODO Check inserts into database
		
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
