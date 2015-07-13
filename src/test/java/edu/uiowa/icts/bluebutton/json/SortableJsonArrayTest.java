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


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.uiowa.icts.spring.AbstractSpringTestCase;

@WebAppConfiguration
public class SortableJsonArrayTest  extends AbstractSpringTestCase {

	@Test
	public void sortedListGetterShouldReturnWhatSetterPutIntoIt(){
		JSONArray ja = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("long", new Long(5));
		ja.put(j);
		SortableJsonArray sja= new SortableJsonArray(ja);
		JSONArray ja2 = new JSONArray();
		JSONObject j2 = new JSONObject();
		j2.put("long", new Long(9));
		ja2.put(j2);
		sja.setJSONArray(ja2);
		assertEquals(ja2,sja.getJSONArray());
	}
	
	
	@Test
	public void sortedLisLongShouldReturnSortedJSONArray(){
		
		JSONArray ja = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("long", new Long(5));
		JSONObject j2 = new JSONObject();
		j2.put("long", new Long(9));
		JSONObject j3 = new JSONObject();
		j3.put("long", new Long(4));
		ja.put(j);
		ja.put(j2);
		ja.put(j3);
		
		SortableJsonArray sja= new SortableJsonArray(ja);
		
		sja.sortByLong("long");
		
		JSONArray jaRight = new JSONArray();
		jaRight.put(j2);
		jaRight.put(j);
		jaRight.put(j3);
		
		assertEquals(jaRight.toString(), sja.getJSONArray().toString());
	}
	
	@Test
	public void sortedLisLongShouldReturnSortedJSONArrayWithSomeObjectsMissingSortByParam(){
		
		JSONArray ja = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("long", new Long(5));
		JSONObject j2 = new JSONObject();
		j2.put("notlong", new Long(4));
		JSONObject j3 = new JSONObject();
		j3.put("long", new Long(4));
		ja.put(j);
		ja.put(j2);
		ja.put(j3);
		
		SortableJsonArray sja= new SortableJsonArray(ja);
		
		sja.sortByLong("long");
		
		JSONArray jaRight = new JSONArray();
		jaRight.put(j);
		jaRight.put(j3);
		jaRight.put(j2);
		
		assertEquals(jaRight.toString(), sja.getJSONArray().toString());
	}
	
	@Test
	public void sortedLisIntShouldReturnSortedJSONArrayWithSomeObjectsMissingSortByParam(){
		
		JSONArray ja = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("int", new Integer(5));
		JSONObject j2 = new JSONObject();
		j2.put("notInt", new Integer(4));
		JSONObject j3 = new JSONObject();
		j3.put("int", new Integer(4));
		ja.put(j);
		ja.put(j2);
		ja.put(j3);
		
		SortableJsonArray sja= new SortableJsonArray(ja);
		
		sja.sortByInt("int");
		
		JSONArray jaRight = new JSONArray();
		jaRight.put(j);
		jaRight.put(j3);
		jaRight.put(j2);
		
		assertEquals(jaRight.toString(), sja.getJSONArray().toString());
	}
	
	
	
	@Test
	public void sortedLisLongShouldReturnSortedJSONArrayWithMultipleObjectsInSideTheJsonObject(){
		
		JSONArray ja = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("long", new Long(5));
		j.put("foo", "bar");
		JSONObject j2 = new JSONObject();
		j2.put("foo", "bar");
		j2.put("long", new Long(9));
		JSONObject j3 = new JSONObject();
		j3.put("long", new Long(4));
		j3.put("foo", "bar");
		ja.put(j);
		ja.put(j2);
		ja.put(j3);
		
		SortableJsonArray sja= new SortableJsonArray(ja);
		
		sja.sortByLong("long");
		
		JSONArray jaRight = new JSONArray();
		jaRight.put(j2);
		jaRight.put(j);
		jaRight.put(j3);
		assertEquals(jaRight.toString(), sja.getJSONArray().toString());
	}
	
	@Test
	public void sortedListLongShouldReturnSortedJSONArrayWithMultipleObjectsInSideTheJsonObjectAndMoreJSONs(){
		
		JSONArray ja = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("long", new Long(5));
		j.put("foo", "bar");
		JSONObject j2 = new JSONObject();
		j2.put("foo", "bar");
		j2.put("long", new Long(9));
		JSONObject j3 = new JSONObject();
		j3.put("long", new Long(4));
		j3.put("foo", "bar");
		ja.put(j);
		ja.put(j2);
		ja.put(j3);
		JSONObject j4 = new JSONObject();
		j4.put("long", new Long(5));
		j4.put("foo", "bar");
		JSONObject j5 = new JSONObject();
		j5.put("foo", "bar");
		j5.put("long", new Long(9));
		JSONObject j6 = new JSONObject();
		j6.put("long", new Long(4));
		j6.put("foo", "bar");
		ja.put(j4);
		ja.put(j5);
		ja.put(j6);
		
		
		SortableJsonArray sja= new SortableJsonArray(ja);
		
		sja.sortByLong("long");
		
		JSONArray jaRight = new JSONArray();
		jaRight.put(j2);
		jaRight.put(j5);
		jaRight.put(j);
		jaRight.put(j4);
		jaRight.put(j3);
		jaRight.put(j6);
		
		assertEquals(jaRight.toString(), sja.getJSONArray().toString());
	}
	
	@Test
	public void sortedListIntShouldReturnSortedJSONArrayWithMultipleObjectsInSideTheJsonObjectAndMoreJSONs(){
		
		JSONArray ja = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("int", new Integer(5));
		j.put("foo", "bar");
		JSONObject j2 = new JSONObject();
		j2.put("foo", "bar");
		j2.put("int", new Integer(9));
		JSONObject j3 = new JSONObject();
		j3.put("int", new Integer(4));
		j3.put("foo", "bar");
		ja.put(j);
		ja.put(j2);
		ja.put(j3);
		JSONObject j4 = new JSONObject();
		j4.put("int", new Integer(5));
		j4.put("foo", "bar");
		JSONObject j5 = new JSONObject();
		j5.put("foo", "bar");
		j5.put("int", new Integer(9));
		JSONObject j6 = new JSONObject();
		j6.put("int", new Integer(4));
		j6.put("foo", "bar");
		ja.put(j4);
		ja.put(j5);
		ja.put(j6);
		
		
		SortableJsonArray sja= new SortableJsonArray(ja);
		
		sja.sortByInt("int");
		
		JSONArray jaRight = new JSONArray();
		jaRight.put(j2);
		jaRight.put(j5);
		jaRight.put(j);
		jaRight.put(j4);
		jaRight.put(j3);
		jaRight.put(j6);
		
		assertEquals(jaRight.toString(), sja.getJSONArray().toString());
	}


}
