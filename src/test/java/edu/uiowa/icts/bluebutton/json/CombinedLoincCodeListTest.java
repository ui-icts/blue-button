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
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

public class CombinedLoincCodeListTest {

	@Test
	public void combinedLoincCodeListShouldReturnCombinedListOfLoincCodes(){
		LoincCode lc = new LoincCode();
		lc.setCode("1234-5");
		lc.setSubRootName("Blood");
		List<LoincCode> catList = new ArrayList<LoincCode>();
		catList.add(lc);
		
		LoincCode lc2 = new LoincCode("1234-5",4.5,12.9);
		List<LoincCode> rangeList = new ArrayList<LoincCode>();
		rangeList.add(lc2);
		
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(catList,rangeList);
		HashMap<String, LoincCode> lh = clcl.getCombinedHash();
		assertNotNull(lh);
		assertEquals(1, lh.size());
		assertTrue(lh.containsKey("1234-5"));
		LoincCode loincCode = lh.get("1234-5");
		assertEquals("Blood", loincCode.getSubRootName());
		assertEquals("4.5 - 12.9", loincCode.getNormalRange());	
	}
	
	@Test
	public void combinedLoincCodeListShouldNotCombineDifferentLoincCodes(){
		LoincCode lc = new LoincCode();
		lc.setCode("1234-5");
		lc.setSubRootName("Blood");
		List<LoincCode> catList = new ArrayList<LoincCode>();
		catList.add(lc);
		
		LoincCode lc2 = new LoincCode("1234-6",4.5,12.9);
		List<LoincCode> rangeList = new ArrayList<LoincCode>();
		rangeList.add(lc2);
		
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(catList,rangeList);
		HashMap<String, LoincCode> lh = clcl.getCombinedHash();
		assertNotNull(lh);
		assertEquals(2, lh.size());
		assertTrue(lh.containsKey("1234-5"));
		LoincCode loincCode = lh.get("1234-5");
		assertEquals("Blood", loincCode.getSubRootName());
		
		assertTrue(lh.containsKey("1234-6"));
		loincCode = lh.get("1234-6");
		assertEquals("4.5 - 12.9", loincCode.getNormalRange());		
	}
	
	@Test
	public void combinedLoincCOdeListShouldReturnEmptyHashMapWhenNoLoincCodesPassed(){
		List<LoincCode> catList = new ArrayList<LoincCode>();
		List<LoincCode> rangeList = new ArrayList<LoincCode>();
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(catList,rangeList);
		assertEquals(new HashMap<String, LoincCode>(), clcl.getCombinedHash());
	}
	
	@Test
	public void combinedLoincCodeListShouldReturnEmptyHashMapWhenGivenNullLists(){
		CombinedLoincCodeList clcl = new CombinedLoincCodeList(null,null);
		assertEquals(new HashMap<String, LoincCode>(), clcl.getCombinedHash());
	}
	
	
}
