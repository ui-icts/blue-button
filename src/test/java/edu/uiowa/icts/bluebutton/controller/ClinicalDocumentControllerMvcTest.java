package edu.uiowa.icts.bluebutton.controller;

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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.uiowa.icts.bluebutton.dao.ClinicalDocumentService;
import edu.uiowa.icts.bluebutton.dao.PersonService;
import edu.uiowa.icts.bluebutton.domain.ClinicalDocument;
import edu.uiowa.icts.bluebutton.domain.Person;

/**
 * Generated by Protogen
 * @since Fri Aug 01 10:04:19 CDT 2014
 */
public class ClinicalDocumentControllerMvcTest extends AbstractControllerMVCTests {
	            
	private Integer firstClinicalDocumentId;
	private Person p;
    
    @Before
    public void setup() throws FileNotFoundException, IOException {
        
    	super.setup();
    	
        this.p = new Person();
        p.setFirstName( "foo" );
        
        p.setLastName( "bar" );
        personService.save( p );
        
        // add 20 clinical documents to test database
        for(int x=1; x<21; x++){
	        ClinicalDocument cd = new ClinicalDocument();
	        String docString = "<xml><clinicaldocument>"+x+"</clinicaldocument>";
	        cd.setDocument(docString.getBytes("UTF-8"));
	        cd.setName("file"+ x+".xml");
	        cd.setPerson( p );
	        cd.setDescription("dev server #"+x);
	        clinicalDocumentService.saveOrUpdate(cd);
	        if (x == 1){
	        	// use this ID for update, show, and delete assertions
	        	firstClinicalDocumentId = cd.getClinicalDocumentId();
	        }
        }   
    }

    @Test
    public void defaultDatatableShouldReturnJSONDataWithNameInDescendingOrder() throws Exception {
    	mockMvc.perform(get("/clinicaldocument/datatable")
    			.param("display", "list")
    			.param("search[value]", "")
    			.param("search[regex]", "false")
    			.param("length", "10")
    			.param("start", "0")
    			.param("columnCount", "5")
    			.param("draw", "20")
    			.param("individualSearch", "true")
    			.param("order[0][column]", "1").param("order[0][dir]","desc")
				.param("columns[0][data]","0").param("columns[0][name]","person").param("columns[0][searchable]","true").param("columns[0][orderable]","true").param("columns[0][search][regex]","false").param("columns[0][search][value]","")
    			.param("columns[1][data]","1").param("columns[1][name]","name").param("columns[1][searchable]","true").param("columns[1][orderable]","true").param("columns[1][search][regex]","false").param("columns[1][search][value]","")
				.param("columns[2][data]","2").param("columns[2][name]","description").param("columns[2][searchable]","true").param("columns[2][orderable]","true").param("columns[2][search][regex]","false").param("columns[2][search][value]","")
				.param("columns[3][data]","3").param("columns[3][name]","dateUploaded").param("columns[3][searchable]","true").param("columns[3][orderable]","true").param("columns[3][search][regex]","false").param("columns[3][search][value]","")
				.param("columns[4][data]","4").param("columns[4][name]","urls").param("columns[4][searchable]","false").param("columns[4][orderable]","false").param("columns[4][search][regex]","false").param("columns[4][search][value]","")
    			.accept(MediaType.APPLICATION_JSON))
    //	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is(clinicalDocumentService.list().size())))
    	.andExpect(jsonPath("$.recordsFiltered", is(clinicalDocumentService.list().size())))
    	.andExpect(jsonPath("$.draw", is("20")))
    	// max # of returned data rows should be 10 per "length" value
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
    	// test array of hosts is returned
    	.andExpect(jsonPath("$.data[0][1]", is("file9.xml")))
    	.andExpect(jsonPath("$.data[0][2]", is("dev server #9")))
    	.andExpect(jsonPath("$.data[0][4]", containsString("show?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[0][4]", containsString("edit?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[0][4]", containsString("delete?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[9][1]", is("file19.xml")))
    	.andExpect(jsonPath("$.data[9][2]", is("dev server #19")))
    	.andExpect(jsonPath("$.data[9][4]", containsString("show?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[9][4]", containsString("edit?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[9][4]", containsString("delete?clinicalDocumentId=")))
        ;
    }
    
    
    @Test
    public void defaultDatatableShouldReturnJSONData() throws Exception {
    	mockMvc.perform(get("/clinicaldocument/datatable")
    			.param("display", "list")
    			.param("search[value]", "")
    			.param("search[regex]", "false")
    			.param("length", "10")
    			.param("start", "0")
    			.param("columnCount", "5")
    			.param("draw", "20")
    			.param("individualSearch", "true")
				.param("columns[0][data]","0").param("columns[0][name]","person").param("columns[0][searchable]","true").param("columns[0][orderable]","true").param("columns[0][search][regex]","false").param("columns[0][search][value]","")
    			.param("columns[1][data]","1").param("columns[1][name]","name").param("columns[1][searchable]","true").param("columns[1][orderable]","true").param("columns[1][search][regex]","false").param("columns[1][search][value]","")
				.param("columns[2][data]","2").param("columns[2][name]","description").param("columns[2][searchable]","true").param("columns[2][orderable]","true").param("columns[2][search][regex]","false").param("columns[2][search][value]","")
				.param("columns[3][data]","3").param("columns[3][name]","dateUploaded").param("columns[3][searchable]","true").param("columns[3][orderable]","true").param("columns[3][search][regex]","false").param("columns[3][search][value]","")
				.param("columns[4][data]","4").param("columns[4][name]","urls").param("columns[4][searchable]","false").param("columns[4][orderable]","false").param("columns[4][search][regex]","false").param("columns[4][search][value]","")
    			.accept(MediaType.APPLICATION_JSON))
    //	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is(clinicalDocumentService.list().size())))
    	.andExpect(jsonPath("$.recordsFiltered", is(clinicalDocumentService.list().size())))
    	.andExpect(jsonPath("$.draw", is("20")))
    	// max # of returned data rows should be 10 per "length" value
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
    	// test array of hosts is returned
    	.andExpect(jsonPath("$.data[0][1]", is("file1.xml")))
    	.andExpect(jsonPath("$.data[0][2]", is("dev server #1")))
    	.andExpect(jsonPath("$.data[0][4]", containsString("show?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[0][4]", containsString("edit?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[0][4]", containsString("delete?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[9][1]", is("file10.xml")))
    	.andExpect(jsonPath("$.data[9][2]", is("dev server #10")))
    	.andExpect(jsonPath("$.data[9][4]", containsString("show?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[9][4]", containsString("edit?clinicalDocumentId=")))
    	.andExpect(jsonPath("$.data[9][4]", containsString("delete?clinicalDocumentId=")))
        ;
    }

    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void confirmShouldShowErrorIfSourceIsBlank() throws Exception{    	
    	mockMvc.perform(post("/clinicaldocument/confirm")
    			.param("clinicalDocumentId",this.firstClinicalDocumentId.toString())
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			
    			.andExpect(view().name("/bluebutton/clinicaldocument/confirmSource"))
    			.andExpect(model().attributeHasFieldErrors("clinicalDocument", "source"))
    			.andExpect(model().attribute("clinicalDocument",hasProperty("xml", is("<xml><clinicaldocument>1</clinicaldocument>"))))
    			.andExpect(model().errorCount(1));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void confirmShouldRedirectOnSuccesfulSave() throws Exception{    	
    	mockMvc.perform(post("/clinicaldocument/confirm")
    			.param("clinicalDocumentId",this.firstClinicalDocumentId.toString())
    			.param("source", "Source")
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().is3xxRedirection())
    			 .andExpect(view().name(containsString("redirect:/person/show?personId="+this.p.getPersonId())));
    	ClinicalDocument cd = this.clinicalDocumentService.findById(firstClinicalDocumentId);
    	assertEquals("1.0.0", cd.getJsonParserVersion());
    }
    
    @Test
    public void defaultDatatableShouldReturnJSONDataWith11Rows() throws Exception {
    	mockMvc.perform(get("/clinicaldocument/datatable")
    			.param("display", "list")
    			.param("search[value]", "")
    			.param("search[regex]", "false")
    			.param("length", "11")
    			.param("start", "0")
    			.param("columnCount", "5")
    			.param("draw", "20")
    			.param("individualSearch", "true")
				.param("columns[0][data]","0").param("columns[0][name]","person").param("columns[0][searchable]","true").param("columns[0][orderable]","true").param("columns[0][search][regex]","false").param("columns[0][search][value]","")
    			.param("columns[1][data]","1").param("columns[1][name]","name").param("columns[1][searchable]","true").param("columns[1][orderable]","true").param("columns[1][search][regex]","false").param("columns[1][search][value]","")
				.param("columns[2][data]","2").param("columns[2][name]","description").param("columns[2][searchable]","true").param("columns[2][orderable]","true").param("columns[2][search][regex]","false").param("columns[2][search][value]","")
				.param("columns[3][data]","3").param("columns[3][name]","dateUploaded").param("columns[3][searchable]","true").param("columns[3][orderable]","true").param("columns[3][search][regex]","false").param("columns[3][search][value]","")
				.param("columns[4][data]","4").param("columns[4][name]","urls").param("columns[4][searchable]","false").param("columns[4][orderable]","false").param("columns[4][search][regex]","false").param("columns[4][search][value]","")
    			.accept(MediaType.APPLICATION_JSON))
    	//.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is(clinicalDocumentService.list().size())))
    	.andExpect(jsonPath("$.recordsFiltered", is(clinicalDocumentService.list().size())))
    	.andExpect(jsonPath("$.draw", is("20")))
    	// max # of returned data rows should be 11 per "length" value
    	.andExpect(jsonPath("$.data", hasSize(is(11))))
        ;
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void saveShouldPersistNewClinicalDocumentAndRedirectToShowView() throws Exception {
       int count = clinicalDocumentService.list().size();
       MockMultipartFile mmFile = new MockMultipartFile("file", "originalFilename", MediaType.APPLICATION_XML_VALUE,"<xml><clinicaldocument></clinicaldocument>".getBytes());
       
       mockMvc.perform(((MockMultipartHttpServletRequestBuilder) fileUpload("/clinicaldocument/save.html")
    		   .param("person.personId", "1")
    		   .param("description","empty file"))
    		   .file(mmFile))
      // .andDo(print())
       .andExpect(status().is3xxRedirection())
       .andExpect(view().name(containsString("redirect:/clinicaldocument/confirm?clinicalDocumentId=")));   
       
       assertEquals("clinical document count should increase by 1", count +1 , clinicalDocumentService.list().size());
       
       ClinicalDocument cd = clinicalDocumentService.findByProperty("name", "originalFilename");
       assertNotNull(cd);
       assertEquals("empty file", cd.getDescription());
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void saveWithWhiteSpaceDescriptionShouldReturnUserToEditView() throws Exception {
       int count = clinicalDocumentService.list().size();       
       MockMultipartFile mmFile = new MockMultipartFile("file", "originalFilename", MediaType.APPLICATION_XML_VALUE,"<xml><clinicaldocument></clinicaldocument>".getBytes());
       
       mockMvc.perform(((MockMultipartHttpServletRequestBuilder) fileUpload("/clinicaldocument/save.html")
    		   .param("person.personId", "1")
    		   .param("description","   ")
    		   )
    		   .file(mmFile))
       .andExpect(status().isOk())
       .andExpect(model().attributeHasErrors("clinicalDocument"))
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(model().attributeExists("personList")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/edit")); 
       
       assertEquals("clinical document count should NOT increase by 1", count , clinicalDocumentService.list().size());
    }
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void saveWithNullDescriptionShouldReturnUserToEditView() throws Exception {
       int count = clinicalDocumentService.list().size();       
       MockMultipartFile mmFile = new MockMultipartFile("file", "originalFilename", MediaType.APPLICATION_XML_VALUE,"<xml><clinicaldocument></clinicaldocument>".getBytes());
       
       mockMvc.perform(((MockMultipartHttpServletRequestBuilder) fileUpload("/clinicaldocument/save.html")
    		   .param("person.personId", "1")
    	//	   .param("description","")
    		   )
    		   .file(mmFile))
       
       .andExpect(status().isOk())
       .andExpect(model().attributeHasErrors("clinicalDocument"))
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(model().attributeExists("personList")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/edit")); 
       
       assertEquals("clinical document count should NOT increase by 1", count , clinicalDocumentService.list().size());
    }
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void saveWithEmptyDescriptionShouldReturnUserToEditView() throws Exception {
       int count = clinicalDocumentService.list().size();       
       MockMultipartFile mmFile = new MockMultipartFile("file", "originalFilename", MediaType.APPLICATION_XML_VALUE,"<xml><clinicaldocument></clinicaldocument>".getBytes());
       
       mockMvc.perform(((MockMultipartHttpServletRequestBuilder) fileUpload("/clinicaldocument/save.html")
    		   .param("person.personId", "1")
    		   .param("description","")
    		   )
    		   .file(mmFile))
       
       .andExpect(status().isOk())
       .andExpect(model().attributeHasErrors("clinicalDocument"))
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(model().attributeExists("personList")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/edit")); 
       
       assertEquals("clinical document count should NOT increase by 1", count , clinicalDocumentService.list().size());
    }
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void saveWithNullFileShouldReturnUserToEditView() throws Exception {
       int count = clinicalDocumentService.list().size();       
       mockMvc.perform(((MockMultipartHttpServletRequestBuilder) fileUpload("/clinicaldocument/save.html")
    		   .param("person.personId", "1")
    		   .param("description", "for patient"))
    		   .file("file", null)
    		   )
       .andExpect(status().isOk())
       .andExpect(model().attributeHasErrors("clinicalDocument"))
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(model().attributeExists("personList")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/edit")); 
       
       assertEquals("clinical document count should NOT increase by 1", count , clinicalDocumentService.list().size());
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void saveWithEmptyFileShouldReturnUserToEditView() throws Exception {
       int count = clinicalDocumentService.list().size();
       MockMultipartFile mmFile = new MockMultipartFile("file", "originalFilename", MediaType.APPLICATION_XML_VALUE,"".getBytes());
       
       mockMvc.perform(((MockMultipartHttpServletRequestBuilder) fileUpload("/clinicaldocument/save.html")
    		   .param("person.personId", "1")
    		   .param("description", "for patient"))
    		   .file(mmFile)
    		   )
       .andExpect(status().isOk())
       .andExpect(model().attributeHasErrors("clinicalDocument"))
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(model().attributeExists("personList")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/edit")); 
       
       assertEquals("clinical document count should NOT increase by 1", count , clinicalDocumentService.list().size());
    }
    
    @Test
    public void indexShouldDisplayListPage() throws Exception {
       mockMvc.perform(get("/clinicaldocument/"))
       .andExpect(status().isOk())
       .andExpect(view().name("/bluebutton/clinicaldocument/list"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void listShouldSimplyLoadPage() throws Exception {
       mockMvc.perform(get("/clinicaldocument/list.html"))
       .andExpect(status().isOk())
       .andExpect(view().name("/bluebutton/clinicaldocument/list"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void listAltShouldLoadListOfDocuments() throws Exception {
       mockMvc.perform(get("/clinicaldocument/list_alt.html"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("clinicalDocumentList")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/list_alt"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void editShouldDisplayClinicalDocumentForm() throws Exception {
       mockMvc.perform(get("/clinicaldocument/edit.html")
    		   .param("clinicalDocumentId", firstClinicalDocumentId.toString()))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/confirmSource"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void editWithBogusIdShouldRedirectToListView() throws Exception {
       mockMvc.perform(get("/clinicaldocument/edit.html")
    		   .param("clinicalDocumentId", "012312"))
       .andExpect(status().isOk())
       .andExpect(model().attributeDoesNotExist("clinicalDocuments")) 
       .andExpect(view().name("/bluebutton/clinicaldocuments/list"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void showShouldDisplayClinicalDocumentForm() throws Exception {
       mockMvc.perform(get("/clinicaldocument/show.html")
    		   .param("clinicalDocumentId", firstClinicalDocumentId.toString()))
       .andExpect(status().is3xxRedirection())
		 .andExpect(view().name(containsString("redirect:/person/show?personId="+this.p.getPersonId())));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void showWithBogusIdShouldShouldRedirectToListView() throws Exception {
       mockMvc.perform(get("/clinicaldocument/show.html")
    		   .param("clinicalDocumentId", "012312"))
       .andExpect(status().isOk())
       .andExpect(model().attributeDoesNotExist("clinicalDocuments")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/list"));
    }
    
    @Test
    public void showByFileNameShouldDisplayClinicalDocument() throws Exception {
       mockMvc.perform(get("/clinicaldocument/file1.xml/file"))
       .andExpect(status().is3xxRedirection())
		 .andExpect(view().name(containsString("redirect:/person/show?personId="+this.p.getPersonId())));
    }
    
    @Test
    public void showByFileNameWithBogusFileNameShouldShouldRedirectToListView() throws Exception {
       mockMvc.perform(get("/clinicaldocument/file1000.xml/file"))
       .andExpect(status().isOk())
       .andExpect(model().attributeDoesNotExist("clinicalDocument")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/list"));
    }

    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void addShouldDisplayNewClinicalDocumentForm() throws Exception {
       mockMvc.perform(get("/clinicaldocument/add.html"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(model().attributeExists("personList")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/edit"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void deleteGetShouldDisplayClinicalDocumentForm() throws Exception {
       mockMvc.perform(get("/clinicaldocument/delete.html").param("clinicalDocumentId", firstClinicalDocumentId.toString()))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("clinicalDocument")) 
       .andExpect(view().name("/bluebutton/clinicaldocument/delete"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void deletePostSubmitYesShouldDeleteClinicalDocumentAndRedirectToListView() throws Exception {
        int count = clinicalDocumentService.list().size();

       mockMvc.perform(post("/clinicaldocument/delete.html").param("clinicalDocumentId", firstClinicalDocumentId.toString()).param("submit", "Yes"))
       .andExpect(status().is3xxRedirection())
       .andExpect(view().name("redirect:/clinicaldocument/list.html"));  
       
       assertEquals("clinical document count should decrease by 1", count - 1 , clinicalDocumentService.list().size());
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void deletePostSubmitYesIgnoreCaseShouldDeleteClinicalDocumentAndRedirectToListView() throws Exception {
        int count = clinicalDocumentService.list().size();

       mockMvc.perform(post("/clinicaldocument/delete.html").param("clinicalDocumentId", firstClinicalDocumentId.toString()).param("submit", "yES"))
       .andExpect(status().isMovedTemporarily())
       .andExpect(view().name("redirect:/clinicaldocument/list.html"));  
       
       assertEquals("clinical document count should decrease by 1", count - 1 , clinicalDocumentService.list().size());
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void deletePostSubmitNoShouldNotDeleteClinicalDocumentAndRedirectToListView() throws Exception {
        int count = clinicalDocumentService.list().size();

       mockMvc.perform(post("/clinicaldocument/delete.html").param("clinicalDocumentId", firstClinicalDocumentId.toString()).param("submit", "No"))
       .andExpect(status().isMovedTemporarily())
       .andExpect(view().name("redirect:/clinicaldocument/list.html"));  
       
       assertEquals("clinical document count should NOT decrease by 1", count , clinicalDocumentService.list().size());
    }
    

}