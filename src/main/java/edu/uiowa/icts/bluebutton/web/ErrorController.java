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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( "/error/*" )
public class ErrorController {

	private static final Log log = LogFactory.getLog( ErrorController.class );

	@ExceptionHandler( Exception.class )
	public ModelAndView exceptionHandler( HttpServletRequest request, Exception exception ) {
		ModelMap model = new ModelMap();
		log.error( "Error URI: " + request.getRequestURI() );
		log.error( exception.getMessage(), exception );
		model.addAttribute( "exception", exception );
		model.addAttribute( "pageTitle", "Error" );
		return new ModelAndView( "/error/display", model );
	}

	@RequestMapping( value = "{page}" )
	public String displayDefault( @PathVariable String page, ModelMap model, HttpServletRequest request ) {
		log.error( "Error URI: " + request.getAttribute( "javax.servlet.error.request_uri" ) );
		Exception exception = (Exception) request.getAttribute( "javax.servlet.error.exception" );
		if ( exception != null ) {
			log.error( request.getAttribute( "javax.servlet.error.message" ), exception );
			model.addAttribute( "exception", exception );
		} else {
			log.error( request.getAttribute( "javax.servlet.error.message" ) );
		}
		model.addAttribute( "pageTitle", "Error" );
		return "/error/" + page;
	}

}