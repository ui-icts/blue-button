package edu.uiowa.icts.bluebutton.resource;

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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

import edu.uiowa.icts.bluebutton.domain.*;
import edu.uiowa.icts.exception.EntityNotFoundException;
import edu.uiowa.icts.spring.GenericDaoListOptions;

/**
 * Generated by Protogen 
 * @since ${date}
 */
@RestController
@RequestMapping( "/api/labresultsynonym" )
public class LabResultSynonymResource extends AbstractBluebuttonApiResource {

    private static final Log log = LogFactory.getLog( LabResultSynonymResource.class );
    
    @RequestMapping( value = { "{labResultSynonymId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public LabResultSynonym get(@PathVariable( "labResultSynonymId" ) Integer labResultSynonymId ) {
    	 LabResultSynonym labResultSynonym = bluebuttonDaoService.getLabResultSynonymService().findById( labResultSynonymId );
		 if (labResultSynonym == null){
			 throw new EntityNotFoundException();
		 } 
	     return labResultSynonym;
    }
    
    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE  )
    public LabResultSynonym create(@RequestBody @Valid LabResultSynonym labResultSynonym ) {
		 bluebuttonDaoService.getLabResultSynonymService().save( labResultSynonym );
		 return labResultSynonym;
    }
    
    @RequestMapping( value = { "{labResultSynonymId}" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE  )
    public LabResultSynonym update(@PathVariable( "labResultSynonymId" ) Integer labResultSynonymId, @RequestBody @Valid LabResultSynonym labResultSynonym ) {
    	LabResultSynonym labResultSynonymRecord = bluebuttonDaoService.getLabResultSynonymService().findById( labResultSynonymId );
    	if (labResultSynonymRecord == null || !labResultSynonymRecord.getLabResultSynonymId().equals(labResultSynonym.getLabResultSynonymId())){
			 throw new EntityNotFoundException(); 
		 } 
    	 bluebuttonDaoService.getLabResultSynonymService().getSession().flush();
         bluebuttonDaoService.getLabResultSynonymService().getSession().clear();
		 bluebuttonDaoService.getLabResultSynonymService().update( labResultSynonym );
		 return labResultSynonym;
    }
    
    @RequestMapping( value = { "{labResultSynonymId}" }, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE )
    public String delete(@PathVariable( "labResultSynonymId" ) Integer labResultSynonymId ) {
    	LabResultSynonym labResultSynonym = bluebuttonDaoService.getLabResultSynonymService().findById( labResultSynonymId );
		 if (labResultSynonym == null){
			 throw new EntityNotFoundException();
		 } 
		 bluebuttonDaoService.getLabResultSynonymService().delete(labResultSynonym);
	     return "";
    }
    
    @RequestMapping( value = {  "", "/"  }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public List<LabResultSynonym> list() {
    	 return bluebuttonDaoService.getLabResultSynonymService().list();
    }

}