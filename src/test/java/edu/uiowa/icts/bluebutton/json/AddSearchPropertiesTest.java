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

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class AddSearchPropertiesTest {

	@Test
	public void addSearchPropertiesShouldAddIdsAndSubGridInformationFromJSONArray(){
		JSONObject json = new JSONObject();
		json.put("name", "test");
		JSONObject json2 = new JSONObject();
		json2.put("name", "test2");
		JSONObject json3 = new JSONObject();
		json3.put("name", "test3");
		
		JSONArray subGrid = new JSONArray();
		subGrid.put(new JSONObject(json, JSONObject.getNames(json))); subGrid.put(json2);
		
		json.put("subGrid", subGrid);
		JSONArray array = new JSONArray();
		array.put(json); array.put(json3);
		
		AddSearchProperties asp = new AddSearchProperties(array, "test");
		JSONArray newArray = asp.getJsonArray();
		
		assertNotNull(newArray);
		assertEquals(2, newArray.length());
		json = newArray.getJSONObject(0);
		assertEquals("test-0", json.get("id"));
		
		assertTrue(json.has("subGrid"));
		subGrid = json.getJSONArray("subGrid");
		assertEquals(2, subGrid.length());
		
		json = subGrid.getJSONObject(0);
		assertTrue(json.has("id"));
		assertEquals("test-0", json.get("id"));
		assertTrue(json.has("parent"));
		assertEquals("test-0",json.get("parent"));
		
		json2 = subGrid.getJSONObject(1);
		assertTrue(json2.has("id"));
		assertEquals("test-1", json2.get("id"));
		assertTrue(json2.has("parent"));
		assertEquals("test-0",json2.get("parent"));
		
		json3 = newArray.getJSONObject(1);
		assertTrue(json3.has("id"));
		assertEquals("test-2",json3.get("id"));
	}
	
	@Test
	public void addSearchPropertiesShouldReturnEmptyJSONArrayWhenNoDataIsEntered(){
		JSONArray array = new JSONArray();
		AddSearchProperties asp = new AddSearchProperties(array, "test");
		JSONArray newArray = asp.getJsonArray();
		assertEquals(0, newArray.length());
	}
}
