package edu.uiowa.icts.bluebutton.dao;

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
import java.util.HashMap;
import java.util.List;

import edu.uiowa.icts.bluebutton.domain.LabTest;
import edu.uiowa.icts.bluebutton.json.CombinedLoincCodeList;
import edu.uiowa.icts.bluebutton.json.LoincCode;
import edu.uiowa.icts.spring.*;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit test Template
 * Generated by Protogen
 * @since 10/29/2014 09:18:02 CDT
 */
@WebAppConfiguration
public class LabTestTest extends AbstractSpringTestCase {

    @Autowired
    private BluebuttonDaoService bluebuttonDaoService;

    @Test
    public void testServiceName() {
    	assertEquals( true, true );
    }

    @Before
    public void setUp() throws Exception {
    	super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    	super.tearDown();
    }

    @Test
    public void CSVImportShouldLoadLabTests() throws IOException {
    	assertEquals( 0, this.bluebuttonDaoService.getLabTestService().count() );
    	
    	File file = new File("src/test/resources/csv/lab-tests.csv");
		this.bluebuttonDaoService.getLabTestService().importCSV(new FileInputStream(file));
		
    	assertEquals( 11, this.bluebuttonDaoService.getLabTestService().count() );
    	
    	LabTest labTest = this.bluebuttonDaoService.getLabTestService().findById(1);
    	assertEquals("Porphobilinogen,Spot Urine", labTest.getName());
    	assertEquals("Porphobilinogen,Spot Urine", labTest.getDescription());
    	assertEquals("Positive",labTest.getUnits());
    	assertEquals("2809-2",labTest.getLoincCode());
    	

    }
    
    @Test
    public void getRangesByGenderAndAgeShouldReturnLoincCodesWithMinNormalAndMaxNormal() throws IOException {
   	    assertEquals( 0, this.bluebuttonDaoService.getLabTestService().count() );
    	File file = new File("src/test/resources/csv/lab-tests.csv");
		this.bluebuttonDaoService.getLabTestService().importCSV(new FileInputStream(file));
    	assertEquals( 11, this.bluebuttonDaoService.getLabTestService().count() );
    	
    	assertEquals( 0, this.bluebuttonDaoService.getLabTestRangeService().count() );
    	File file2 = new File("src/test/resources/csv/lab-test-ranges.csv");
		this.bluebuttonDaoService.getLabTestRangeService().importCSV(new FileInputStream(file2));
    	assertEquals( 30, this.bluebuttonDaoService.getLabTestRangeService().count() );
    	
    	List<LoincCode> listFemaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge("feMaLe", new Double(27));
    	List<LoincCode> listFemaleAge15 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge("FEmaLe", new Double(15));
    	List<LoincCode> listMaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge("mALe", new Double(27));
    	List<LoincCode> listMaleAge15 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge("MalE", new Double(15));
    	List<LoincCode> listEmptySex = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge("", new Double(27));
    	List<LoincCode> listNullSex = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge(null, new Double(27));
    	List<LoincCode> listNullAge = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge("feMaLe", null);
    	List<LoincCode> listBothEmpty = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAndAge("", null);

    	HashMap<String, LoincCode> hashFemaleAge27 = new CombinedLoincCodeList(null, listFemaleAge27).getCombinedHash();
    	HashMap<String, LoincCode> hashFemaleAge15 = new CombinedLoincCodeList(null, listFemaleAge15).getCombinedHash();
    	HashMap<String, LoincCode> hashMaleAge27 = new CombinedLoincCodeList(null, listMaleAge27).getCombinedHash();
    	HashMap<String, LoincCode> hashMaleAge15 = new CombinedLoincCodeList(null, listMaleAge15).getCombinedHash();
    	HashMap<String, LoincCode> hashEmptySex = new CombinedLoincCodeList(null, listEmptySex).getCombinedHash();
    	HashMap<String, LoincCode> hashNullSex = new CombinedLoincCodeList(null, listNullSex).getCombinedHash();
    	HashMap<String, LoincCode> hashNullAge = new CombinedLoincCodeList(null, listNullAge).getCombinedHash();
    	HashMap<String, LoincCode> hashBothEmpty = new CombinedLoincCodeList(null, listBothEmpty).getCombinedHash();
    	
    	
    	assertNotNull(listEmptySex);
    	assertNotNull(listNullSex);
    	assertNotNull(listNullAge);
    	assertNotNull(listBothEmpty);

    	String loincCode = "2809-2";
    	HashMap<String, LoincCode> testHash = hashFemaleAge27;
    	// test mixed case gender and age 27 insignificant
    	assertEquals(0.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(0.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge27;
    	assertEquals(0.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(0.0, testHash.get(loincCode).getMax_normal());
    	
    	// test only data for men, female null
    	loincCode = "C1046-2";
    	assertEquals(false, hashFemaleAge27.containsKey(loincCode));
    	assertEquals(0.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(0.0, testHash.get(loincCode).getMax_normal());
    	
    	// test age matters
    	loincCode = "769-0";
    	testHash = hashFemaleAge27;
    	assertEquals(38.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(71.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge27;
    	assertEquals(38.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(71.0, testHash.get(loincCode).getMax_normal());
    	
    	testHash = hashFemaleAge15;
    	assertEquals(37.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(73.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge15;
    	assertEquals(37.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(73.0, testHash.get(loincCode).getMax_normal());
    	
    	// test different values based on sex 
    	loincCode = "718-7";
    	testHash = hashFemaleAge15;
    	assertEquals(12.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(16.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge15;
    	assertEquals(14.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(18.0, testHash.get(loincCode).getMax_normal());
    	
    	/* 
    	 * test relevant loinc_code list passed in 
    	 */
    	String loincCodeCsvList = "2809-2,C1046-2,769-0,718-7";
    	listFemaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("feMaLe", new Double(27), loincCodeCsvList);
    	listFemaleAge15 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("FEmaLe", new Double(15), loincCodeCsvList);
    	listMaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("mALe", new Double(27), loincCodeCsvList);
    	listMaleAge15 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("MalE", new Double(15), loincCodeCsvList);
    	listEmptySex = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("", new Double(27), loincCodeCsvList);
    	listNullSex = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes(null, new Double(27), loincCodeCsvList);
    	listNullAge = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("feMaLe", null, loincCodeCsvList);
    	listBothEmpty = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("", null, loincCodeCsvList);

    	hashFemaleAge27 = new CombinedLoincCodeList(null, listFemaleAge27).getCombinedHash();
    	hashFemaleAge15 = new CombinedLoincCodeList(null, listFemaleAge15).getCombinedHash();
    	hashMaleAge27 = new CombinedLoincCodeList(null, listMaleAge27).getCombinedHash();
    	hashMaleAge15 = new CombinedLoincCodeList(null, listMaleAge15).getCombinedHash();
    	hashEmptySex = new CombinedLoincCodeList(null, listEmptySex).getCombinedHash();
    	hashNullSex = new CombinedLoincCodeList(null, listNullSex).getCombinedHash();
    	hashNullAge = new CombinedLoincCodeList(null, listNullAge).getCombinedHash();
    	hashBothEmpty = new CombinedLoincCodeList(null, listBothEmpty).getCombinedHash();   	
    	
    	assertNotNull(listEmptySex);
    	assertNotNull(listNullSex);
    	assertNotNull(listNullAge);
    	assertNotNull(listBothEmpty);

    	// test mixed case gender and age 27 insignificant
    	loincCode = "2809-2";
    	testHash = hashFemaleAge27;
    	assertEquals(0.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(0.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge27;
    	assertEquals(0.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(0.0, testHash.get(loincCode).getMax_normal());
    	
    	// test only data for men, female null
    	loincCode = "C1046-2";
    	assertEquals(false, hashFemaleAge27.containsKey(loincCode));
    	assertEquals(0.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(0.0, testHash.get(loincCode).getMax_normal());
    	
    	// test age matters
    	loincCode = "769-0";
    	testHash = hashFemaleAge27;
    	assertEquals(38.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(71.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge27;
    	assertEquals(38.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(71.0, testHash.get(loincCode).getMax_normal());
    	
    	testHash = hashFemaleAge15;
    	assertEquals(37.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(73.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge15;
    	assertEquals(37.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(73.0, testHash.get(loincCode).getMax_normal());
    	
    	// test different values based on sex 
    	loincCode = "718-7";
    	testHash = hashFemaleAge15;
    	assertEquals(12.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(16.0, testHash.get(loincCode).getMax_normal());
    	testHash = hashMaleAge15;
    	assertEquals(14.0, testHash.get(loincCode).getMin_normal());
    	assertEquals(18.0, testHash.get(loincCode).getMax_normal());

    	/* 
    	 * test loinc_code list passed in does not include needed loinc codes so empty results be returned
    	 */
    	loincCodeCsvList = "LP31755-9,13969-1,14976-5,3936-2"; // random but real loinc codes
    	listFemaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("feMaLe", new Double(27), loincCodeCsvList);
    	listFemaleAge15 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("FEmaLe", new Double(15), loincCodeCsvList);
    	listMaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("mALe", new Double(27), loincCodeCsvList);
    	listMaleAge15 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("MalE", new Double(15), loincCodeCsvList);
    	listEmptySex = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("", new Double(27), loincCodeCsvList);
    	listNullSex = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes(null, new Double(27), loincCodeCsvList);
    	listNullAge = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("feMaLe", null, loincCodeCsvList);
    	listBothEmpty = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("", null, loincCodeCsvList);

    	hashFemaleAge27 = new CombinedLoincCodeList(null, listFemaleAge27).getCombinedHash();
    	hashFemaleAge15 = new CombinedLoincCodeList(null, listFemaleAge15).getCombinedHash();
    	hashMaleAge27 = new CombinedLoincCodeList(null, listMaleAge27).getCombinedHash();
    	hashMaleAge15 = new CombinedLoincCodeList(null, listMaleAge15).getCombinedHash();
    	hashEmptySex = new CombinedLoincCodeList(null, listEmptySex).getCombinedHash();
    	hashNullSex = new CombinedLoincCodeList(null, listNullSex).getCombinedHash();
    	hashNullAge = new CombinedLoincCodeList(null, listNullAge).getCombinedHash();
    	hashBothEmpty = new CombinedLoincCodeList(null, listBothEmpty).getCombinedHash();
    	
    	assertNotNull(listEmptySex);
    	assertNotNull(listNullSex);
    	assertNotNull(listNullAge);
    	assertNotNull(listBothEmpty);

    	// test mixed case gender and age 27 insignificant
    	loincCode = "2809-2";
    	assertEquals(false, hashFemaleAge27.containsKey(loincCode));
    	assertEquals(false, hashMaleAge27.containsKey(loincCode));
    	
    	// test only data for men, female null
    	loincCode = "C1046-2";
    	assertEquals(false, hashFemaleAge27.containsKey(loincCode));
    	assertEquals(false, hashMaleAge27.containsKey(loincCode));
    	
    	// test age matters
    	loincCode = "769-0";
    	assertEquals(false, hashFemaleAge27.containsKey(loincCode));
    	assertEquals(false, hashMaleAge27.containsKey(loincCode));
    	
    	assertEquals(false, hashFemaleAge15.containsKey(loincCode));
    	assertEquals(false, hashMaleAge15.containsKey(loincCode));
    	
    	// test different values based on sex 
    	loincCode = "718-7";
    	assertEquals(false, hashFemaleAge15.containsKey(loincCode));
    	assertEquals(false, hashMaleAge15.containsKey(loincCode));
    	
    	/* 
    	 * test loinc_code with extra comma 
    	 */
    	loincCodeCsvList = "2809-2,C1046-2,769-0,718-7,";
    	listFemaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("feMaLe", new Double(27), loincCodeCsvList);
    	hashFemaleAge27 = new CombinedLoincCodeList(null, listFemaleAge27).getCombinedHash();

    	assertEquals(0.0, hashFemaleAge27.get("2809-2").getMin_normal());
    	
    	/* 
    	 * test loinc_code list is empty
    	 */
    	loincCodeCsvList = ""; // random but real loinc codes
    	listFemaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("feMaLe", new Double(27), loincCodeCsvList);
    	hashFemaleAge27 = new CombinedLoincCodeList(null, listFemaleAge27).getCombinedHash();
    	assertEquals(false, (hashFemaleAge27.containsKey("2809-2")));
    	
    	/* 
    	 * test loinc_code list is empty with comma and spaces
    	 */
    	loincCodeCsvList = " ,  "; // random but real loinc codes
    	listFemaleAge27 = this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes("feMaLe", new Double(27), loincCodeCsvList);
    	hashFemaleAge27 = new CombinedLoincCodeList(null, listFemaleAge27).getCombinedHash();
    	assertEquals(false, (hashFemaleAge27.containsKey("2809-2")));
    	
    }
}