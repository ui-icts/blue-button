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

import org.junit.Test;

import edu.uiowa.icts.bluebutton.json.DateRange;
import edu.uiowa.icts.bluebutton.json.Medication;

public class MedicationMetaFinderTest {

	@Test
	public void medsMertaFinderShouldReturnIfMedsAreRecent() {
		List<Medication> list = new ArrayList<Medication>();
		Medication m = new Medication();
		DateRange dr = new DateRange();
		dr.setStart("2015-01-31T05:00:00.000Z");
		m.setDate_range(dr);
		list.add(m);
		MedicationsMetaFinder mmf = new MedicationsMetaFinder(list);
		assertEquals("been prescribed medications",mmf.getRecentMedications());
		
	}
	
	@Test
	public void medsMertaFinderShouldReturnIfMedsAreNotRecent() {
		List<Medication> list = new ArrayList<Medication>();
		Medication m = new Medication();
		DateRange dr = new DateRange();
		dr.setStart("2013-01-31T05:00:00.000Z");
		m.setDate_range(dr);
		list.add(m);
		MedicationsMetaFinder mmf = new MedicationsMetaFinder(list);
		assertEquals("not had any medications prescribed",mmf.getRecentMedications());
		
	}
	
	@Test
	public void medsMertaFinderShouldReturnNullIfNoMeds() {
		List<Medication> list = new ArrayList<Medication>();
		MedicationsMetaFinder mmf = new MedicationsMetaFinder(list);
		assertEquals("not had any medications prescribed",mmf.getRecentMedications());		
	}
	
}