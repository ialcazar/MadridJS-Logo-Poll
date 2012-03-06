package org.madridjs.logopoll.services;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.dto.VoteDto;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:spring/test-context.xml") 
public class VotesServiceIntegrationTests {
	@Inject
	private VotesService votesService;
	
	
	private final static Long USER_ID = 100l;
	
	@Test
	@Ignore
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
	
	@Test
	public void user_confirms_votes(){
			UserDto userDto = new UserDto();
			userDto.setTimeStamp("123456");
			userDto.setUserId(101l);
			
			votesService.confirm(userDto);
			
			//TODO Confirm votes are added
	}
		
}
