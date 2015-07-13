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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class DateMinMaxCalculatorTest {

	
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	@Test
	public void getMinMaxShouldNotExistWithEmptyList() {
	
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertNull(list.getMaxTime());
		assertNull(list.getMinTime());
	}
	@Test
	public void getMinMaxShouldNotExistWithNullList() {
	
		DateMinMaxCalculator list =  new DateMinMaxCalculator(null);
		assertNull(list.getMaxTime());
		assertNull(list.getMinTime());
	}
	
	@Test
	public void encountersEntryListShouldReturnJSONWithMaxTime() {
		Encounter e = new Encounter();
		e.setDate("2015-02-19T00:00:00.000Z");
		Encounter e2= new Encounter();
		e2.setDate("2015-02-18T00:00:00.000Z");
		Encounter e3= new Encounter();
		e3.setDate("2015-02-17T00:00:00.000Z");
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e); eList.add(e2); eList.add(e3);
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424152800000L), list.getMinTime());
	}
	
	@Test
	public void encountersEntryListShouldReturnJSONWithMaxTimeWithEncountersWithoutDate() {
		Encounter e = new Encounter();
		e.setDate("2015-02-19T00:00:00.000Z");
		Encounter e2= new Encounter();
		e2.setDate("2015-02-18T00:00:00.000Z");
		Encounter e3= new Encounter();
		e3.setDate("2015-02-17T00:00:00.000Z");
		Encounter e4= new Encounter();
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e); eList.add(e2); eList.add(e3); eList.add(e4);
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424152800000L), list.getMinTime());
	}
	
	@Test
	public void shouldReturnJSONWithMaxTimeWithEntriesWithBothStartDateInMillisAndDatInMillis() {
		Entry e = new Entry();
		e.setDate("2015-02-19T00:00:00.000Z");
		Entry e2= new Entry();
		e2.setDate("2015-02-18T00:00:00.000Z");
		Entry e3= new Entry();
		e3.date_range.setStart("2015-02-17T00:00:00.000Z");
		e3.date_range.setEnd("2015-02-18T00:00:00.000Z");
		Entry e4= new Entry();
		e4.date_range.setStart("2015-02-17T00:00:00.000Z");
		e4.date_range.setEnd("2015-02-18T00:00:00.000Z");
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e); eList.add(e2); eList.add(e3); eList.add(e4);
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424152800000L), list.getMinTime());
	}
	
	@Test
	public void shouldReturnJSONWithMaxTimeWithEntriesWithAllTheSameEndDates() {
		Entry e = new Entry();
		e.setDate("2015-02-19T00:00:00.000Z");
		Entry e2= new Entry();
		e2.setDate("2015-02-19T00:00:00.000Z");
		Entry e3= new Entry();
		e3.date_range.setStart("2015-02-19T00:00:00.000Z");
		e3.date_range.setEnd("2015-02-19T00:00:00.000Z");
		Entry e4 = new Entry();
		e4.setDate("2015-02-19T00:00:00.000Z");
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e); eList.add(e2); eList.add(e3); eList.add(e4);
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424325600000L), list.getMinTime());
	}
	
	@Test
	public void shouldReturnJSONWithMaxTimeWithEntriesWithEqualEndDates() {
		Entry e = new Entry();
		e.setDate("2015-02-19T00:00:00.000Z");
		Entry e2= new Entry();
		e2.setDate("2015-02-18T00:00:00.000Z");
		Entry e3= new Entry();
		e3.date_range.setStart("2015-02-17T00:00:00.000Z");
		e3.date_range.setEnd("2015-02-19T00:00:00.000Z");
		Entry e4 = new Entry();
		e4.setDate("2015-02-19T00:00:00.000Z");
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e); eList.add(e2); eList.add(e3); eList.add(e4);
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424152800000L), list.getMinTime());
	}
	@Test
	public void shouldReturnJSONWithMaxTimeWithEntriesWithOnlyStartDate() {
		
		Entry e3= new Entry();
		e3.date_range.setStart("2015-02-19T00:00:00.000Z");
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e3);
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424325600000L), list.getMinTime());
	}
	
	@Test
	public void shouldReturnJSONWithMaxTimeWithEntriesWithOnlyEndDate() {
		
		Entry e3= new Entry();
		e3.date_range.setEnd("2015-02-19T00:00:00.000Z");
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e3);
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);
		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424325600000L), list.getMinTime());
	}
	
	@Test
	public void shouldReturnJSONWithMaxTimeWithEntriesWithEqualEndDatesWithNaturalOrder() {
		Entry e = new Entry();
		e.setDate("2015-02-19T00:00:00.000Z");
		Entry e2= new Entry();
		e2.setDate("2015-02-18T00:00:00.000Z");
		Entry e3= new Entry();
		e3.setDate("2015-02-17T00:00:00.000Z");
		
		List<IDateInMillis> eList = new ArrayList<IDateInMillis>();
		eList.add(e); eList.add(e2); eList.add(e3); 
		DateMinMaxCalculator list =  new DateMinMaxCalculator(eList);

		assertEquals(new Long(1424325600000L), list.getMaxTime());
		assertEquals(new Long(1424152800000L), list.getMinTime());
	}
}
