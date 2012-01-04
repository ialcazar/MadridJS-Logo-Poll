package org.madridjs.logopoll.web;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.madridjs.logopoll.exceptions.EmailExistsException;
import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.exceptions.ResourceNotFoundException;
import org.madridjs.logopoll.rest.LogosRest;
import org.madridjs.logopoll.rest.VotesRest;
import org.madridjs.logopoll.services.LoginService;
import org.madridjs.logopoll.services.LogosService;
import org.madridjs.logopoll.services.VotesService;
import org.springframework.ui.Model;


public class VotesControllerUnitTests {

	@Test
	public void list_all_votes_exists_some_logos() {
		VotesService votesService = mock(VotesService.class);
		Model model = mock(Model.class);
		VotesRest items = mock(VotesRest.class);
		VotesController votesController = new VotesController(votesService);
		
		when(votesService.listAllRest()).thenReturn(items);
		
		String returned = votesController.listAllVotes(model);
		
		verify(votesService).listAllRest();
		verify(model).addAttribute("votes",items);
		
		assertEquals("votes",returned);
	}
	
	@Test
	public void list_all_logos_not_exists_logos() {
		VotesService votesService = mock(VotesService.class);
		Model model = mock(Model.class);
		
		VotesController votesController = new VotesController(votesService);
		
		when(votesService.listAllRest()).thenReturn(null);
	
		try {
			votesController.listAllVotes(model);
			fail("Expected raise ResourceNotFoundException");
		} catch (ResourceNotFoundException e) {
			
		}
		verify(votesService).listAllRest();
		
		
	}
	
	@Test
	public void list_all_logos_general_error() {
		VotesService votesService = mock(VotesService.class);
		Model model = mock(Model.class);
		
		VotesController votesController = new VotesController(votesService);
		
		when(votesService.listAllRest()).thenThrow(new RuntimeException());
	
		try {
			votesController.listAllVotes(model);
			fail("Expected raise ResourceNotFoundException");
		} catch (GeneralErrorException e) {
			
		}
		verify(votesService).listAllRest();
		
		
	}
	
	

}
