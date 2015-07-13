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

import edu.uiowa.icts.bluebutton.json.CombinedSmokingStatus;
import edu.uiowa.icts.bluebutton.json.SmokingStatus;


public class CombinedSmokingStatsTest {

	@Test
	public void combinedSmokingStatusShouldCombineAListOfSmokingStatus(){
		List<SmokingStatus> list = new ArrayList<SmokingStatus>();
		SmokingStatus ss = new SmokingStatus();
		ss.setName("never smoker (never smoked)");
		list.add(ss);
		list.add(new SmokingStatus());
		
		SmokingStatus s = new CombinedSmokingStatus(list).getSmokingStatus();
		assertEquals("non-smoker", s.getName());
	}
	
	@Test
	public void combinedSmokingStatusShouldCombineAListOfSmokingStatusForSmoker(){
		List<SmokingStatus> list = new ArrayList<SmokingStatus>();
		SmokingStatus ss = new SmokingStatus();
		ss.setName("current smoker");
		list.add(ss);
		list.add(new SmokingStatus());
		
		SmokingStatus s = new CombinedSmokingStatus(list).getSmokingStatus();
		assertEquals("smoker", s.getName());
	}
	
}
