//package edu.uiowa.icts.bluebutton.json;

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
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.junit.Test;
//
//public class SubGridGeneratorTest {
//
//	@Test
//	public void generatorShouldReturnJsonForZeroLabs() {
//	    List<JSONObject> allergyList =  new ArrayList<JSONObject>();
//		SubGridGenerator aWithSubGrid = new SubGridGenerator(allergyList,"loincCode", "resultName", "dateInMillis");
//		JSONArray ja = aWithSubGrid.getJsonArray();
//		assertEquals(0, ja.length());
//	}
//	
//	/*
//	 * Test results with LOINC codes
//	 */
//	
//	@Test
//	public void generatorShouldGiveEveryRowASubGridEvenWhenThereIsNoOtherRowsToGroupWith(){
//		List<JSONObject> list =  new ArrayList<JSONObject>();
//		Medication m = new Medication();
//		Quantity q = new Quantity();
//		q.setValue("5");
//		Product p = new Product();
//		p.setName("Test");
//		m.setProduct(p);
//		m.setDose_quantity(q);
//		list.add(m.getJson());
//		SubGridGenerator subMeds = new SubGridGenerator(list,"medicationName","dateInMillis","m");
//		JSONArray medsGrid = subMeds.getJsonArray();
//		assertEquals(1, medsGrid.length());
//		JSONObject json =  medsGrid.getJSONObject(0);
//			
//		//test subgrid
//		assertTrue(json.has("subGrid"));
//		JSONArray subGrid = json.getJSONArray("subGrid");
//		assertEquals(1,subGrid.length());
//		json = subGrid.getJSONObject(0);
//		assertEquals("5", json.get("dose"));
//	}
//	
//	@Test
//	public void generatorShouldReturnJsonWithMedicationsGroupedByNameWithIdInjected(){
//		Medication m = new Medication();
//		Quantity q = new Quantity();
//		q.setValue("5");
//		Product p = new Product();
//		p.setName("Test");
//		m.setProduct(p);
//		m.setDose_quantity(q);
//		Medication m2 = new Medication();
//		Quantity q2 = new Quantity();
//		q2.setValue("8");
//		m2.setProduct(p);
//		m2.setDose_quantity(q2);
//		Medication m3 = new Medication();
//		Quantity q3 = new Quantity();
//		q3.setValue("12");
//		m3.setDose_quantity(q3);
//		m3.setProduct(p);
//		
//		List<Medication> meds = new ArrayList<Medication>();
//		meds.add(m);
//		meds.add(m2);
//		meds.add(m3);
//		
//		List<JSONObject> medsJson = new ArrayList<JSONObject>();
//		for (Medication med : meds) {
//			medsJson.add(med.getJson());
//		}
//		
//		SubGridGenerator subMeds = new SubGridGenerator(medsJson,"medicationName","dateInMillis","m");
//		JSONArray medsGrid = subMeds.getJsonArray();
//		assertEquals(1, medsGrid.length());
//		JSONObject json =  medsGrid.getJSONObject(0);
//		
//		//test subgrid
//		assertTrue(json.has("subGrid"));
//		JSONArray subGrid = json.getJSONArray("subGrid");
//		assertEquals(3,subGrid.length());
//		json = subGrid.getJSONObject(0);
//		assertEquals("5", json.get("dose"));
//		json = subGrid.getJSONObject(1);
//		assertEquals("8", json.get("dose"));
//		json = subGrid.getJSONObject(2);
//		assertEquals("12", json.get("dose"));
//
//		
//	}
//	
//	@Test
//	public void subGridGeneratorShouldNotGridEmptyJSONObject(){
//		List<JSONObject> list = new ArrayList<JSONObject>();
//		LabResult lr = new LabResult();
//		list.add(lr.getJson());
//		SubGridGenerator subMeds = new SubGridGenerator(list,"medicationName","dateInMillis","m");
//		assertEquals(0,subMeds.getJsonArray().length());
//	}
//	
//	@Test
//	public void subGridChildRowsShouldNotHaveSubGridObjectInsideOfThem(){
//		List<JSONObject> list = new ArrayList<JSONObject>();
//		LabResult lr = new LabResult(); lr.setName("Test");
//		list.add(lr.getJson()); list.add(lr.getJson());
//		assertEquals(2, list.size());
//		JSONArray subLabs = new SubGridGenerator(list,"name","dateInMillis","lab").getJsonArray();
//		assertEquals(1,subLabs.length());
//		JSONObject parent = subLabs.getJSONObject(0);
//		assertTrue(parent.has("subGrid"));
//		JSONArray subGrid = parent.getJSONArray("subGrid");
//		assertEquals(2, subGrid.length());
//		JSONObject child0 = subGrid.getJSONObject(0);
//		assertFalse(child0.has("subGrid"));
//		JSONObject child1 = subGrid.getJSONObject(1);
//		assertFalse(child1.has("subGrid"));
//	}
//	
//	
//}
