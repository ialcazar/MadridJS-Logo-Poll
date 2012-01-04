package org.madridjs.logopoll.web;



import javax.inject.Inject;


import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.exceptions.ResourceNotFoundException;

import org.madridjs.logopoll.rest.VotesRest;

import org.madridjs.logopoll.services.VotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles requests for the application home page.
 */
@Controller
public class VotesController {
	
	private static final Logger logger = LoggerFactory.getLogger(VotesController.class);
	private VotesService votesService;
	
	@Inject
	public VotesController(VotesService votesService){
		this.votesService = votesService;
	}
	
	
	@RequestMapping(value = "/votes", method = RequestMethod.GET)
	@ResponseBody
	public  VotesRest listAllVotes(Model model) {
		logger.debug("Starting listAllVotes");
		
		
		VotesRest items = null;
		
		try{
			
			items = votesService.listAllRest();
			
			
		}catch(Throwable e){
			throw new GeneralErrorException(e);
		}
		
		if(items == null)
			throw new ResourceNotFoundException("Recurso no encontrado");
		
		
		return items;
		
	}
	
}
