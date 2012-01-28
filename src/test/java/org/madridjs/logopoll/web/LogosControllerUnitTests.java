package org.madridjs.logopoll.web;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.exceptions.ResourceNotFoundException;
import org.madridjs.logopoll.rest.LogosRest;
import org.madridjs.logopoll.services.LoginService;
import org.madridjs.logopoll.services.LogosService;
import org.springframework.ui.Model;


public class LogosControllerUnitTests {

	@Test
	public void list_all_logos_exists_some_logos() {
		LogosService logosService = mock(LogosService.class);
		Model model = mock(Model.class);
		LogosRest items = mock(LogosRest.class);
		LogosController logosController = new LogosController(logosService);
		
		when(logosService.listAllRest()).thenReturn(items);
		
		items = logosController.listAllLogos(model);
		
		verify(logosService).listAllRest();
		
		
		
	}
	
	@Test
	public void list_all_logos_not_exists_logos() {
		LogosService logosService = mock(LogosService.class);
		Model model = mock(Model.class);
		
		LogosController logosController = new LogosController(logosService);
		
		when(logosService.listAllRest()).thenReturn(null);
	
		try {
			logosController.listAllLogos(model);
			fail("Expected raise ResourceNotFoundException");
		} catch (ResourceNotFoundException e) {
			
		}
		verify(logosService).listAllRest();
		
		
	}
	
	@Test
	public void list_all_logos_general_error() {
		LogosService logosService = mock(LogosService.class);
		Model model = mock(Model.class);
		
		LogosController logosController = new LogosController(logosService);
		
		when(logosService.listAllRest()).thenThrow(new RuntimeException());
	
		try {
			logosController.listAllLogos(model);
			fail("Expected raise ResourceNotFoundException");
		} catch (GeneralErrorException e) {
			
		}
		verify(logosService).listAllRest();
		
		
	}
	
	

}
