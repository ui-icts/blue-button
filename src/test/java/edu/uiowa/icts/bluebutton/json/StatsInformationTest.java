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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.uiowa.icts.bluebutton.json.view.IGetStats;
import edu.uiowa.icts.bluebutton.json.view.StatsFinder;

public class StatsInformationTest {

	@Test
	public void getNormalShouldReturnYesWhenInRange(){
		LoincCode lc = new LoincCode("1234-5", 4.5, 8.6);
		LabResult lr = new LabResult();
		lr.setLoincCode(lc);
		lr.setValue("5.5");
		StatsInformation si = new StatsInformation(null,lr.getDoubleValue(), lc);
		assertEquals(true, si.getInNormalRange());
	}
	
	@Test
	public void getNormalShouldReturnNoWhenOutOfRange(){
		LoincCode lc = new LoincCode("1234-5", 4.5, 8.6);
		LabResult lr = new LabResult();
		lr.setValue("9.6");
		lr.setLoincCode(lc);
		StatsInformation si = new StatsInformation(null,lr.getDoubleValue(), lc);
		assertEquals(false, si.getInNormalRange());
		assertNotNull(si.getDisplayText());
		assertEquals(StatsInformation.outOfRangeText,si.getDisplayText());
	}
	
	@Test
	public void getNormalSDShouldReturnFalseWhenDataPointIsOutOfSDRange(){
		LabResult lr = new LabResult();
		lr.setValue("2000");
		List<IGetStats> list = new ArrayList<IGetStats>();
		for(int x=0; x<5; x++){
			LabResult lr2 = new LabResult();
			lr2.setValue(new Double(x*3).toString());
			list.add(lr2);
		}
		
		StatsFinder sf = new StatsFinder(list);
		StatsInformation si = new StatsInformation(sf,lr.getDoubleValue(), null);
		assertNotNull(si.getDisplayText());
		assertEquals("Greater than 2 standard deviations",si.getDisplayText());
	}
	
	@Test
	public void getNormalSDShouldReturnFalseWhenDataPointBothOutOfRange(){
		LoincCode lc = new LoincCode("1234-5", 4.5, 8.6);
		LabResult lr = new LabResult();
		lr.setValue("2000");
		List<IGetStats> list = new ArrayList<IGetStats>();
		for(int x=0; x<5; x++){
			LabResult lr2 = new LabResult();
			lr2.setValue(new Double(x*3).toString());
			list.add(lr2);
		}
		
		StatsFinder sf = new StatsFinder(list);
		StatsInformation si = new StatsInformation(sf,lr.getDoubleValue(), lc);
		assertNotNull(si.getDisplayText());
		assertEquals(StatsInformation.bothOutOfRange,si.getDisplayText());
	}
	
	@Test
	public void getNormalShouldReturnNullWhenNoLoincCode(){
		assertNull(new StatsInformation(null,null,null).getInNormalRange());
		assertNull(new StatsInformation(null,null,null).getDisplayText());	}
	
	@Test
	public void getNormalShouldReturnNullWhenNoValue(){
		LoincCode lc = new LoincCode("1234-5", 4.5, 8.6);
		LabResult lr = new LabResult();
		lr.setLoincCode(lc);
		StatsInformation si = new StatsInformation(null,lr.getDoubleValue(), lc);
		assertNull(si.getInNormalRange());
	}
	
}
