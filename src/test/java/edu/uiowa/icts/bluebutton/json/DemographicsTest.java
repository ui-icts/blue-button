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

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class DemographicsTest {

	
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	@Before
    public void setup() {
        DateTime dt= format.parseDateTime("2015-02-04");
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
    }
    
    @After
    public void tearDown() throws Exception {
    	DateTimeUtils.setCurrentMillisSystem();
    }
	
	@Test
	public void ageShouldReturn28() {
		Demographics d = new Demographics();
		d.setDob("1987-01-20T06:00:00.000Z");
		
		assertEquals(new Double(28), d.getAge());
	}
	
	@Test
	public void ageShouldReturn71() {
		Demographics d = new Demographics();
		d.setDob("1943-09-03T05:00:00.000Z");
		
		assertEquals(new Double(71), d.getAge());
	}
	
	@Test
	public void ageShouldReturn5() {
		Demographics d = new Demographics();
		d.setDob("2009-02-11T06:00:00.000Z");
		
		assertEquals(new Double(5), d.getAge());
	}
	
	@Test
	public void ageShouldReturn10() {
		Demographics d = new Demographics();
		d.setDob("2004-11-02T06:00:00.000Z");
		
		assertEquals(new Double(10), d.getAge());
	}
	
	@Test
	public void ageShouldReturn76() {
		Demographics d = new Demographics();
		d.setDob("1938-12-12T06:00:00.000Z");
		
		assertEquals(new Double(76), d.getAge());
	}
	
	@Test
	public void ageShouldReturn67() {
		Demographics d = new Demographics();
		d.setDob("1947-05-01T05:00:00.000Z");
		
		assertEquals(new Double(67), d.getAge());
	}
	
	@Test
	public void ageShouldReturnNegativeOne() {
		Demographics d = new Demographics();
		
		assertEquals(new Double(-1), d.getAge());
	}
	
	@Test
	public void ageShouldReturn62() {
		Demographics d = new Demographics();
		d.setDob("1952-05-21T05:00:00.000Z");
		
		assertEquals(new Double(62), d.getAge());
	}
	
	@Test
	public void genderMaleShouldReturnHe(){
		Demographics d = new Demographics();
		d.setGender("male");
		assertEquals("He", d.getHeSheGenderedPronoun());
	}
	
	@Test
	public void genderFemaleCapaitalizedShouldReturnShe(){
		Demographics d = new Demographics();
		d.setGender("Female");
		assertEquals("She", d.getHeSheGenderedPronoun());
	}
	
	@Test
	public void genderUnknownShouldReturnVoidGenderedPronoun(){
		Demographics d = new Demographics();
		d.setGender("Unknown");
		assertNull(d.getHeSheGenderedPronoun());
	}
	
	@Test
	public void nullGenderShouldReturnNullGenderedPronoun(){
		Demographics d = new Demographics();
		assertNull(d.getHeSheGenderedPronoun());
	}
	
	@Test
	public void dataSourceShouldReturnProviderOrginization(){
		Demographics d = new Demographics();
		Provider p = new Provider();
		p.setOrganization("Madison Medical Center P. A.");
		d.setProvider(p);
		assertEquals("Madison Medical Center P. A.", d.getDataSource());
	}
	
	@Test
	public void dataSourceShouldReturnUnknownWithNoProviderOrganization(){
		Demographics d = new Demographics();
		assertEquals("unknown", d.getDataSource());
	}
}
