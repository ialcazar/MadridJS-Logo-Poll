package org.madridjs.logopoll.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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


public class VotesServiceIntegrationTests {
	private VotesService votesService;
	@Test
	public void user_votes_one_logo_result_correct(){
		Long userId = 1l;
		List<Long> myVotes = Arrays.asList(1l);
		UserDto userDto = new UserDto();
		
		UserRepository usersDao = mock(UserRepository.class);
		VoteRepository votesDao = mock(VoteRepository.class);
		
		when(usersDao.findOne(userId)).thenReturn(userDto);
		
		votesService = new VotesServiceImpl(votesDao,usersDao);
		
		
		votesService.vote(userId, myVotes);
		
		verify(usersDao).findOne(userId);
		
		verify(votesDao).save(any(VoteDto.class));
	}
	@Test
	public void user_votes_two_logo_result_correct(){
		Long userId = 1l;
		List<Long> myVotes = Arrays.asList(1l,2l);
		UserDto userDto = new UserDto();
		
		UserRepository usersDao = mock(UserRepository.class);
		VoteRepository votesDao = mock(VoteRepository.class);
		
		when(usersDao.findOne(userId)).thenReturn(userDto);
		
		votesService = new VotesServiceImpl(votesDao,usersDao);
		
		
		votesService.vote(userId, myVotes);
		
		verify(usersDao).findOne(userId);
		
		verify(votesDao,times(2)).save(any(VoteDto.class));
	}
		
}
