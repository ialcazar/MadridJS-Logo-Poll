package org.madridjs.logopoll.services;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.madridjs.logopoll.daos.UserRepository;
import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.dto.VoteDto;
import org.madridjs.logopoll.exceptions.NotVotesFoundException;
import org.madridjs.logopoll.services.impl.VotesServiceImpl;


public class VotesServiceUnitTests {
	private VotesService votesService;
	
	private VoteDto voteDto;
	private UserDto userDto;
	private UserRepository usersDao;
	private MailService    mailService;
	
	private final static Long USER_ID = 1l;
	
	@Before
	public void setUp(){
		mockUp();
		votesService = new VotesServiceImpl(usersDao,mailService);
		
	}

	private void mockUp() {
		voteDto = mock(VoteDto.class);
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
	
	@Test
	public void user_confirms_votes(){
			Set<VoteDto> myVotes = new HashSet<VoteDto>(3);
			myVotes.add(voteDto);
			myVotes.add(voteDto);
			
			when(usersDao.findByTimeStampsAndUserId(anyString(), anyLong())).thenReturn(userDto);
			when(userDto.getVotes()).thenReturn(myVotes);
			
			
			votesService.confirm(userDto);
			
			verify(usersDao).findByTimeStampsAndUserId(anyString(), anyLong());
			verify(userDto).getVotes();
			verify(voteDto,atLeast(myVotes.size())).addCount();
			verify(usersDao).save(userDto);
	}
	
	@Test
	public void user_confirms_votes_when_user_hasnt_votes(){
			Set<VoteDto> myVotes = new HashSet<VoteDto>(3);
			
			when(usersDao.findByTimeStampsAndUserId(anyString(), anyLong())).thenReturn(userDto);
			when(userDto.getVotes()).thenReturn(myVotes);
			
			try{
				votesService.confirm(userDto);
				fail("Expecting NotVotesFoundException");
			}catch(NotVotesFoundException e){}
			
			verify(usersDao).findByTimeStampsAndUserId(anyString(), anyLong());
			verify(userDto).getVotes();
			
	}
		
}
