package edu.uiowa.icts.bluebutton.web;

/*
 * #%L
 * blue-button Spring MVC Web App
 * %%
 * Copyright (C) 2014 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uiowa.icts.bluebutton.controller.AbstractBluebuttonController;
import edu.uiowa.icts.exception.MappingNotFoundException;

@Controller
@RequestMapping( "/*" )
public class DefaultController extends AbstractBluebuttonController {

	private static final Log log = LogFactory.getLog( DefaultController.class );

	@RequestMapping( "/**" )
	public void mappingNotFound( HttpServletRequest request, HttpServletResponse response ) throws MappingNotFoundException {
		throw new MappingNotFoundException( request.getRequestURL().toString() );
	}

	@ExceptionHandler
	public ModelAndView error( HttpServletRequest request, Exception exception ) {
		log.error( "Error URI: " + request.getRequestURI() );
		log.error( "Error Username: " + getUsername() );
		log.error( "Error Message: " + exception.getMessage(), exception );
		ModelMap model = new ModelMap();
		model.addAttribute( "exception", exception );
		return new ModelAndView( "error", model );
	}
	
	@RequestMapping( value = "{page}" , method = RequestMethod.GET )
	public String displayDefault( @PathVariable String page, ModelMap model ) {
		return page;
	}

	@RequestMapping( value = { "/", "index" } , method = RequestMethod.GET )
	public String indexPage( ModelMap model ) {
		return "index";
	}
	
	@RequestMapping( value = "message" , method = RequestMethod.GET )
	public String message( ModelMap model, 
		@RequestParam( value = "error" , required = false ) Boolean error,
		@RequestParam( value = "headerText" , required = false ) String headerText, 
		@RequestParam( value = "message" , required = false ) String message ) {
		
		model.addAttribute( "error", error == null ? false : error );
		model.addAttribute( "headerText", headerText );
		model.addAttribute( "message", message );
		
		return "message";
	}

	@RequestMapping( value = "login" , method = RequestMethod.GET )
	public String login( ModelMap model, @RequestParam( value = "error" , required = false ) String error ) {
		model.addAttribute( "error", error == null ? false : error );
		return "login";
	}

}