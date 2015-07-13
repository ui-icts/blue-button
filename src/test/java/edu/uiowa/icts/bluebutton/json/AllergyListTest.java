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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class AllergyListTest {

	@Test
	public void allergyListShouldReturnCorrectListOfAllergiesFromListOfBBRs(){
		Entry e = new Entry();
		e.setName("Pollen");
		
		List<Allergy> aList = new ArrayList<Allergy>();
		for (int i = 1; i < 4; i++) {
			Allergy a = new Allergy();
			a.setDate(new DateTime(2015,1,i,0,0).toString(Entry.FORMAT));
			a.setAllergen(e);
			aList.add(a);
		}
		
		assertEquals(3, aList.size());
				
		AllergyList al = new AllergyList(aList);
		
		aList = al.getAllergies();
		assertNotNull(aList);	
		assertEquals(1, aList.size());
		
		Allergy a = aList.get(0);
		assertEquals("allergy0", a.getId());
		assertEquals("Pollen", a.getAllergen().getName());
		assertEquals(new DateTime(2015,1,3,0,0).toString(Entry.FORMAT), a.getDate());
		
		List<Allergy> subGrid = a.getSubGrid();
		
		assertEquals(3, subGrid.size());
		Allergy subA = subGrid.get(0);
		assertEquals("allergy1", subA.getId());
		assertEquals("allergy0", subA.getParent());
		assertEquals("Pollen", subA.getAllergen().getName());
		
		subA = subGrid.get(1);
		assertEquals("allergy2", subA.getId());
		assertEquals("allergy0", subA.getParent());
		assertEquals("Pollen", subA.getAllergen().getName());
		assertEquals(new DateTime(2015,1,2,0,0).toString(Entry.FORMAT), subA.getDate());
		
		subA = subGrid.get(2);
		assertEquals("allergy3", subA.getId());
		assertEquals("allergy0", subA.getParent());
		assertEquals("Pollen", subA.getAllergen().getName());
		assertEquals(new DateTime(2015,1,1,0,0).toString(Entry.FORMAT), subA.getDate());
		
	}
	
}
