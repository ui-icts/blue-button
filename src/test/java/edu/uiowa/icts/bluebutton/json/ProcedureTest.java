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
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ProcedureTest {

	@Test
	public void proceduresShouldReturnNameWithTranslation() {
		Procedure p = new Procedure ();
		p.setName("RADIOLOGIC EXAMINATION, ABDOMEN; ANTEROPOSTERIOR AND ADDITIONAL OBLIQUE AND CONE VIEWS");
		Entry s = new Entry();
		s.setName("Specimen");
		p.setSpecimen(s);
		Entry d  = new Entry();
		d.setName("Device");
		p.setDevice(d);
		
		assertEquals("RADIOLOGIC EXAMINATION, ABDOMEN; ANTEROPOSTERIOR AND ADDITIONAL OBLIQUE AND CONE VIEWS", p.getName());
		assertEquals("Specimen", p.getSpecimen());
		assertEquals("Device", p.getDevice());
	}
	
	@Test
	public void proceduresShouldContainOgranizationName(){
		Procedure p = new Procedure();
		Location l = new Location();
		l.setOrganization("Provider's Office");
		p.setPerformer(l);
		assertEquals("Provider's Office", p.getOrganization());
	}
		
}
