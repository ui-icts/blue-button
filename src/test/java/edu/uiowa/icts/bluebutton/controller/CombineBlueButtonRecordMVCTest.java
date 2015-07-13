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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.uiowa.icts.bluebutton.dao.BluebuttonDaoService;

public class CombineBlueButtonRecordMVCTest extends AbstractControllerMVCTests  {
  
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd Z");

    
    @Before
    public void setup() throws FileNotFoundException, IOException {
        
    	super.setup();
    	
        File file = new File("src/test/resources/csv/lab-tests.csv");
		this.bluebuttonDaoService.getLabTestService().importCSV(new FileInputStream(file));
    	
    	File file2 = new File("src/test/resources/csv/lab-test-ranges.csv");
		this.bluebuttonDaoService.getLabTestRangeService().importCSV(new FileInputStream(file2));

		// freeze time
        DateTime dt= format.parseDateTime("2015-02-04 +00:00");
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
    }
    
    @After
    public void tearDown() throws Exception {
    	// unfreeze time
    	DateTimeUtils.setCurrentMillisSystem();
    }
	
	@Test
    public void indexShouldReturnJsonForJSONWithEverythingWithTwoRecords() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/blue_button_sample_with_everything_with_two_records.json");
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("10")))
    	    	.andExpect(jsonPath("$.encounters", hasSize(is(4)))) 
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(3)))) 
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1291377600000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1266062400000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.vitalsGrid[0].statsInformation.mean", is(new Double(16.60))))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.allergies[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.allergies[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.smokingStatus.name",is("unknown")))
    	    	;
    }
	
	@Test
    public void indexShouldReturnJsonForJSONWithEverythingWithThreeRecords() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/blue_button_sample_with_everything_with_three_records.json");
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("10")))
    	    	.andExpect(jsonPath("$.encounters", hasSize(is(4)))) 
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(3)))) 
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1291377600000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1266062400000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.vitalsGrid[0].statsInformation.mean", is(new Double(16.60))))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.smokingStatus.name",is("unknown")))
    	    	;
    }
	
	@Test
    public void indexShouldReturnJsonForJSONWithEverythingWithTwoRecordsWithMixedSoruces() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/blue_button_sample_with_everything_with_two_records_with_two_sources.json");
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("10")))
    	    	.andExpect(jsonPath("$.encounters", hasSize(is(4)))) 
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(3)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(1))))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1291377600000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1266062400000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.vitalsGrid[0].statsInformation.mean", is(new Double(16.60))))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals, Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.medications[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[1].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[2].source", is("Not Community Health and Hospitals")))
				.andExpect(jsonPath("$.medications[3].source", is("Not Community Health and Hospitals")))
				.andExpect(jsonPath("$.labResultsGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.immunizations[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.immunizations[0].subGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.procedures[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.procedures[0].subGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.allergies[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.allergies[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.encounters[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.encounters[0].subGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.problems[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.problems[0].subGrid[0].source", is("Not Community Health and Hospitals")))
    	        .andExpect(jsonPath("$.vitalsGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.vitalsGrid[0].subGrid[0].source", is("Not Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.smokingStatus.name",is("unknown")))
    	    	;
    }
	
	
}
