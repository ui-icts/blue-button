package edu.uiowa.icts.bluebutton.json.view;

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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.uiowa.icts.bluebutton.json.Allergy;
import edu.uiowa.icts.bluebutton.json.view.AllergyMetaFinder;

public class AllergyMetaFinderTest {

	@Test
	public void allergyMetaFinderShouldReturnServerityInformation() {
		List<Allergy> list = new ArrayList<Allergy>();
		Allergy a = new Allergy();;
		a.setSeverity("Severe");
		a.setStatus("Active");
		list.add(a);
		
		AllergyMetaFinder amf = new AllergyMetaFinder(list);
		assertEquals("severe",amf.getSeverity());
		
	}
	
	@Test
	public void allergyMetaFinderShouldReturnMultipleServerityInformation() {
		List<Allergy> list = new ArrayList<Allergy>();
		Allergy a = new Allergy();;
		a.setSeverity("Severe");
		a.setStatus("Active");
		list.add(a);
		
		Allergy a2 = new Allergy();;
		a2.setSeverity("Severe");
		a2.setStatus("Active");
		list.add(a2);
		
		AllergyMetaFinder amf = new AllergyMetaFinder(list);
		assertEquals("multiple severe",amf.getSeverity());
	}
	
	@Test
	public void allergyMetaFinderShouldReturnTheCorrectSeverityLevel() {
		List<Allergy> list = new ArrayList<Allergy>();
		Allergy a = new Allergy();;
		a.setSeverity("Mild");
		list.add(a);
		
		Allergy a2 = new Allergy();;
		a2.setSeverity("Moderate");
		list.add(a2);
		
		AllergyMetaFinder amf = new AllergyMetaFinder(list);
		assertEquals("moderate",amf.getSeverity());
	}
	
	@Test
	public void allergyMetaFinderShouldReturnTheCorrectSeverityLevelWithMultiple() {
		List<Allergy> list = new ArrayList<Allergy>();
		Allergy a = new Allergy();;
		a.setSeverity("Mild");
		list.add(a);
		
		Allergy a2 = new Allergy();;
		a2.setSeverity("Moderate");
		list.add(a2);
		
		Allergy a3 = new Allergy();;
		a3.setSeverity("Mild");
		list.add(a3);
		
		AllergyMetaFinder amf = new AllergyMetaFinder(list);
		assertEquals("moderate",amf.getSeverity());
	}
	
	@Test
	public void allergyMetaFinderShouldReturnTheCorrectSeverityLevelWithNoSeverity() {
		List<Allergy> list = new ArrayList<Allergy>();
		Allergy a = new Allergy();;
		a.setSeverity("Mild");
		list.add(a);
		
		Allergy a2 = new Allergy();;
		a2.setSeverity("Moderate");
		list.add(a2);
		
		Allergy a3 = new Allergy();;
		a3.setSeverity("Moderate");
		list.add(a3);
		
		AllergyMetaFinder amf = new AllergyMetaFinder(list);;
		assertEquals("multiple moderate",amf.getSeverity());
	}
	
	@Test
	public void allergyMetaFinderShouldReturnCorrectSeverityWithLowerCaseSeverity(){
		List<Allergy> list = new ArrayList<Allergy>();
		Allergy a = new Allergy();;
		a.setSeverity("mild");
		list.add(a);
		
		Allergy a2 = new Allergy();;
		a2.setSeverity("moderate");
		list.add(a2);
		
		Allergy a3 = new Allergy();;
		a3.setSeverity("moderate");
		list.add(a3);
		
		AllergyMetaFinder amf = new AllergyMetaFinder(list);;
		assertEquals("multiple moderate",amf.getSeverity());
	}
	
	
	@Test
	public void allergyMetaFinderShouldReturnTheCorrectSeverityLevelWithMultiple2() {
		List<Allergy> list = new ArrayList<Allergy>();
		Allergy a = new Allergy();;
		list.add(a);
		Allergy a2 = new Allergy();;
		list.add(a2);
		Allergy a3 = new Allergy();;
		list.add(a3);
	
		AllergyMetaFinder amf = new AllergyMetaFinder(list);
		assertEquals("multiple",amf.getSeverity());
	}
	
}
