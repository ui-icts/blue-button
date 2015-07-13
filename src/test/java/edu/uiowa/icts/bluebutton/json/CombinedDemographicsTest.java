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

import org.junit.Test;

public class CombinedDemographicsTest {

	@Test
	public void combinedDemographicsshouldCombineDemographics(){
		List<Demographics> record = new ArrayList<Demographics>();
		
		Demographics d = new Demographics(); Name name = new Name();
		List<String> nameList = new ArrayList<String>(); nameList.add("Bob");
		name.setFamily("Smith"); name.setGiven(nameList);
		d.setName(name);
		Provider provider = new Provider();
		provider.setOrganization("Place 1");
		d.setProvider(provider);
		record.add(d);
		
		Demographics d2 = new Demographics();
		d2.setGender("Male");
		Provider provider2 = new Provider();
		provider2.setOrganization("Place 2");
		d2.setProvider(provider2);
		record.add(d2);
		
		Demographics d3 = new Demographics();
		d3.setMarital_status("Single");
		Provider provider3 = new Provider();
		provider3.setOrganization("Place 3");
		d3.setProvider(provider3);
		record.add(d3);
		
		Demographics d4 = new Demographics();
		d4.setDob("06/13/1992");
		Provider provider4 = new Provider();
		provider4.setOrganization("Place 4");
		d4.setProvider(provider4);
		record.add(d4);
		
		Demographics masterDemo = new CombinedDemographics(record).getDempgraphics();
		assertEquals("Bob Smith", masterDemo.getName().getFullName());
		assertEquals("Place 1, Place 2, Place 3, Place 4", masterDemo.getCombinedSourceList());
		assertEquals("Male", masterDemo.getGender());
		assertEquals("Single",masterDemo.getMarital_status());
		assertEquals("06/13/1992", masterDemo.getDob());
		
	}
	
}
