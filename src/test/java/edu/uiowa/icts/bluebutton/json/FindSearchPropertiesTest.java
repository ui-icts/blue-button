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
import org.junit.Test;

public class FindSearchPropertiesTest {

	
	@Test
	public void findSearchPopertiesShouldAddSearchIdsToRecords(){
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
		
		List<ISubgroup> fList = new ArrayList<ISubgroup>();
		fList.addAll(aList);
		
		List<ISubgroup> listWithIds = new FindSearchProperties(fList).getListWithSearchIds();
		
		assertNotNull(listWithIds);
		assertEquals(1, listWithIds.size());
		ISubgroup i = listWithIds.get(0);
		assertEquals("allergy0", i.getId());
		
		List<Allergy> subgridAList = new ArrayList<Allergy>();
		for(ISubgroup s : i.getSubgridInterface()){
			subgridAList.add((Allergy) s);
		}
		
		assertEquals(3, subgridAList.size());
		
		Allergy a = subgridAList.get(0);
		assertEquals("allergy1", a.getId());
		assertEquals("allergy0", a.getParent());
		assertEquals("Pollen", a.getKeywords().get(0));
		
		a = subgridAList.get(1);
		assertEquals("allergy0", a.getParent());
		assertEquals("allergy2", a.getId());
		assertEquals("Pollen", a.getKeywords().get(0));

		
		a = subgridAList.get(2);
		assertEquals("allergy0", a.getParent());
		assertEquals("allergy3", a.getId());
		assertEquals("Pollen", a.getKeywords().get(0));

		
		
		
		
		
		
		
	}
	
}
