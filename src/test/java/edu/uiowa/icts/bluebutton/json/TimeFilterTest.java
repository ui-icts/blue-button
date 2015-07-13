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

public class TimeFilterTest {

	@Test
	public void timeFilteringShouldRemoveDatesBeforeDesiredOfRange(){
		Encounter p = new Encounter();
		p.setDate("2015-03-02T00:00:00.000Z");
		Encounter p2 = new Encounter();
		p2.setDate("2015-03-01T00:00:00.000Z");
		Encounter p3 = new Encounter();
		p3.setDate("2015-02-28T00:00:00.000Z");
		
		List<IDateInMillis> list = new ArrayList<IDateInMillis>();
		list.add(p);
		list.add(p2);
		list.add(p3);
		
		TimeFilter tf = new TimeFilter(list,1425189600000L,1425448800000L);
		List<IDateInMillis> jsonList = tf.getFillteredList();
		assertEquals(2, jsonList.size());	
	}
	
	@Test
	public void timeFilteringShouldRemoveDatesAfterDesiredOfRange(){
		Encounter p = new Encounter();
		p.setDate("2015-03-02T00:00:00.000Z");
		Encounter p2 = new Encounter();
		p2.setDate("2015-03-01T00:00:00.000Z");
		Encounter p3 = new Encounter();
		p3.setDate("2015-02-28T00:00:00.000Z");
		
		List<IDateInMillis> list = new ArrayList<IDateInMillis>();
		list.add(p);
		list.add(p2);
		list.add(p3);
		
		TimeFilter tf = new TimeFilter(list,1425016800000L,1425189600000L);
		List<IDateInMillis> jsonList = tf.getFillteredList();
		assertEquals(2, jsonList.size());	
	}
	@Test
	public void timeFilteringShouldKeepRangesWithOutDates(){
		Encounter p = new Encounter();
		Encounter p2 = new Encounter();
		Encounter p3 = new Encounter();
		
		List<IDateInMillis> list = new ArrayList<IDateInMillis>();
		list.add(p);
		list.add(p2);
		list.add(p3);
		
		TimeFilter tf = new TimeFilter(list,1425016800000L,1425189600000L);
		List<IDateInMillis> jsonList = tf.getFillteredList();
		assertEquals(3, jsonList.size());	
	}
	
	@Test
	public void timeFilteringShouldReturnAllDatesWhenNoStartDateOrEndDateIsSet(){
		Encounter p = new Encounter();
		p.setDate("2015-03-02T00:00:00.000Z");
		Encounter p2 = new Encounter();
		p2.setDate("2015-03-01T00:00:00.000Z");
		Encounter p3 = new Encounter();
		p3.setDate("2015-02-28T00:00:00.000Z");
		
		List<IDateInMillis> list = new ArrayList<IDateInMillis>();
		list.add(p);
		list.add(p2);
		list.add(p3);
		
		TimeFilter tf = new TimeFilter(list,null,null);
		List<IDateInMillis> jsonList = tf.getFillteredList();
		assertEquals(3, jsonList.size());	
	}
	 
	
	
}
