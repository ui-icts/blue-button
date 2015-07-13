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

public class VitalsResultTest {

	/*
	 * get LoincCode
	 */
	@Test
	public void getLoincCodeShouldReturnCodeIfCodeSystemIsLoinc() {
		VitalsResult l = new VitalsResult();
		l.setCode_system_name("LOINC");
		l.setCode("1234-5");
		assertEquals("1234-5", l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnNullIFCodeSystemIsNotLoinc() {
		VitalsResult l = new VitalsResult();
		l.setCode_system_name("NOT-LOINC");
		l.setCode("X1234-5");
		assertNull(l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnLoincCodeWithXRemoved() {
		VitalsResult l = new VitalsResult();
		l.setCode_system_name("LOINC");
		l.setCode("X1234-5");
		assertEquals("1234-5", l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnNullWhenNoCodeSystemNamePresent() {
		VitalsResult l = new VitalsResult();
		l.setCode("X1234-5");
		assertNull(l.getLoincCodeName());
	}
	
	@Test
	public void getLoincCodeShouldReturnNullWhenNoCodePresent() {
		VitalsResult l = new VitalsResult();
		l.setCode_system_name("LOINC");
		assertNull(l.getLoincCodeName());
	}
	
	/*
	 * get display value
	 */
	@Test
	public void getDisplayValueShouldReturnNullWhenValueIsNull(){
		VitalsResult l = new VitalsResult();
		assertNull(l.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldReturnValueWhenUnitIsNull(){
		VitalsResult l = new VitalsResult();
		l.setValue("4.4");
		assertEquals("4.4",l.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldReturnValueWithUnit(){
		VitalsResult l = new VitalsResult();
		l.setValue("4.4");
		l.setUnit("psi");
		assertEquals("4.4psi",l.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldReturnUnitWhenValueIsNull(){
		VitalsResult l = new VitalsResult();
		l.setUnit("psi");
		assertEquals("psi",l.getDisplayValue());
	}
	/*
	 * get double value
	 */
	@Test
	public void getDoubleValueShouldReturnNullWhenValueIsNull(){
		VitalsResult l = new VitalsResult();
		assertNull(l.getDoubleValue());
	}
	
	@Test
	public void getDoubleValueShouldReturnValue(){
		VitalsResult l = new VitalsResult();
		l.setValue("4.4");
		assertEquals(new Double("4.4"),l.getDoubleValue());
	}

}
