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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import edu.uiowa.icts.bluebutton.dao.*;
import edu.uiowa.icts.bluebutton.domain.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Generated by Protogen
 * @since Wed Oct 29 09:27:59 CDT 2014
 */
public class LabTestControllerMvcTest extends AbstractControllerMVCTests {
	

    private LabTest firstLabTest;
    
    @Before
    public void setup() throws FileNotFoundException, IOException {
        super.setup();
        
              // add 20 records to test database
        for(int x=1; x<21; x++){
        	LabTest labTest = new LabTest();
        	labTest.setLabTestId(3000+x);
        	bluebuttonDaoService.getLabTestService().save(labTest);
	        if (x == 1){
	        	// use this ID for update, show, and delete assertions
	        	firstLabTest = labTest;
	        }
        }   
          }

    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void addShouldDisplayNewLabTestForm() throws Exception {
       mockMvc.perform(get("/labtest/add"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("labTest")) 
       .andExpect(view().name("/bluebutton/labtest/edit"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void listShouldSimplyLoadPage() throws Exception {
       mockMvc.perform(get("/labtest/list"))
       .andExpect(status().isOk())
       .andExpect(view().name("/bluebutton/labtest/list"));
    }
    
    @Test
    public void indexShouldDisplayListPage() throws Exception {
       mockMvc.perform(get("/labtest/"))
       .andExpect(status().isOk())
       .andExpect(view().name("/bluebutton/labtest/list"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void listAltShouldLoadListOfLabTests() throws Exception {
       mockMvc.perform(get("/labtest/list_alt"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("labTestList")) 
       .andExpect(view().name("/bluebutton/labtest/list_alt"));
    }
    
    
          	  
//    @Test
//    public void saveShouldUpdateAndRedirectToListView() throws Exception {
//       int count = bluebuttonDaoService.getLabTestService().list().size();
//       
//       mockMvc.perform(post("/labtest/save").param("labTest.labTestId", this.firstLabTest.getLabTestId().toString())).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/labtest/list"));   
//       
//       assertEquals("LabTest count should increase by 1", count +1 , bluebuttonDaoService.getLabTestService().list().size());
//	}
  
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void editShouldLoadObjectAndDisplayForm() throws Exception {
    	mockMvc.perform(get("/labtest/edit").param("labTestId", firstLabTest.getLabTestId().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("labTest")) 
         .andExpect(view().name("/bluebutton/labtest/edit"));
    }
    
    @Test
    public void showShouldLoadAndDisplayObject() throws Exception {
    	mockMvc.perform(get("/labtest/show").param("labTestId", firstLabTest.getLabTestId().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("labTest")) 
         .andExpect(view().name("/bluebutton/labtest/show"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void deleteGetShouldLoadAndDisplayYesNoButtons() throws Exception {
    	mockMvc.perform(get("/labtest/delete").param("labTestId", firstLabTest.getLabTestId().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("labTest")) 
         .andExpect(view().name("/bluebutton/labtest/delete"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void deletePostSubmitYesShouldDeleteAndRedirectToListView() throws Exception {
        int count = bluebuttonDaoService.getLabTestService().list().size();

       mockMvc.perform(post("/labtest/delete").param("labTestId", firstLabTest.getLabTestId().toString())
       .param("submit", "Yes")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/labtest/list"));  
       
       assertEquals("count should decrease by 1", count - 1 , bluebuttonDaoService.getLabTestService().list().size());
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void deletePostSubmitNoShouldNotDeleteAndRedirectToListView() throws Exception {
        int count = bluebuttonDaoService.getLabTestService().list().size();

       mockMvc.perform(post("/labtest/delete").param("labTestId", firstLabTest.getLabTestId().toString())
       .param("submit", "No")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/labtest/list"));  
       
       assertEquals("count should NOT decrease by 1", count , bluebuttonDaoService.getLabTestService().list().size());
    }
      
    @Test
    public void defaultDatatableShouldReturnJSONDataWith10Rows() throws Exception {
    	mockMvc.perform(get("/labtest/datatable")
			.param("display", "list")
			.param("search[value]", "")
			.param("search[regex]", "false")
			.param("length", "10")
			.param("start", "0")
			.param("columnCount", "9")
			.param("draw", "1")
			.param("individualSearch", "true")
						.param("columns[0][data]","0").param("columns[0][name]","dataType").param("columns[0][searchable]","true").param("columns[0][orderable]","true").param("columns[0][search][regex]","false").param("columns[0][search][value]","")
						.param("columns[1][data]","1").param("columns[1][name]","dateCreated").param("columns[1][searchable]","true").param("columns[1][orderable]","true").param("columns[1][search][regex]","false").param("columns[1][search][value]","")
						.param("columns[2][data]","2").param("columns[2][name]","dateUpdated").param("columns[2][searchable]","true").param("columns[2][orderable]","true").param("columns[2][search][regex]","false").param("columns[2][search][value]","")
						.param("columns[3][data]","3").param("columns[3][name]","description").param("columns[3][searchable]","true").param("columns[3][orderable]","true").param("columns[3][search][regex]","false").param("columns[3][search][value]","")
						.param("columns[4][data]","4").param("columns[4][name]","labTestRanges").param("columns[4][searchable]","true").param("columns[4][orderable]","true").param("columns[4][search][regex]","false").param("columns[4][search][value]","")
						.param("columns[5][data]","5").param("columns[5][name]","loincCode").param("columns[5][searchable]","true").param("columns[5][orderable]","true").param("columns[5][search][regex]","false").param("columns[5][search][value]","")
						.param("columns[6][data]","6").param("columns[6][name]","name").param("columns[6][searchable]","true").param("columns[6][orderable]","true").param("columns[6][search][regex]","false").param("columns[6][search][value]","")
						.param("columns[7][data]","7").param("columns[7][name]","tdsNumber").param("columns[7][searchable]","true").param("columns[7][orderable]","true").param("columns[7][search][regex]","false").param("columns[7][search][value]","")
						.param("columns[8][data]","8").param("columns[8][name]","units").param("columns[8][searchable]","true").param("columns[8][orderable]","true").param("columns[8][search][regex]","false").param("columns[8][search][value]","")
						.accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is(bluebuttonDaoService.getLabTestService().list().size())))
    	.andExpect(jsonPath("$.recordsFiltered", is(bluebuttonDaoService.getLabTestService().list().size())))
    	.andExpect(jsonPath("$.draw", is("1")))
    	// max # of returned data rows should be 10 per "length" value
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
        ;
    }
    	
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void csvImportShouldDisplayFileUploadForm() throws Exception {
    	mockMvc.perform(get("/labtest/import"))
         .andExpect(status().isOk())
         .andExpect(view().name("/bluebutton/labtest/import"));
    }
    
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void CSVImportShouldInsertRecordsIntoDatabase() throws Exception {
    	assertEquals( 20, this.bluebuttonDaoService.getLabTestService().count() );
    	File file = new File("src/test/resources/csv/lab-tests.csv");
    	FileInputStream fis = new FileInputStream(file);
    	MockMultipartFile mmFile = new MockMultipartFile("file", fis);
       // MockMultipartFile mmFile = new MockMultipartFile("file", "test-file.csv", MediaType.TEXT_PLAIN_VALUE,fis.);
        
        mockMvc.perform(((MockMultipartHttpServletRequestBuilder) fileUpload("/labtest/import")).file(mmFile))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(containsString("redirect:/labtest/")));   
        
	//	this.bluebuttonDaoService.getLabTestService().importCSV(new FileInputStream(file));
		
    	assertEquals( 31, this.bluebuttonDaoService.getLabTestService().count() );
    	
    	LabTest labTest = this.bluebuttonDaoService.getLabTestService().findById(1);
    	assertNotNull(labTest);
    	assertEquals("Porphobilinogen,Spot Urine", labTest.getName());
    	assertEquals("Porphobilinogen,Spot Urine", labTest.getDescription());
    	assertEquals("Positive",labTest.getUnits());
    	assertEquals("2809-2",labTest.getLoincCode());
    }
    	  
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void labTestsAndRangesShouldReturnJSONDataForGenderAndAge() throws Exception {
    	// load data
   	    assertEquals( 20, this.bluebuttonDaoService.getLabTestService().count() );
    	File file = new File("src/test/resources/csv/lab-tests.csv");
		this.bluebuttonDaoService.getLabTestService().importCSV(new FileInputStream(file));
    	assertEquals( 31, this.bluebuttonDaoService.getLabTestService().count() );
    	
    	assertEquals( 0, this.bluebuttonDaoService.getLabTestRangeService().count() );
    	File file2 = new File("src/test/resources/csv/lab-test-ranges.csv");
		this.bluebuttonDaoService.getLabTestRangeService().importCSV(new FileInputStream(file2));
    	assertEquals( 30, this.bluebuttonDaoService.getLabTestRangeService().count() );
    	
    	String loincCodeCsvList = "2809-2,11273-0,769-0,718-7";
    	
    	mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "fEmale").param("age", "15").param("loincCodeCsvList", loincCodeCsvList)
    	         .accept(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
    	        .andExpect(jsonPath("$.ranges.[2].min_normal", is(12)))
    	    	.andExpect(jsonPath("$.ranges.[2].max_normal", is(16)))
    	    	.andExpect(jsonPath("$.ranges.[3].min_normal", is(3.799999952)))
    	    	.andExpect(jsonPath("$.ranges.[3].max_normal", is(5.199999809)));
    		;

		mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "male").param("age", "15").param("loincCodeCsvList", loincCodeCsvList)
   	         .accept(MediaType.APPLICATION_JSON))
   	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
   	        .andExpect(jsonPath("$.ranges.[2].min_normal", is(14)))
   	    	.andExpect(jsonPath("$.ranges.[2].max_normal", is(18)))
   	    	.andExpect(jsonPath("$.ranges.[3].min_normal", is(4.300000191)))
   	    	.andExpect(jsonPath("$.ranges.[3].max_normal", is(5.900000095)));
   		;
   		// test empty sex
		mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "").param("age", "15").param("loincCodeCsvList", loincCodeCsvList)
	   	         .accept(MediaType.APPLICATION_JSON))
	   	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
	   	        .andExpect(jsonPath("$.ranges.['718-7']").doesNotExist());
   		// test empty age
			mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "male").param("age", "").param("loincCodeCsvList", loincCodeCsvList)
   	         .accept(MediaType.APPLICATION_JSON))
   	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
   	        .andExpect(jsonPath("$.ranges.['718-7']").doesNotExist());
   		
   		// test empty both sex and age
			mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "").param("age", "").param("loincCodeCsvList", loincCodeCsvList)
   	         .accept(MediaType.APPLICATION_JSON))
   	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
   	    	.andExpect(jsonPath("$.ranges.['718-7']").doesNotExist());
			
		// test loinc codes referenced are not included in parameter list of loinc codes
		   loincCodeCsvList = "LP31755-9,13969-1,14976-5,3936-2"; // random but real loinc codes
	    	
	    	mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "fEmale").param("age", "15").param("loincCodeCsvList", loincCodeCsvList)
	    	         .accept(MediaType.APPLICATION_JSON))
	    	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
	    	        .andExpect(jsonPath("$.ranges.['718-7']").doesNotExist())
    	    	.andExpect(jsonPath("$.ranges.['11273-0']").doesNotExist())
	    		;

			mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "male").param("age", "15").param("loincCodeCsvList", loincCodeCsvList)
	   	         .accept(MediaType.APPLICATION_JSON))
	   	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
	   	        .andExpect(jsonPath("$.ranges.['718-7']").doesNotExist())
	   	    	.andExpect(jsonPath("$.ranges.['11273-0']").doesNotExist());
			
    }
    
    @Test
    @WithMockUser(username="user", roles={"BLUEBUTTON_ADMIN"})
    public void labTestsAndRangesShouldReturnJSONDataForLoincCodeCategoryNames() throws Exception {
    	    
    	LoincCodeCategory llc1 = new LoincCodeCategory();
    	llc1.setLoincCode("1234-5");
    	llc1.setRootCategoryName("test root");
    	llc1.setSubrootCategoryName("test sub name");
    	
    	LoincCodeCategory llc1Two = new LoincCodeCategory();
    	llc1Two.setLoincCode("1234-6");
    	llc1Two.setRootCategoryName("test root also");
    	llc1Two.setSubrootCategoryName("test sub name also");
    	
    	LoincCodeCategory llc1Three = new LoincCodeCategory();
    	llc1Three.setLoincCode("1234-7");
    	llc1Three.setRootCategoryName("test root also three");
    	llc1Three.setSubrootCategoryName("test sub name also three");
    	
    	this.bluebuttonDaoService.getLoincCodeCategoryService().save(llc1);
    	this.bluebuttonDaoService.getLoincCodeCategoryService().save(llc1Two);
    	this.bluebuttonDaoService.getLoincCodeCategoryService().save(llc1Three);
    	
    	
    	String loincCodeCsvList = "1234-5,1234-6,1234-7";
    	
    	mockMvc.perform(get("/labtest/ranges_with_categories").param("sex", "fEmale").param("age", "15").param("loincCodeCsvList", loincCodeCsvList)
    	         .accept(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.categories.[0].subRootName", is("test sub name")))
    	    	.andExpect(jsonPath("$.categories.[1].subRootName", is("test sub name also")))
    	    	.andExpect(jsonPath("$.categories.[2].subRootName", is("test sub name also three")))
    		;
    }

}