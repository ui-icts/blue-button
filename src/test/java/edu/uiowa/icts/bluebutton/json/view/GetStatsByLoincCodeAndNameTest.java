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
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import edu.uiowa.icts.bluebutton.json.LabResult;
import edu.uiowa.icts.bluebutton.json.LoincCode;

public class GetStatsByLoincCodeAndNameTest {

	@Test
	public void divideByUnitsShouldSeperateValuesBasedOnUnit(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LabResult lr = new LabResult();
		lr.setValue("5.5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		LabResult lr2 = new LabResult();
		lr2.setValue("6.5");
		lr2.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr2);
		LabResult lr3 = new LabResult();
		lr3.setValue("7.5");
		lr3.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr3);
		LabResult lr4 = new LabResult();
		lr4.setValue("8.5");
		lr4.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr4);

		LabResult lr5 = new LabResult();
		lr5.setValue("10");
		lr5.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr5);
		LabResult lr6 = new LabResult();
		lr6.setValue("11");
		lr6.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr6);
		LabResult lr7 = new LabResult();
		lr7.setValue("12");
		lr7.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr7);
		LabResult lr8 = new LabResult();
		lr8.setValue("13");
		lr8.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr8);
		
		GetStatsByLoincCodeAndName dbu = new GetStatsByLoincCodeAndName(list);
		HashMap<String, StatsFinder> hash = dbu.getUnits();
		
		StatsFinder sf = hash.get("1234-5");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(7), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
		
    	sf = hash.get("1234-6");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(11.5), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
		
	}
	
	@Test
	public void divideByUnitsShouldSeperateValuesBasedOnUnitWithNullUnit(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LabResult lr = new LabResult();
		lr.setValue("5.5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		LabResult lr2 = new LabResult();
		lr2.setValue("6.5");
		lr2.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr2);
		LabResult lr3 = new LabResult();
		lr3.setValue("7.5");
		lr3.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr3);
		LabResult lr4 = new LabResult();
		lr4.setValue("8.5");
		lr4.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr4);

		LabResult lr5 = new LabResult();
		lr5.setValue("10");
		lr5.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr5);
		LabResult lr6 = new LabResult();
		lr6.setValue("11");
		lr6.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr6);
		LabResult lr7 = new LabResult();
		lr7.setValue("12");
		lr7.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr7);
		LabResult lr8 = new LabResult();
		lr8.setValue("13");
		lr8.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr8);
		LabResult lr9 = new LabResult();
		lr9.setValue("13");
		list.add(lr9);
		
		GetStatsByLoincCodeAndName dbu = new GetStatsByLoincCodeAndName(list);
		HashMap<String, StatsFinder> hash = dbu.getUnits();
		
		StatsFinder sf = hash.get("1234-5");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(7), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
		
    	sf = hash.get("1234-6");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(11.5), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
		
	}
	
	@Test
	public void divideByUnitsShouldSeperateValuesBasedOnUnitWithNullValue(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LabResult lr = new LabResult();
		lr.setValue("5.5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		LabResult lr2 = new LabResult();
		lr2.setValue("6.5");
		lr2.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr2);
		LabResult lr3 = new LabResult();
		lr3.setValue("7.5");
		lr3.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr3);
		LabResult lr4 = new LabResult();
		lr4.setValue("8.5");
		lr4.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr4);

		LabResult lr5 = new LabResult();
		lr5.setValue("10");
		lr5.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr5);
		LabResult lr6 = new LabResult();
		lr6.setValue("11");
		lr6.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr6);
		LabResult lr7 = new LabResult();
		lr7.setValue("12");
		lr7.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr7);
		LabResult lr8 = new LabResult();
		lr8.setValue("13");
		lr8.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr8);
		LabResult lr9 = new LabResult();
		lr9.setLoincCode(new LoincCode("1234-6", null, null));
		list.add(lr9);
		
		GetStatsByLoincCodeAndName dbu = new GetStatsByLoincCodeAndName(list);
		HashMap<String, StatsFinder> hash = dbu.getUnits();
		
		StatsFinder sf = hash.get("1234-5");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(7), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
		
    	sf = hash.get("1234-6");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(11.5), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
		
	}
	
	@Test
	public void groupByLoincCodeAndResultNameShouldGroupByResultNameAndResultName(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LabResult lr = new LabResult();
		lr.setValue("5.5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		LabResult lr2 = new LabResult();
		lr2.setValue("6.5");
		lr2.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr2);
		LabResult lr3 = new LabResult();
		lr3.setValue("7.5");
		lr3.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr3);
		LabResult lr4 = new LabResult();
		lr4.setValue("8.5");
		lr4.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr4);

		LabResult lr5 = new LabResult();
		lr5.setName("Blood");
		lr5.setValue("10");
		list.add(lr5);
		LabResult lr6 = new LabResult();
		lr6.setName("Blood");
		lr6.setValue("11");
		list.add(lr6);
		LabResult lr7 = new LabResult();
		lr7.setName("Blood");
		lr7.setValue("12");
		list.add(lr7);
		LabResult lr8 = new LabResult();
		lr8.setName("Blood");
		lr8.setValue("13");
		list.add(lr8);
		
		GetStatsByLoincCodeAndName dbu = new GetStatsByLoincCodeAndName(list);
		HashMap<String, StatsFinder> hash = dbu.getUnits();
		
		StatsFinder sf = hash.get("1234-5");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(7), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
		
    	sf = hash.get("Blood");
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(11.5), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
	}
	
	
}
