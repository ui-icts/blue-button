package edu.uiowa.icts.bluebutton.domain;

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

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class ClinicalDocumentTest {

	@Test
	public void getXMLShouldReturnStringValue() throws UnsupportedEncodingException {
		ClinicalDocument cd = new ClinicalDocument(null,"<xml><clinicaldocument></clinicaldocument>".getBytes("UTF-8"),null,null,null,null,null);
		assertEquals("<xml><clinicaldocument></clinicaldocument>", cd.getXml());
	}
	
	@Test
	public void getXMLShouldReturnEmptyString() throws UnsupportedEncodingException {
		ClinicalDocument cd = new ClinicalDocument(null,"".getBytes("UTF-8"),null,null,null,null,null);
		assertEquals("", cd.getXml());
	}
	
	@Test
	public void getXMLShouldReturnEmptyStringWhenValueIsNull() throws UnsupportedEncodingException {
		ClinicalDocument cd = new ClinicalDocument(null,null,null,null,null,null,null);
		assertEquals("", cd.getXml());
	}

}
