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


import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

import edu.uiowa.icts.datatable.DataTableColumn;
import edu.uiowa.icts.datatable.DataTableRequest;
import edu.uiowa.icts.datatable.DataTableSearch;
import edu.uiowa.icts.bluebutton.dao.*;
import edu.uiowa.icts.bluebutton.domain.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import edu.uiowa.icts.bluebutton.controller.AbstractControllerMVCTests;

/**
 * Generated by Protogen
 * @since Wed Jul 08 08:12:43 CDT 2015
 */
@WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
public class PersonResourceMvcTest extends AbstractControllerMVCTests {
	
    private Person firstPerson;
    private ObjectMapper mapper;
    
    @Before
    public void before() { 
        // add 20 records to test database
        for(int x=1; x<21; x++){
        	Person person = new Person();
        	bluebuttonDaoService.getPersonService().save(person);
	        if (x == 1){
	        	// use this ID for update, show, and delete assertions
	        	firstPerson = person;
	        }
        }   
        this.mapper = new ObjectMapper();
        // fix NonUniqueObjectException
        this.bluebuttonDaoService.getPersonService().getSession().flush();
        this.bluebuttonDaoService.getPersonService().getSession().clear();
    }    
      
    @Test
    public void getByPathVariableIdShouldLoadAndReturnObject() throws Exception {
    	mockMvc.perform(get("/api/person/"+firstPerson.getPersonId().toString()))
         .andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.personId", is(firstPerson.getPersonId())))
        ;
    }
  
    @Test
    public void getByPathVariableIdShouldReturn404ForBogusId() throws Exception {
    	mockMvc.perform(get("/api/person/-123")).andExpect(status().isNotFound()).andExpect(jsonPath("$.message", is("/api/person/-123 could not be found.")));
    }
    
    @Test
    public void restMappingNotFoundShouldReturn404() throws Exception {
    	mockMvc.perform(get("/api/person/asdfasdf/asdfasdf"))
    	.andExpect(status().isNotFound())
    	 .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.message", is("/api/person/asdfasdf/asdfasdf could not be found.")))
    	;
    }
    
    @Test
    public void createShouldPersistAndReturnObject() throws Exception {
	   long count = bluebuttonDaoService.getPersonService().count();	       
	   Person person = new Person(); 
       
       mockMvc.perform(post("/api/person/").content(this.mapper.writeValueAsString(person))
	   .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
       .andExpect(jsonPath("$.personId").value(IsNull.notNullValue()))  
       ;
       
       assertEquals("count should increase by 1", count +1 , bluebuttonDaoService.getPersonService().count());
	}
     
    @Test
    public void updateShouldPersistExistingAndReturnObject() throws Exception {
       long count = bluebuttonDaoService.getPersonService().count();

       mockMvc.perform(post("/api/person/"+ firstPerson.getPersonId().toString())
    		   .content(this.mapper.writeValueAsString(firstPerson))
    		   .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
       .andExpect(jsonPath("$.personId", is(firstPerson.getPersonId())))
       ;
         
       assertEquals("count NOT should increase", count , bluebuttonDaoService.getPersonService().count());
  	}  
    
    @Test
    public void updateByPathVariableIdShouldReturn404ForMismatchBetweenPathIdAndObjectId() throws Exception {	       
       String correctId =  firstPerson.getPersonId().toString();
       // this ID manipulation should be overwritten with path variable id
       firstPerson.setPersonId(-123);
       
       mockMvc.perform(post("/api/person/"+correctId)
    		   .content(this.mapper.writeValueAsString(firstPerson))
    		   .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isNotFound())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
       .andExpect(jsonPath("$.message", is("/api/person/" +correctId +" could not be found.")))
       ;
  	} 
    
    @Test
    public void updateByPathVariableIdShouldReturn404ForBogusPathId() throws Exception {
    	mockMvc.perform(post("/api/person/-123")
    			.content(this.mapper.writeValueAsString(firstPerson))
    		   .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isNotFound())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	    .andExpect(jsonPath("$.message", is("/api/person/-123 could not be found.")));
    }
    
    @Test
    public void deleteShouldDeleteAndReturnStatusOk() throws Exception {
        long count = bluebuttonDaoService.getPersonService().count();

        mockMvc.perform(delete("/api/person/"+ firstPerson.getPersonId().toString()))
       .andExpect(status().isOk());  
       
       assertEquals("count should decrease by 1", count - 1 , bluebuttonDaoService.getPersonService().count());
    }
    
    @Test
    public void deleteShouldFailWithBogusId() throws Exception {
        long count = bluebuttonDaoService.getPersonService().count();

        mockMvc.perform(delete("/api/person/-123"))
       .andExpect(status().isNotFound())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
       .andExpect(jsonPath("$.message", is("/api/person/-123 could not be found.")));  
       
       assertEquals("count should NOT decrease by 1", count , bluebuttonDaoService.getPersonService().count());
    }

    @Test
    public void listShouldReturnAllByDefault() throws Exception {
    	mockMvc.perform(get("/api/person/"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(jsonPath("$.", hasSize(is(20))))
        .andExpect(jsonPath("$.[0].personId", is(firstPerson.getPersonId())))
        ;
    }
    
    @Test
    public void listShouldReturnAllByDefaultWithoutTrailUrlSlash() throws Exception {
    	mockMvc.perform(get("/api/person"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(jsonPath("$.", hasSize(is(20))))
        .andExpect(jsonPath("$.[0].personId", is(firstPerson.getPersonId())))
        ;
    }
}