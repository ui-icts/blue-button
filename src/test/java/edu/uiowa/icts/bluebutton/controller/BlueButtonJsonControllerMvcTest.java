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

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.nullValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.jayway.jsonpath.JsonPath;

import edu.uiowa.icts.bluebutton.dao.BluebuttonDaoService;
import edu.uiowa.icts.bluebutton.domain.ClinicalDocument;
import edu.uiowa.icts.bluebutton.domain.LabResultSynonym;
import edu.uiowa.icts.bluebutton.domain.Person;
import edu.uiowa.icts.bluebutton.json.Allergy;
import edu.uiowa.icts.bluebutton.json.BlueButtonRecord;
import edu.uiowa.icts.bluebutton.json.Entry;
import edu.uiowa.icts.bluebutton.json.Lab;
import edu.uiowa.icts.bluebutton.json.LabResult;
import edu.uiowa.icts.bluebutton.json.StatsInformation;

/**
 * Generated by Protogen
 * @since Fri Aug 01 10:04:19 CDT 2014
 */
public class BlueButtonJsonControllerMvcTest extends AbstractControllerMVCTests {
	
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
    
    @Before
    public void setup() throws FileNotFoundException, IOException {
    	super.setup();
    	/*
         * load lab test data
         */
        File file = new File("src/test/resources/csv/lab-tests.csv");
		this.bluebuttonDaoService.getLabTestService().importCSV(new FileInputStream(file));
    	
    	File file2 = new File("src/test/resources/csv/lab-test-ranges.csv");
		this.bluebuttonDaoService.getLabTestRangeService().importCSV(new FileInputStream(file2));

		// freeze time
        DateTime dt= format.parseDateTime("2015-02-04");
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        int id =0;
        this.bluebuttonDaoService.getLabResultSynonymService().save(new LabResultSynonym(id++, "Hemoglobin", "Hemoglobin", ""));
        this.bluebuttonDaoService.getLabResultSynonymService().save(new LabResultSynonym(id++, "Potassium","Potassium [Moles/volume] in Serum or Plasma", "Electrolyte"));
        this.bluebuttonDaoService.getLabResultSynonymService().save(new LabResultSynonym(id++, "Hematocrit", "Hematocrit", ""));
        this.bluebuttonDaoService.getLabResultSynonymService().save(new LabResultSynonym(id++, "Creatine Kinase", "Creatine kinase [Enzymatic activity/volume] in Serum or Plasma", "Nutrition Panel"));
        this.bluebuttonDaoService.getLabResultSynonymService().save(new LabResultSynonym(id++, "Chloresterol Total", "Cholesterol", ""));
        this.bluebuttonDaoService.getLabResultSynonymService().save(new LabResultSynonym(id++, "Hemoglobin", "HGB", ""));
        this.bluebuttonDaoService.getLabResultSynonymService().save(new LabResultSynonym(id++, "White Blood Cells (WBC)", "WBC", ""));

    }
    
    @After
    public void tearDown() throws Exception {
    	// unfreeze time
    	DateTimeUtils.setCurrentMillisSystem();
    }
    @Test
    public void bluebuttonShouldReturnCombinedBlueButtonObjectGivenPersonId() throws Exception{
    	
    	Person person = new Person();
    	person.setFirstName("Test"); person.setLastName("McTest");
    	
    	this.bluebuttonDaoService.getPersonService().save(person);
    	
    	for (int i = 0; i < 3; i++) {
    		ClinicalDocument cd = new ClinicalDocument();
    		String s = new Integer(i).toString();
    		
    		cd.setDateUploaded(new Date());
    		cd.setDescription("test ".concat(s));
    		cd.setPerson(person);
    		cd.setSource("Source ".concat(s));

    		
    		BlueButtonRecord bbr = new BlueButtonRecord();
    		List<Allergy> aList = new ArrayList<Allergy>();
    		
    		Allergy a = new Allergy();
    		
    		Entry allergen = new Entry();
    		allergen.setName("Allergy ".concat(s));
    		a.setAllergen(allergen);
    		
    		aList.add(a);
    		bbr.setAllergies(aList);
    		
    		String bbS = new ObjectMapper().writeValueAsString(bbr);
    		
    		cd.setParsedJson(bbS.getBytes("UTF-8"));
    		
    		this.bluebuttonDaoService.getClinicalDocumentService().save(cd);
		}
    	
    	mockMvc.perform(post(new String("/blue-button/byPerson?personId=").concat(person.getPersonId().toString()).concat("&startDateInMillis=1420092000000&endDateInMillis=1430092000000"))
    		.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))//.andDo(MockMvcResultHandlers.print())
    	    .andExpect(status().isOk())
    	    .andExpect(content().contentType("application/json"))
    	    .andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    .andExpect(jsonPath("$.allergies[0].name", is("Allergy 2")))
    	    .andExpect(jsonPath("$.allergies[1].name", is("Allergy 1")))
    	    .andExpect(jsonPath("$.allergies[2].name", is("Allergy 0")))
    	    .andExpect(jsonPath("$.allergies[0].source", is("Source 2")))
    	    .andExpect(jsonPath("$.allergies[1].source", is("Source 1")))
    	    .andExpect(jsonPath("$.allergies[2].source", is("Source 0")))
    	    ;
    }
    
    
    
    @Test
    public void indexShouldReturnJsonForJaneDoeJune() throws Exception {
    	File categoryfile = new File("src/test/resources/csv/LOINC_250_MULTI-AXIAL_HIERARCHY-for-testing.CSV");
		this.bluebuttonDaoService.getLoincCodeCategoryService().importCSV(new FileInputStream(categoryfile));
    	
    	File file = new File("src/test/resources/bluebutton-json/jane-doe-june-2014.json");
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("28")))
    	    	.andExpect(jsonPath("$.gender", is("female")))
    	    	.andExpect(jsonPath("$.immunizations", hasSize(is(3))))//.andDo(MockMvcResultHandlers.print())
    	    	.andExpect(jsonPath("$.demographics.dataSource", is("unknown")))
    	        .andExpect(jsonPath("$.problems", hasSize(is(4))))
    	        .andExpect(jsonPath("$.problems[0].startDate", is("2013-05-22")))
    	        .andExpect(jsonPath("$.problems[0].dateInMillis", is(1369180800000L)))
    	    	.andExpect(jsonPath("$.problems[0].name", is("Tinnitus")))
    	    	.andExpect(jsonPath("$.problems[1].name", is("Health maintenance examination")))
    	    	.andExpect(jsonPath("$.problems[2].name", is("Diabetes Mellitus without mention of Complication, type II or unspecified type, (ICD-9-CM 250.02)")))
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(0)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(18))))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(5))))
    	    	.andExpect(jsonPath("$.medications[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[1].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[2].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[3].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[4].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1227484800000L))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1401235200000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("been prescribed medications")))
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(18))))
    	    	.andExpect(jsonPath("$.labResultsGrid[5].statsInformation.mean", is(14.1)))
    	    	.andExpect(jsonPath("$.labResultsGrid[5].statsInformation.standardDeviation", is(new Double(0))))
    	    	.andExpect(jsonPath("$.labResultsGrid[5].statsInformation.range", is("14.1 to 14.1")))
    	    	.andExpect(jsonPath("$.labResultsGrid[5].statsInformation.normalDisplay", is("Yes")))
    	    	.andExpect(jsonPath("$.labResultsGrid[5].name", is("Hemoglobin"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[17].name", is("Potassium"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[5].name", is("Hemoglobin"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[6].name", is("Hematocrit"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[6].id", is("labs10")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].date", is("2014-05-28")))
    	    	;
    } 
    
    @Test
    public void indexShouldReturnJsonForJaneDoeJuneWithEndDateOfJuly11_2012() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/end_date_July_2012_jane-doe-june-2014.json");

    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("28")))
    	    	.andExpect(jsonPath("$.gender", is("female")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.medications[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[1].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[2].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[3].source", is(nullValue())))
				.andExpect(jsonPath("$.immunizations", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.problems", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(1)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(22)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].id", is("labs0")))//.andDo(MockMvcResultHandlers.print())
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.demographics.dataSource", is("unknown")))
    	 	    ;
    }
    
    @Test
    public void indexShouldReturnJsonForJaneDoeJuneWithStartDateOfMay21_2013AndEndDateOfMay23_2013() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/start_and_end_date_May_21_2013_jane-doe-May_23_2013.json");

    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))//.andDo(MockMvcResultHandlers.print())
    	    	.andExpect(jsonPath("$.age", is("28")))
    	    	.andExpect(jsonPath("$.gender", is("female")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.medications[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[1].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[2].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[3].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.problems", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(1)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(12)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(0))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.demographics.dataSource", is("unknown")))
    	        ;
    }
    
    
    
    
    
    @Test
    public void indexShouldReturnJsonFor25YearOldFemaleProblemsData() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/25-year-old-female.json");
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.gender", is("female")))
    	    	.andExpect(jsonPath("$.problems", hasSize(is(6))))//.andDo(MockMvcResultHandlers.print())
    	    	.andExpect(jsonPath("$.problems[0].name", is("Multiple gestation")))
    	    	.andExpect(jsonPath("$.problems[0].startDate", is("2010-12-27")))
    	    	.andExpect(jsonPath("$.problems[0].status", is("Active")))
    	    	.andExpect(jsonPath("$.encounters", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1293408000000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1269993600000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple severe")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(2))))    	    	
    	    	.andExpect(jsonPath("$.medications[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[1].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(5)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.locations", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.locations[0].name", is("Community Health and Hospitals")))
    	    	    	    	.andExpect(jsonPath("$.locations[0].type", is("Provider")))

    	    	.andExpect(jsonPath("$.locations[1].name", is("Frances Dixon")))
    	    	.andExpect(jsonPath("$.locations[1].type", is("Home")))

//    	    	.andExpect(jsonPath("$.locations", hasSize(is(13))))
//    	    	.andExpect(jsonPath("$.locations[0].name", is("Community Health and Hospitals")))
//    	    	.andExpect(jsonPath("$.locations[1].name", is("Frances Dixon")))
//    	    	.andExpect(jsonPath("$.locations[1].type", is("Home")))
//    	    	.andExpect(jsonPath("$.locations[2].name", is("Routine general medical examination at a health care facility")))
//    	    	.andExpect(jsonPath("$.locations[3].name", is("Routine general medical examination at a health care facility")))
//    	    	.andExpect(jsonPath("$.locations[3].type", is("Encounter")))

    	        ;
    } 
    
    @Test
    public void indexShouldReturnJsonForJaneDoeAugust() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/jane-doe-august-2014.json");

    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("28")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(9))))
    	    	.andExpect(jsonPath("$.medications[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[1].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[2].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[3].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[4].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[5].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[6].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.medications[7].source", is(nullValue())))
				.andExpect(jsonPath("$.medications[8].source", is(nullValue())))    	    	
    	    	.andExpect(jsonPath("$.immunizations", hasSize(is(0))))
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(1)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(13)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is(nullValue())))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(0))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1175472000000L))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1401840000000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("no")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("been prescribed medications")))
    	    	.andExpect(jsonPath("$.demographics.dataSource", is("unknown")))
    	    	.andExpect(jsonPath("$.labResultsGrid[4].name", is("Creatine Kinase"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[4].labName", is("Nutrition Panel"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[6].name", is("Potassium"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[6].labName", is("Electrolyte"))) //synonym test
    	    	//.andExpect(jsonPath("$.labResultsGrid[9].name", is("Chloresterol Total"))) //synonym test
    	 	    ;
    }
    
    @Test
    public void indexShouldReturnJsonForOutOfRangeData() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/NIST_gov_data_out-of-range-data.json");
    	
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("67")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(1))))
    	    	.andExpect(jsonPath("$.medications[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.immunizations", hasSize(is(2))))
    	    	.andExpect(jsonPath("$.immunizations[0].name", is("influenza, live, intranasal")))
    	    	.andExpect(jsonPath("$.immunizations[1].name", is("Tetanus and diphtheria toxoids - preservative free")))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(1))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1344556800000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1146441600000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("severe")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(3)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.medications[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.medications[0].subGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.immunizations[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.immunizations[0].subGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.procedures[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.procedures[0].subGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.allergies[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.allergies[0].subGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.encounters[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.encounters[0].subGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.problems[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.problems[0].subGrid[0].source", is("Get Well Clinic")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].name", is("Hemoglobin"))) //synonym test
    	    	.andExpect(jsonPath("$.labResultsGrid[1].name", is("White Blood Cells (WBC)"))) //synonym test
    	        ;
    }
        
    
    
    @Test
    public void indexShouldReturnJsonForFullReferenceDataWithEncounters() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/blue_button_sample_with_everything.json");
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("10")))
    	    	.andExpect(jsonPath("$.encounters", hasSize(is(4)))) 
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(3)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(1))))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1291334400000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1266019200000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.vitalsGrid[0].statsInformation.mean", is(new Double(16.60))))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.medications[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[1].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[2].source", is("Community Health and Hospitals")))
				.andExpect(jsonPath("$.medications[3].source", is("Community Health and Hospitals")))
				.andExpect(jsonPath("$.labResultsGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.immunizations[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.immunizations[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.procedures[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.procedures[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.allergies[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.allergies[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.encounters[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.encounters[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.problems[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.problems[0].subGrid[0].source", is("Community Health and Hospitals")))
    	        .andExpect(jsonPath("$.vitalsGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.vitalsGrid[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	;
    }
    
    @Test
    public void indexShouldReturnJsonForLabsOutOfRangeAllergiesMeds() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/vitera_labs-out-of-range-allergies-meds.json");
    	
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("76")))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(5))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1326412800000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1038787200000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Madison Medical Center P. A.")))
    	    	.andExpect(jsonPath("$.smokingStatus.name", is("unknown")))
    	    	.andExpect(jsonPath("$.encounters[0].subGrid[4].organization", is("Provider's Office")))//.andDo(MockMvcResultHandlers.print())
    	    	.andExpect(jsonPath("$.medications", hasSize(is(2))))    	    	
    	    	.andExpect(jsonPath("$.medications[0].source", is("Madison Medical Center P. A.")))
    	    	.andExpect(jsonPath("$.medications[1].source", is("Madison Medical Center P. A.")))
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(0)))) 
    	        ;
    }
    
    @Test
    public void indexShouldReturnJsonFor72YearOldData() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/71-year-old-data.json");
    	
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("71")))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(8))))
    	    	.andExpect(jsonPath("$.vitalsGrid", hasSize(is(4)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(5))))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1287878400000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1269993600000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple moderate")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.smokingStatus.name", is("non-smoker")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(6)))) 
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[1].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[2].source", is("Community Health and Hospitals")))
				.andExpect(jsonPath("$.medications[3].source", is("Community Health and Hospitals")))
				.andExpect(jsonPath("$.medications[4].source", is("Community Health and Hospitals")))  
				.andExpect(jsonPath("$.medications[5].source", is("Community Health and Hospitals")))  	
    	        .andExpect(jsonPath("$.labResultsGrid[3].subGrid[1].statsInformation.displayText", is(StatsInformation.outOfRangeText)))
    	        .andExpect(jsonPath("$.labResultsGrid[3].subGrid[1].loincCode", is("2093-3")))
				;
    }
    
    @Test
    public void indexShouldReturnJsonForFiveYearOldWithImmunizations() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/five-year-old-with-immunizations.json");
    	
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("5")))
    	    	.andExpect(jsonPath("$.gender", is("female")))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(3))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1298332800000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1234483200000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("multiple severe")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(1)))) 
    	    	.andExpect(jsonPath("$.medications[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(1)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Community Health and Hospitals")))
    	        ;
    }
    
    @Test
    public void indexShouldReturnJsonFor62YearOldMale() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/62-year-old-male.json");
    	
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("62")))
    	    	.andExpect(jsonPath("$.gender", is("male")))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(1))))
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1288569600000L))))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1255046400000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("moderate")))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(6)))) 
    	    	.andExpect(jsonPath("$.medications[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[1].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.medications[2].source", is("Community Health and Hospitals")))
				.andExpect(jsonPath("$.medications[3].source", is("Community Health and Hospitals")))
				.andExpect(jsonPath("$.medications[4].source", is("Community Health and Hospitals"))) 
				.andExpect(jsonPath("$.medications[5].source", is("Community Health and Hospitals")))
				.andExpect(jsonPath("$.labResultsGrid", hasSize(is(1)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("Community Health and Hospitals")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("Community Health and Hospitals")))
    	        ;
    }
    
    @Test
    public void indexShouldReturnJsonForUIHC_MyChartJohnDoe() throws Exception {
    	File file = new File("src/test/resources/bluebutton-json/john_doe_uihc_my_chart.json");
    	
    	mockMvc.perform(post("/blue-button/").content(FileUtils.readFileToString(file))
    	         .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    	    	.andExpect(status().isOk())
    	    	.andExpect(content().contentType("application/json"))
    	    	.andExpect(jsonPath("$.age", is("40")))
    	    	.andExpect(jsonPath("$.timeRanges.minTime", is(new Long(1280966400000L))))    	    	
    	    	.andExpect(jsonPath("$.timeRanges.maxTime", is(new Long(1404086400000L))))
    	    	.andExpect(jsonPath("$.metaData.allergies.severity", is("existing")))
    	    	.andExpect(jsonPath("$.metaData.encounters.recentEncounters", is("did not have any medical encounters")))
    	    	.andExpect(jsonPath("$.metaData.medications.recentMedications", is("not had any medications prescribed")))
    	    	.andExpect(jsonPath("$.demographics.combinedSourceList", is("University of Iowa Hospitals and Clinics (UIHC)")))
    	    	.andExpect(jsonPath("$.problems", hasSize(is(4))))
    	    	.andExpect(jsonPath("$.problems[0].source", is("University of Iowa Hospitals and Clinics (UIHC)")))
    	    	.andExpect(jsonPath("$.problems[0].subGrid[0].source", is("University of Iowa Hospitals and Clinics (UIHC)")))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(1))))
    	    	.andExpect(jsonPath("$.allergies[0].source", is("University of Iowa Hospitals and Clinics (UIHC)")))
    	    	.andExpect(jsonPath("$.allergies[0].subGrid[0].source", is("University of Iowa Hospitals and Clinics (UIHC)")))
    	    	.andExpect(jsonPath("$.medications", hasSize(is(0))))
    	    	.andExpect(jsonPath("$.immunizations", hasSize(is(0))))
    	    	.andExpect(jsonPath("$.procedures", hasSize(is(0))))
    	    	.andExpect(jsonPath("$.encounters", hasSize(is(0))))
    	    	.andExpect(jsonPath("$.allergies", hasSize(is(1))))
    	    	.andExpect(jsonPath("$.labResultsGrid", hasSize(is(11)))) 
    	    	.andExpect(jsonPath("$.labResultsGrid[0].source", is("University of Iowa Hospitals and Clinics (UIHC)")))
    	    	.andExpect(jsonPath("$.labResultsGrid[0].subGrid[0].source", is("University of Iowa Hospitals and Clinics (UIHC)")))
    	        ;
    }
    
}
