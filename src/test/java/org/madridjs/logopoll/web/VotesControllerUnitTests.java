package org.madridjs.logopoll.web;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.exceptions.ResourceNotFoundException;
import org.madridjs.logopoll.rest.LogosRest;
import org.madridjs.logopoll.rest.VotesRest;
import org.madridjs.logopoll.services.LoginService;
import org.madridjs.logopoll.services.LogosService;
import org.madridjs.logopoll.services.VotesService;
import org.springframework.ui.Model;


public class VotesControllerUnitTests {
	private final static Long USER_ID = 1l;
	
	@Test
	public void user_votes_some_logos(){
		List<Long> votes = Arrays.asList(1l,2l,3l);
		VotesRest myVotes = new VotesRest();
		VotesService votesService = mock(VotesService.class);
		
		VotesController votesController = new VotesController(votesService);
		
		votesController.vote(USER_ID,myVotes);
		
		verify(votesService).vote(USER_ID,votes);
	}

//	@Test
//	public void list_all_votes_exists_some_logos() {
//		VotesService votesService = mock(VotesService.class);
//		Model model = mock(Model.class);
//		VotesRest items = mock(VotesRest.class);
//		VotesController votesController = new VotesController(votesService);
//		
//		when(votesService.listAllRest()).thenReturn(items);
//		
//		items = votesController.listAllVotes(model);
//		
//		verify(votesService).listAllRest();
//	}
//	
//	@Test
//	public void list_all_logos_not_exists_logos() {
//		VotesService votesService = mock(VotesService.class);
//		Model model = mock(Model.class);
//		
//		VotesController votesController = new VotesController(votesService);
//		
//		when(votesService.listAllRest()).thenReturn(null);
//	
//		try {
//			votesController.listAllVotes(model);
//			fail("Expected raise ResourceNotFoundException");
//		} catch (ResourceNotFoundException e) {
//			
//		}
//		verify(votesService).listAllRest();
//		
//		
//	}
//	
//	@Test
//	public void list_all_logos_general_error() {
//		VotesService votesService = mock(VotesService.class);
//		Model model = mock(Model.class);
//		
//		VotesController votesController = new VotesController(votesService);
//		
//		when(votesService.listAllRest()).thenThrow(new RuntimeException());
//	
//		try {
//			votesController.listAllVotes(model);
//			fail("Expected raise ResourceNotFoundException");
//		} catch (GeneralErrorException e) {
//			
//		}
//		verify(votesService).listAllRest();
//		
//		
//	}
	
	

}
