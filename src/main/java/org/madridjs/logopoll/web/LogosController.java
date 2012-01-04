package org.madridjs.logopoll.web;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.madridjs.logopoll.dto.Logo;
import org.madridjs.logopoll.exceptions.GeneralErrorException;
import org.madridjs.logopoll.exceptions.ResourceNotFoundException;
import org.madridjs.logopoll.rest.LogoRest;
import org.madridjs.logopoll.rest.LogosRest;
import org.madridjs.logopoll.services.LogosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LogosController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogosController.class);
	private LogosService logosService;
	
	@Inject
	public LogosController(LogosService logosService){
		this.logosService = logosService;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/hey", method = RequestMethod.GET)
	public String hey(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "hey";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Root home! the client locale is "+ locale.toString());
		
		
		String msg = "Bienvenido a la encuesta para elegir el logo de MadridJS";
		
		model.addAttribute("msg", msg );
		
		return "home";
	}
	
	@RequestMapping(value = "/logos", method = RequestMethod.GET)
	public  String listAllLogos(Model model) {
		logger.debug("Starting listAllLogos " );
		
		
		LogosRest items = null;
		
		try{
			
			items = logosService.listAllRest();
			model.addAttribute("logos",items);
			
			
		}catch(Throwable e){
			throw new GeneralErrorException(e);
		}
		
		if(items == null)
			throw new ResourceNotFoundException("Recurso no encontrado");
		
		return "logos";
		
	}
	
}
