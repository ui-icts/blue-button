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

import org.json.JSONObject;
import org.junit.Test;

import edu.uiowa.icts.bluebutton.json.VitalsResult;
import edu.uiowa.icts.bluebutton.json.LoincCode;

public class CombineStatsTest {

	@Test
	public void combinedStatsShouldReturnNullIfNoDataPassed(){
		CombineStats cs = new CombineStats(null, null);
		assertNull(cs.getCombiedStats());
	}
	
	@Test
	public void combinedStatsShouldReturnNullIfNoListPassed(){
		HashMap<String, StatsFinder> hash = new HashMap<String, StatsFinder>();
		hash.put("12345", new StatsFinder(new ArrayList<IGetStats>()));
		assertNull(new CombineStats(null, hash).getCombiedStats());
	}
	
	@Test
	public void combinedSatasShouldUnAlteredListIfNoHashMapPass(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		VitalsResult lr = new VitalsResult();
		lr.setValue("5.5");
		list.add(lr);
		lr = new VitalsResult();
		lr.setValue("6.5");
		list.add(lr);
		lr = new VitalsResult();
		lr.setValue("7.5");
		list.add(lr);
		
		CombineStats cs = new CombineStats(list, null);
		assertNotNull(cs.getCombiedStats());
		assertEquals(3, cs.getCombiedStats().size());
		
		VitalsResult vr =(VitalsResult) cs.getCombiedStats().get(0);
		assertEquals(new Double(5.5), vr.getValue());
		
		vr = (VitalsResult) cs.getCombiedStats().get(1);
		assertEquals(new Double(6.5), vr.getValue());
		
		vr =  (VitalsResult)cs.getCombiedStats().get(2);
		assertEquals(new Double(7.5), vr.getValue());

	}
	
	@Test
	public void combineStatsShouldSetWithinSD(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		VitalsResult lr = new VitalsResult();
		lr.setValue("1");
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		lr = new VitalsResult();
		lr.setValue("1");
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		lr = new VitalsResult();
		lr.setValue("1");
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		lr = new VitalsResult();
		lr.setValue("1");
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		lr = new VitalsResult();
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		lr.setValue("200");
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		list.add(lr);
		lr = new VitalsResult();
		lr.setValue("1");
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		lr = new VitalsResult();
		lr.setValue("1");
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setLoincCode(new LoincCode("1234-5", null, null));
		list.add(lr);
		
		
		GetStatsByLoincCodeAndName dbu = new GetStatsByLoincCodeAndName(list);
		CombineStats cs = new CombineStats(list, dbu.getUnits());
		
		List<IGetStats> statsList = cs.getCombiedStats();
		assertNotNull(statsList);
		VitalsResult vr =  (VitalsResult) statsList.get(0);
		assertEquals(true,vr.getStatsInformation().getNormalSD());
		assertEquals(new Double(29.428571428571427),vr.getStatsInformation().getMean());
		assertEquals(new Double(69.63549411626464),vr.getStatsInformation().getStandardDeviation());		
		
		vr = (VitalsResult) statsList.get(4);
		assertEquals(false,vr.getStatsInformation().getNormalSD());
		
	}
	
	@Test
	public void combinedStatsShouldReturnWithinNormalRangeIfAllDataIsTheSame(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LoincCode lc = new LoincCode("1234-5", null, null);
		VitalsResult lr = new VitalsResult();
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setValue("1");
		lr.setLoincCode(lc);
		list.add(lr);
		lr = new VitalsResult();
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setValue("1");
		lr.setLoincCode(lc);
		list.add(lr);
		lr = new VitalsResult();
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		lr.setValue("1");
		lr.setLoincCode(lc);
		list.add(lr);
		lr = new VitalsResult();
		lr.setCode_system_name("LOINC");
		lr.setCode("1234-5");
		
		lr.setValue("1");
		lr.setLoincCode(lc);
		list.add(lr);
		
		GetStatsByLoincCodeAndName dbu = new GetStatsByLoincCodeAndName(list);
		CombineStats cs = new CombineStats(list, dbu.getUnits());
		
		List<IGetStats> statsList = cs.getCombiedStats();
		assertNotNull(statsList);
		assertEquals(4, statsList.size());
		VitalsResult vr = (VitalsResult)statsList.get(0);
		assertEquals(new Double(1), vr.getStatsInformation().getMean());
		assertEquals(new Double(0), vr.getStatsInformation().getStandardDeviation());
		assertEquals(true,vr.getStatsInformation().getNormalSD());
		
	}
	
}
