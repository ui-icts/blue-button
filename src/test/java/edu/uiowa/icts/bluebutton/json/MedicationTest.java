//package edu.uiowa.icts.bluebutton.json;

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
//
//import static org.junit.Assert.*;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeUtils;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
//import org.json.JSONObject;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class MedicationTest {
//	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
//
//	@Before
//    public void setup() throws FileNotFoundException, IOException {
//        // freeze time
//        DateTime dt= format.parseDateTime("2015-02-04");
//        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//    	// unfreeze time
//    	DateTimeUtils.setCurrentMillisSystem();
//    }
//    
//	@Test
//	public void getJsonShouldReturnNullName() {
//		Medication m = new Medication();
//		
//		JSONObject jo = m.getJson();
//		
//		assertFalse(jo.has("medicationName"));
//	}
//
//	@Test
//	public void getJsonShouldReturnProductName() {
//		Product p = new Product();
//		p.setName("product name");
//		
//		Medication m = new Medication();
//		m.setProduct(p);
//		
//		JSONObject jo = m.getJson();
//		
//		assertEquals("product name", jo.getString("medicationName"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnTranslationName() {
//		Entry t = new Entry();
//		t.setName("translation name");
//		
//		Product p = new Product();
//		p.setName("product name");
//		p.setTranslation(t);
//		
//		Medication m = new Medication();
//		m.setProduct(p);
//		
//		JSONObject jo = m.getJson();
//		
//		assertEquals("translation name", jo.getString("medicationName"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnDateInMillisAndDisplayDates() {
//		DateRange dr = new DateRange();
//		dr.setStart("1989-05-02T05:00:00.000Z");
//		dr.setEnd("1990-05-02T05:00:00.000Z");
//		
//		Medication m = new Medication();
//		m.setDate_range(dr);
//		
//		JSONObject json = m.getJson();
//		assertEquals(new Long("610106400000"),(Long) json.getLong("dateInMillis"));
//		assertEquals("1989-05-02", json.get("startDate"));
//		assertEquals("1990-05-02", json.get("endDate"));
//		assertEquals("No", json.get("current"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnNullForAllFields() {
//		Medication m = new Medication();
//		
//		JSONObject json = m.getJson();
//		assertFalse(json.has("dateInMillis"));
//		assertFalse(json.has("startDate"));
//		assertFalse(json.has("endDate"));
//		assertFalse(json.has("dose"));
//		assertFalse(json.has("rate"));
//		assertFalse(json.has("current"));
//		assertFalse(json.has("schedule"));
//		assertFalse(json.has("precondition"));
//		assertFalse(json.has("reason"));
//		assertFalse(json.has("route"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnDoseQuantity() {
//		Quantity dq = new Quantity();
//		dq.setUnit("lbs");
//		dq.setValue("100");
//		
//		Medication m = new Medication();
//		m.setDose_quantity(dq);
//		
//		JSONObject json = m.getJson();
//		assertEquals("100lbs", json.get("dose"));
//	}
//	
//	
//	@Test
//	public void getJsonShouldReturnRateQuantity() {
//		Quantity dq = new Quantity();
//		dq.setUnit("lbs");
//		dq.setValue("100");
//		
//		Medication m = new Medication();
//		m.setRate_quantity(dq);
//		
//		JSONObject json = m.getJson();
//		assertEquals("100lbs", json.get("rate"));
//	}
//	
//	@Test
//	public void isActiveInTheLastYearForFullDateTimeShouldReturnTrueBecauseStartDateIsExactlyOneYear() {
//		DateRange dr = new DateRange();
//		dr.setStart("2014-02-05T05:00:00.000Z");
//		
//		Medication m = new Medication();
//		m.setDate_range(dr);
//		
//		JSONObject json = m.getJson();
//		assertEquals("Yes", json.get("current"));
//	}
//	
//	@Test
//	public void isActiveInTheLastYearForFullDateTimeShouldReturnFalseBecauseStartDateIsExactlyOneYearPlusOneDay() {
//		DateRange dr = new DateRange();
//		dr.setStart("2014-02-04T00:00:00.000Z");
//		
//		Medication m = new Medication();
//		m.setDate_range(dr);
//		
//		JSONObject json = m.getJson();
//		assertEquals("No", json.get("current"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnSchedule() {
//		Schedule s = new Schedule();
//		s.setType("frequency");
//		s.setPeriod_value("12");
//		s.setPeriod_unit("hours");
//		
//		Medication m = new Medication();
//		m.setSchedule(s);
//		
//		JSONObject json = m.getJson();
//		assertEquals("frequency: 12hours", json.get("schedule"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnPrecondition() {
//		Entry e = new Entry();
//		e.setName("asthma");
//		
//		Medication m = new Medication();
//		m.setPrecondition(e);
//		
//		JSONObject json = m.getJson();
//		assertEquals("asthma", json.get("precondition"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnReason() {
//		Entry e = new Entry();
//		e.setName("depression");
//		
//		Medication m = new Medication();
//		m.setReason(e);
//		
//		JSONObject json = m.getJson();
//		assertEquals("depression", json.get("reason"));
//	}
//	
//	@Test
//	public void getJsonShouldReturnRoute() {
//		Entry e = new Entry();
//		e.setName("mouth");
//		
//		Medication m = new Medication();
//		m.setRoute(e);
//		
//		JSONObject json = m.getJson();
//		assertEquals("mouth", json.get("route"));
//	}
//	
//}
