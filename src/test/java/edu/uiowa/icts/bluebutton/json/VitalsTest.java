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

import org.junit.Test;

public class VitalsTest {

	/*
	 * get date in millis
	 */
	@Test
	public void getDateInMillisShouldReturnNullWhenNoDatePresent() {
		Vitals v = new Vitals();
		assertNull(v.getDateInMillis());
	}
	
	@Test
	public void getDateInMillisShouldReturnTimeInMillis() {
		Vitals v = new Vitals();
		v.setDate("1952-05-21T05:00:00.000Z");
		assertEquals(new Long(-555861600000L), v.getDateInMillis());
	}
	
	@Test
	public void getDateInMillisShouldReturnTimeInMillNegativeNumber() {
		Vitals v = new Vitals();
		v.setDate("1989-05-02T05:00:00.000Z");
		assertEquals(new Long(610106400000L),v.getDateInMillis());
	}
	
	@Test
	public void getDateInMillisShouldReturnTimeInMillOddTimeReturn() {
		Vitals v = new Vitals();
		v.setDate("2009-02-11T06:00:00.000Z");
		assertEquals(new Long(1234353600000L),v.getDateInMillis());
	}
	/*
	 * get display date
	 */
	@Test
	public void displayDateShouldReturnFormattedDate() {
		Vitals v = new Vitals();
		v.setDate("1989-05-02T05:00:00.000Z");
		
		assertEquals("1989-05-02", v.getDisplayDate());
	}
	@Test
	public void displayDateShouldReturnEmtpyString() {
		Vitals v = new Vitals();		
		assertNull(v.getDisplayDate());
	}
}
