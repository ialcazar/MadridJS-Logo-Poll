package org.madridjs.logopoll.web;



import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.exceptions.ResourceNotFoundException;

import org.madridjs.logopoll.rest.VotesRest;

import org.madridjs.logopoll.services.VotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


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

	@RequestMapping(value = "/vote/{userId}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void vote(@PathVariable Long userId, @RequestBody VotesRest myVotes) {
		List<Long> longVotes = new ArrayList<Long>();
		logger.debug("Starting a vote with userId:"+userId+",myvotes:"+myVotes);
		
		for(String vote:myVotes.getVotes()){
			longVotes.add(Long.valueOf(vote));
		}
		logger.debug("Converted VotesRest to Long Votes:"+longVotes);
		votesService.vote(userId, longVotes);
	}
	@RequestMapping(value="/confirm")
	public String confirmVote(@RequestParam("id") String timeStamp,
							  @RequestParam("i") String userId, Model model){
		String msg ="";
		UserDto userDto = new UserDto();
		userDto.setTimeStamp(timeStamp);
		userDto.setUserId(Long.valueOf(userId));
		try{
			votesService.confirm(userDto);
			msg = "Gracias por confirmar tu voto";
		}catch(Throwable e){
			msg = "Error inexperado. Vuelva a pinchar sobre el enlace en su email para confirmar el voto";
		}
		
		model.addAttribute("msg", msg);
		
		return "confirm";
		
	}
	
}
