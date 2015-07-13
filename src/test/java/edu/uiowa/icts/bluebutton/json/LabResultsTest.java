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

import java.util.List;

import org.json.JSONArray;
import org.junit.Test;

public class LabResultsTest {	
	
	@Test
	public void getLoincCodeShouldReturnCodeIfCodeSystemIsLoinc() {
		LabResult l = new LabResult();
		l.setCode_system_name("LOINC");
		l.setCode("1234-5");
		assertEquals("1234-5", l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnNullIFCodeSystemIsNotLoinc() {
		LabResult l = new LabResult();
		l.setCode_system_name("NOT-LOINC");
		l.setCode("X1234-5");
		assertNull(l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnLoincCodeWithXRemoved() {
		LabResult l = new LabResult();
		l.setCode_system_name("LOINC");
		l.setCode("X1234-5");
		assertEquals("1234-5", l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnNullWhenNoCodeSystemNamePresent() {
		LabResult l = new LabResult();
		l.setCode("X1234-5");
		assertNull(l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnNullWhenNoCodePresent() {
		LabResult l = new LabResult();
		l.setCode_system_name("LOINC");
		assertNull(l.getLoincCodeName());
	}
	
	@Test
	public void getDateInMillisShouldReturnNullWhenNoDatePresent() {
		LabResult l = new LabResult();
		assertNull(l.getDateInMillis());
	}
	
	@Test
	public void getDateInMillisShouldReturnTimeInMillis() {
		LabResult l = new LabResult();
		l.setDate("1952-05-21T05:00:00.000Z");
		assertEquals(new Long(-555861600000L),l.getDateInMillis());
	}
	
	@Test
	public void getDateInMillisShouldReturnTimeInMillNegativeNumber() {
		LabResult l = new LabResult();
		l.setDate("1989-05-02T05:00:00.000Z");
		assertEquals(new Long(610106400000L),l.getDateInMillis());
	}
	
	@Test
	public void getDateInMillisShouldReturnTimeInMillOddTimeReturn() {
		LabResult l = new LabResult();
		l.setDate("2009-02-11T06:00:00.000Z");
		assertEquals(new Long(1234353600000L),l.getDateInMillis());
	}
	
	@Test
	public void getTextSHouldReturnNullWhenTextIsNull(){
		LabResult l = new LabResult();
		assertNull(l.getReferenceRange());
	}
	
	@Test
	public void getTextShouldReturnText(){
		LabResultReferenceRange r = new LabResultReferenceRange();
		r.setText("text");
		LabResult l = new LabResult();
		l.setReference_range(r);
		assertEquals("text",l.getReferenceRange());
	}
	/*
	 * get display value
	 */
	@Test
	public void getDisplayValueShouldReturnNullWhenValueIsNull(){
		LabResult l = new LabResult();
		assertNull(l.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldReturnValueWhenUnitIsNull(){
		LabResult l = new LabResult();
		l.setValue("4.4");
		assertEquals("4.4",l.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldReturnValueWithUnit(){
		LabResult l = new LabResult();
		l.setValue("4.4");
		l.setUnit("psi");
		assertEquals("4.4psi",l.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldReturnUnitWhenValueIsNull(){
		LabResult l = new LabResult();
		l.setUnit("psi");
		assertEquals("psi",l.getDisplayValue());
	}
	/*
	 * get double value
	 */
	@Test
	public void getDoubleValueShouldReturnNullWhenValueIsNull(){
		LabResult l = new LabResult();
		assertNull(l.getDoubleValue());
	}
	
	@Test
	public void getDoubleValueShouldReturnValue(){
		LabResult l = new LabResult();
		l.setValue("4.4");
		assertEquals(new Double("4.4"),l.getDoubleValue());
	}
	/*
	 * get display date
	 */
	@Test
	public void displayDateShouldReturnFormattedDate() {
		LabResult l = new LabResult();
		l.setDate("1989-05-02T05:00:00.000Z");
		
		assertEquals("1989-05-02", l.getDisplayDate());
	}
	@Test
	public void displayDateShouldReturnNull() {
		LabResult l = new LabResult();		
		assertNull(l.getDisplayDate());
	}
	
}
