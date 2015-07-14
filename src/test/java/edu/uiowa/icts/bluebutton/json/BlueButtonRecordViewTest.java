package edu.uiowa.icts.bluebutton.json;

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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uiowa.icts.bluebutton.dao.BluebuttonDaoService;
import edu.uiowa.icts.bluebutton.json.view.AllergyMetaFinder;
import edu.uiowa.icts.bluebutton.json.view.BlueButtonRecordView;
import edu.uiowa.icts.bluebutton.json.view.FindLoincCodeList;
import edu.uiowa.icts.bluebutton.json.view.MetaData;
import edu.uiowa.icts.spring.AbstractSpringTestCase;

@WebAppConfiguration
public class BlueButtonRecordViewTest  extends AbstractSpringTestCase {
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");

	@Autowired
    private BluebuttonDaoService bluebuttonDaoService; 
	
	@Before
    public void setup() throws FileNotFoundException, IOException {
        // freeze time
        DateTime dt= format.parseDateTime("2015-02-04");
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
       
    }
    
    @After
    public void tearDown() throws Exception {
    	// unfreeze time
    	DateTimeUtils.setCurrentMillisSystem();
    	super.tearDown();
    }
        
	@Test
	public void jsonOutputShouldIncludeCorrectAge() throws JsonProcessingException {
		Demographics d = new Demographics();
		d.setDob("1987-01-20T06:00:00.000Z");
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setDemographics(d);
		assertEquals(28d, bbr.getDemographics().getAge());
		
	}
	
	@Test
	public void jsonOutputShouldIncludeGender() throws JsonProcessingException {
		Demographics d = new Demographics();
		d.setGender("female");		
		assertEquals("female", d.getGender());
		
	}
	
	@Test
	public void jsonOutputShouldIncludeMinAndMaxTimeWithOnlyEncountersTimes() throws JsonProcessingException{
		BlueButtonRecord bbr = new BlueButtonRecord();
		List<Encounter> encounters = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		e.setDate("2015-02-19T00:00:00.000Z");
		encounters.add(e);
		Encounter e2 = new Encounter();
		e2.setDate("2015-02-18T00:00:00.000Z");
		encounters.add(e2);
		Encounter e3 = new Encounter();
		e3.setDate("2015-02-17T00:00:00.000Z");
		encounters.add(e3);
		bbr.setEncounters(encounters);
		
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, new HashMap<String, LoincCode>());
		assertEquals(new Long(1424304000000L), bbrv.getTimeRanges().getMaxTime());
		assertEquals(new Long(1424131200000L), bbrv.getTimeRanges().getMinTime());
	}
	
	
	@Test
	public void jsonOutputShouldIncludeOneLabResultAndOneImmunization() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/blue_button_sample_with_everything.json");

		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();
		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, clcl.getCombinedHash());
		List<Immunization> immunizationsArray = bbrv.getImmunizations();
		assertEquals("Influenza, high dose seasonal", immunizationsArray.get(0).getName());
		assertTrue(immunizationsArray.get(0).getKeywords().size()>0);
		List<String> keywords = immunizationsArray.get(0).getKeywords();
		assertEquals(2, keywords.size());
		assertEquals("Influenza, high dose seasonal", keywords.get(0));
		assertEquals("2010-12-03", keywords.get(1));
		
		//Test timeRange calculator
		DateMinMaxCalculator dateRange = bbrv.getTimeRanges();
		assertEquals(new Long(1291334400000L), dateRange.getMaxTime());
		assertEquals(new Long(1266019200000L), dateRange.getMinTime());
		
		/*
		 * test lab results grid, all of these have LOINC codes!		
		 */
		
		/*
		 * test vitals grid, all of these vitals have LOINC codes!		
		 */
		List<VitalsResult> vList = bbrv.getVitalsGrid();
		assertEquals(3, vList.size());
		assertEquals(new String("BMI"), vList.get(0).getName());
		assertEquals(new Double(16.9),  vList.get(0).getValue());
		assertEquals("16.9kg/m2",  vList.get(0).getDisplayValue());
		assertEquals(new Long("1291334400000"),  vList.get(0).getDateInMillis());
		assertEquals("2010-12-03",vList.get(0).getDate());
		assertEquals("39156-5",vList.get(0).getLoincCode());
		assertNotNull(vList.get(0).getSubGrid());
		assertEquals(2, vList.get(0).getSubGrid().size());
		
		assertNotNull(vList.get(0).getKeywords());
		List<String> keywordsList = vList.get(0).getKeywords();
		assertEquals(4, keywordsList.size());
		assertEquals("BMI", keywordsList.get(0));
		assertEquals("2010-12-03", keywordsList.get(1));
		assertEquals("16.9kg/m2", keywordsList.get(2));
		assertEquals("39156-5", keywordsList.get(3));
		
		VitalsResult vr = vList.get(1);
		assertEquals("BP Diastolic", vr.getName());
		assertEquals(new Double(56), vr.getValue());
		assertEquals("56mm[Hg]", vr.getDisplayValue());
		assertEquals(new Long("1283817600000"), vr.getDateInMillis());
		assertEquals("2010-09-07", vr.getDate());
		assertEquals("8462-4", vr.getLoincCode());
		assertNotNull(vr.getSubGrid());
		List<VitalsResult> subV = vr.getSubGrid();
		assertEquals(2, subV.size());
		VitalsResult vr2 = vList.get(2);
		assertEquals("BP Systolic", vr2.getName());
		assertEquals(new Double(98), vr2.getValue());
		assertEquals("98mm[Hg]", vr2.getDisplayValue());
		assertEquals(new Long("1283817600000"), vr2.getDateInMillis());
		assertEquals("2010-09-07", vr2.getDate());
		assertEquals("8480-6",vr2.getLoincCode());
		assertNotNull(vr2.getSubGrid());
		subV = vr2.getSubGrid();
		assertEquals(2, subV.size());
		
		//Test allergies
		List<Allergy> aList = bbrv.getAllergies();  
		assertNotNull(aList);
		assertEquals(3,aList.size());
		Allergy a = aList.get(0);
		assertEquals("Food allergy (disorder)", a.getReaction_type().getName());
		assertEquals("Eggs", a.getName());

		a = aList.get(1);		
		assertEquals("Drug allergy (disorder)", a.getReaction_type().getName());
		assertEquals("Amikacin Sulfate 50 MG/ML Injectable Solution", a.getName());
		assertNotNull(a.getKeywords());
		keywords = a.getKeywords();
		assertEquals(3, keywords.size());
		assertEquals("2010-03-31", keywords.get(0));
		assertEquals("Amikacin Sulfate 50 MG/ML Injectable Solution", keywords.get(1));
		assertEquals("Drug allergy (disorder)", keywords.get(2));
		
		a = aList.get(2);		
		assertEquals("Drug allergy (disorder)", a.getReaction_type().getName());
		assertEquals("Codeine", a.getName());
		
		//Test allergy meta data 
		assertNotNull(bbrv.getMetaData());
		MetaData metaData = bbrv.getMetaData();
		assertNotNull(metaData.getAllergies());
		AllergyMetaFinder allergiesMeta = metaData.getAllergies();
		assertEquals("multiple", allergiesMeta.getSeverity());
		
		
	}
	
	@Test
	public void jsonOutputShouldIncludeThreeLabResultsInGrid() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/NIST_gov_data_out-of-range-data.json");
		
		ObjectMapper mapper = new ObjectMapper();
	
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();
		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, clcl.getCombinedHash());
		
		assertNotNull(bbrv.getLabResultsGrid());
		List<LabResult> labList = bbrv.getLabResultsGrid();
		assertEquals(3, labList.size());
		assertEquals("HGB", ( labList.get(0)).getName());
		assertEquals("WBC", ( labList.get(1)).getName());
		assertEquals("PLT", ( labList.get(2)).getName());
		
		/* 
		 * test medications
		 */
		assertNotNull(bbrv.getMedications());
		List<Medication> mList = bbrv.getMedications();
		assertEquals(1, mList.size());
		assertEquals("Proventil 0.09 MG/ACTUAT inhalant solution", mList.get(0).getName());
		Medication med = mList.get(0);
		assertNotNull(med.getKeywords());
		List<String> keywords = med.getKeywords();
		assertEquals(2, keywords.size());
		assertEquals("Proventil 0.09 MG/ACTUAT inhalant solution", keywords.get(0));
		assertEquals("2012-08-06", keywords.get(1));
		
	}
	
	@Test
	public void jsonOutputShouldIncludeGenderMale() throws JsonProcessingException {
		Demographics d = new Demographics();
		d.setGender("male");
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setDemographics(d);
		
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, new HashMap<String, LoincCode>());
		assertNotNull(bbrv);
		assertEquals("male", bbrv.getGender());
		
	}
	
	
	@Test
	public void jsonOutputShouldCreateJSONForJaneDoeAugust() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/jane-doe-august-2014.json");
		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, clcl.getCombinedHash());
		/*
		 * test labs grid, should still group labs without LOINC codes	
		 */
		assertNotNull(bbrv.getLabResultsGrid());
		List<LabResult> labsArray = bbrv.getLabResultsGrid();
		assertEquals(13, labsArray.size());
		
		
		//Test timeRange calculator
		DateMinMaxCalculator timeRanges = bbrv.getTimeRanges();
		assertEquals(new Long(1401840000000L), timeRanges.getMaxTime());
		assertEquals(new Long(1175472000000L), timeRanges.getMinTime());
		
		//Test allergy meta data 
		MetaData metaData =bbrv.getMetaData();
		AllergyMetaFinder amf = metaData.getAllergies();
		assertEquals("no", amf.getSeverity());
	}
	
	@Test
	public void jsonOutputShouldCreateJSONForJaneDoeJuneWithEndDateOfJuly11_2012() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/end_date_July_2012_jane-doe-june-2014.json");
		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		CombineBlueButtonRecords cbbr = new CombineBlueButtonRecords(records.getRecords(), records.getStartDateInMillis(), records.getEndDateInMillis(), this.bluebuttonDaoService.getLabResultSynonymService());
    	BlueButtonRecord bbr = cbbr.getMasterBlueButtonRecord();
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();
		
		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr,clcl.getCombinedHash());
		/*
		 * test vitals grid
		 */
		List<VitalsResult> vitalsArray = bbrv.getVitalsGrid();
		assertEquals(1, vitalsArray.size());
		/*
		 * test labs grid, should still group labs without LOINC codes	
		 */
		List<LabResult> labsArray = bbrv.getLabResultsGrid();
		assertEquals(22, labsArray.size());
		
	}
	
	@Test
	public void jsonOutputShouldCreateJSONForViteraWithMedsSubGrid() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/vitera_labs-out-of-range-allergies-meds.json");
		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, clcl.getCombinedHash());
		List<Medication> medicationsArray = bbrv.getMedications();
		assertEquals(2,medicationsArray.size());
		
		//Test encounters organization
		List<Encounter> encountersArray = bbrv.getEncounters();
		assertEquals(1,encountersArray.size());
		Encounter encountersJson = encountersArray.get(0);
		List<Encounter> subGrid = encountersJson.getSubGrid();
		assertEquals(13, subGrid.size());
		Encounter ejson = subGrid.get(4);
		assertEquals("Provider's Office", ejson.getOrganization());
		

	}
	
	@Test
	public void jsonOutputShouldCreateJSONFor71YearOldFemaleWithMedsSubGrid() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/71-year-old-data.json");
		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv =  new BlueButtonRecordView(bbr, clcl.getCombinedHash());
		List<Medication> mList = bbrv.getMedications();
		assertEquals(6,mList.size());
		
		Medication m = mList.get(1);
		
		//test subGrid
		List<Medication> subGrid = m.getSubGrid();
		assertEquals(2, subGrid.size());
		Medication subM = subGrid.get(0); 
		assertEquals("exenatide 0.25 MG/ML Injectable Solution", subM.getName());
		assertEquals("2010-10-24", subM.getStartDate());
		subM = subGrid.get(1);
		assertEquals("exenatide 0.25 MG/ML Injectable Solution", subM.getName());
		assertEquals("2010-03-31", subM.getStartDate());
		
		m = mList.get(2);
		
		//test subGrid
		subGrid = m.getSubGrid();
		assertEquals(2, subGrid.size());
		subM = subGrid.get(0);
		assertEquals("atorvastatin 10 MG Oral Tablet [Lipitor]", subM.getName());
		assertEquals("2010-10-24", subM.getStartDate());
		subM = subGrid.get(1);
		assertEquals("atorvastatin 10 MG Oral Tablet [Lipitor]", subM.getName());
		assertEquals("2010-03-31", subM.getStartDate());
		
		
		m = mList.get(3);
	}
	
	
	@Test
	public void jsonOutputShouldCreateJSONForJaneDoeJuneWithProcedures() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/end_date_July_2012_jane-doe-june-2014.json");
		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		CombineBlueButtonRecords cbbr = new CombineBlueButtonRecords(records.getRecords(), records.getStartDateInMillis(), records.getEndDateInMillis(), this.bluebuttonDaoService.getLabResultSynonymService());
    	BlueButtonRecord bbr = cbbr.getMasterBlueButtonRecord();
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

    	List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv = new BlueButtonRecordView( bbr,clcl.getCombinedHash());
		/*
		 * test procedures
		 */
		List<Procedure> pList = bbrv.getProcedures();
		assertEquals(3, pList.size());
		
		Procedure p = pList.get(0);
		assertEquals("2012-02-17", p.getDisplayDate());
		assertEquals(new Long(1329436800000L), p.getDateInMillis());
		List<String> keywords = p.getKeywords();
		assertEquals("2012-02-17", keywords.get(0));
		
		p = pList.get(1);
		assertEquals("2012-02-16", p.getDisplayDate());
		assertEquals(new Long(1329350400000L), p.getDateInMillis());
		
		p = pList.get(2);
		assertEquals("2011-08-10", p.getDisplayDate());
		assertEquals(new Long(1312934400000L), p.getDateInMillis());
		assertEquals("ECG IMPRESSION",p.getName());
		
		Demographics demo = bbrv.getDemographics();
		assertEquals("Jane Doe",demo.getName().getFullName());
		
		List<Problem> problems = bbrv.getProblems();
		Problem problem = problems.get(0);
		keywords = problem.getKeywords();
		assertEquals(2, keywords.size());
		assertEquals("2013-05-22", keywords.get(0));
		assertEquals("Tinnitus", keywords.get(1));
		
	}
	
	@Test
	public void jsonOutputShouldCreateJSONForJaneDoeJuneWithStartDateOfMay21_2013AndEndDateOfMay23_2013() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/start_and_end_date_May_21_2013_jane-doe-May_23_2013.json");
		ObjectMapper mapper = new ObjectMapper();
    	BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
    	CombineBlueButtonRecords cbbr = new CombineBlueButtonRecords(records.getRecords(), records.getStartDateInMillis(), records.getEndDateInMillis(), this.bluebuttonDaoService.getLabResultSynonymService());
    	BlueButtonRecord bbr = cbbr.getMasterBlueButtonRecord();
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, new HashMap<String, LoincCode>());
		
		/*
		 * test vitals grid
		 */
		List<VitalsResult> vitalsArray = bbrv.getVitalsGrid();
		assertEquals(1, vitalsArray.size());
		/*
		 * test labs grid, should still group labs without LOINC codes	
		 */
		List<LabResult> labsArray =bbrv.getLabResultsGrid();
		assertEquals(12, labsArray.size());
	}
	
	@Test
	public void jsonOutputShouldCreateJSONForJaneDoe() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/jane-doe-june-2014.json");
		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv = new BlueButtonRecordView(bbr, clcl.getCombinedHash());
		/*
		 * test labs grid, these labs have LOINC codes	
		 */
		
		List<LabResult> labsArray = bbrv.getLabResultsGrid();
		assertEquals(18, labsArray.size());		
		LabResult lr= labsArray.get(0);
		assertEquals("---GYN ORDER/REPORT PROCEDURE---", lr.getLabName());
		List<String> keywordList =lr.getKeywords();
		assertEquals(keywordList.get(0),"---GYN ORDER/REPORT PROCEDURE---");
		assertEquals(keywordList.get(1), "2014-05-28");
		
		/*
		 * test vitals grid, none of these vitals have LOINC codes!	
		 */
		List<VitalsResult> vitalsArray = bbrv.getVitalsGrid();
		assertEquals(0, vitalsArray.size());
		
	}
	
	/*
	 * test encounters
	 */
	@Test
	public void getJSONShouldReturnEncountersForJaneDoe() throws JsonParseException, JsonMappingException, IOException{
		File file = new File("src/test/resources/bluebutton-json/25-year-old-female.json");
		ObjectMapper mapper = new ObjectMapper();
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		List<LoincCode> list = this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(list, null);
		BlueButtonRecordView bbrv =  new BlueButtonRecordView(bbr,clcl.getCombinedHash());
		

		List<Encounter> encountersArray= bbrv.getEncounters();
		assertEquals(3, encountersArray.size());
		Encounter encounter = encountersArray.get(0);
		assertEquals("Multiple gestation", encounter.getName());
		assertEquals("2010-12-27", encounter.getDisplayDate());
		assertEquals("Pregnancy-related diagnosis", encounter.getFindings());
		assertEquals(3, encounter.getKeywords().size());
		assertEquals("2010-12-27",encounter.getKeywords().get(0));
		assertEquals("Multiple gestation",encounter.getKeywords().get(1));
		assertEquals("Pregnancy-related diagnosis",encounter.getKeywords().get(2));


		
		encounter = encountersArray.get(1);
		assertEquals("Encounter for supervision of normal pregnancy, unspecified", encounter.getName());
		assertEquals("2010-12-16", encounter.getDisplayDate());
		
		encounter = encountersArray.get(2);
		assertEquals("Routine general medical examination at a health care facility", encounter.getName());
		assertEquals("2010-06-15", encounter.getDisplayDate());
		assertEquals("Pregnancy", encounter.getFindings());

		
	}
	
	/* 
	 * get age
	 */
	@Test
	public void getAgeShouldReturn28() {
		Demographics d = new Demographics();
		d.setDob("1987-01-20T06:00:00.000Z");		
		assertEquals(new Double(28), d.getAge());
	}
	
//	@Test
//	public void getAgeShouldReturnNull() {
//		BlueButtonRecord bbr = new BlueButtonRecord();
//		assertNull(bbr.getAge());
//	}
	/*
	 * get gender
	 */
	@Test
	public void getGenderShouldReturnFemale() {
		Demographics d = new Demographics();
		d.setGender("female");		
		assertEquals("female", d.getGender());
	}
	
	@Test
	public void getGenderShouldReturnMale() {
		Demographics d = new Demographics();
		d.setGender("male");
			
		assertEquals("male",d.getGender());
	}
	
	@Test
	public void getGenderShouldReturnNull() {
		Demographics d = new Demographics();
		
		assertNull(d.getGender());
	}
	/* 
	 * test getLoincCodeCsvList
	 */
	@Test
	public void getLoincCodeCsvListShouldReturnThreeVitalsAndOneLab() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/blue_button_sample_with_everything.json");
		
		ObjectMapper mapper = new ObjectMapper();	
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		assertEquals("8462-4,8480-6,39156-5,31971-5", csvLonicCodeList);
	}
	
	@Test
	public void getLoincCodeCsvListShouldReturnZeroVitalsAndThreeLabs() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/NIST_gov_data_out-of-range-data.json");
		
		ObjectMapper mapper = new ObjectMapper();	
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		assertEquals("30313-1,33765-9,26515-7", csvLonicCodeList);
	}
	
	@Test
	public void getLoincCodeCsvListShouldReturnZeroVitalsBecauseNoLoincCodesAndNotIncludeLabResultsWithoutLoincCodes() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/test/resources/bluebutton-json/jane-doe-june-2014.json");
		
		ObjectMapper mapper = new ObjectMapper();	
		BlueButtonRecords records = mapper.readValue(FileUtils.readFileToString(file), BlueButtonRecords.class);
		BlueButtonRecord bbr = records.getRecords().get(0);
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();

		assertEquals("4548-4,27353-2,6690-2,789-8,718-7,4544-3,787-2,785-6,786-4,777-3,788-0,771-6,12189-7,10535-3,29637-6,13969-1,2157-6,2823-3,43396-1,2085-9,2093-3,890-4,20438-8,2345-7,8014-3,11579-0", csvLonicCodeList);
	}
	
	@Test
	public void getDataSourceShouldReturnValue() {
		Provider p = new Provider();
		p.setOrganization("Get Well Clinic");
		Demographics d = new Demographics();
		d.setProvider(p);
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setDemographics(d);

		assertEquals("Get Well Clinic", bbr.getSource());
	}
	
	@Test
	public void getDataSourceShouldReturnValueOfNullWhenNoData() {
		Provider p = new Provider();
		Demographics d = new Demographics();
		d.setProvider(p);
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setDemographics(d);

		assertNull(bbr.getSource());
	}
	
}
