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
import java.util.List;

import org.junit.Test;

import edu.uiowa.icts.bluebutton.json.LabResult;

public class StatsFinderTest {

	@Test
	public void statsFinderShouldReturnStandardDeviation(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LabResult lr = new LabResult();
		lr.setValue("5.5");
		list.add(lr);
		LabResult lr2 = new LabResult();
		lr2.setValue("6.5");
		list.add(lr2);
		LabResult lr3 = new LabResult();
		lr3.setValue("7.5");
		list.add(lr3);
		LabResult lr4 = new LabResult();
		lr4.setValue("8.5");
		list.add(lr4);
		
		StatsFinder sf = new StatsFinder(list);
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(7), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
	}
	
	@Test
	public void statsFinderShouldReturnStandardDeviationWithNullValuesNotCounted(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LabResult lr = new LabResult();
		lr.setValue("5.5");
		list.add(lr);
		LabResult lr2 = new LabResult();
		lr2.setValue("6.5");
		list.add(lr2);
		LabResult lr3 = new LabResult();
		lr3.setValue("7.5");
		list.add(lr3);
		LabResult lr4 = new LabResult();
		lr4.setValue("8.5");
		list.add(lr4);
		list.add(new LabResult());
		
		StatsFinder sf = new StatsFinder(list);
		assertEquals(new Double(1.118033988749895),sf.getStandardDeviation());
    	assertEquals(new Double(7), sf.getMean());
    	assertEquals(new Double(1.25), sf.getVariance());
	}
	
	@Test
	public void statsFinderShouldReturnNullWhenNoListGiven(){
		StatsFinder sf = new StatsFinder(new ArrayList<IGetStats>());
		assertNull(sf.getStandardDeviation());
		assertNull(sf.getMean());
		assertNull(sf.getVariance());
	}
	
	@Test
	public void statsFinderShouldReturnAStringOfTheMinAndMaxValues(){
		List<IGetStats> list = new ArrayList<IGetStats>();
		LabResult lr = new LabResult();
		lr.setValue("5.5");
		list.add(lr);
		LabResult lr2 = new LabResult();
		lr2.setValue("6.5");
		list.add(lr2);
		LabResult lr3 = new LabResult();
		lr3.setValue("7.5");
		list.add(lr3);
		LabResult lr4 = new LabResult();
		lr4.setValue("8.5");
		list.add(lr4);
		
		StatsFinder sf = new StatsFinder(list);
		assertNotNull(sf.getDataSetRange());
		assertEquals("5.5 to 8.5", sf.getDataSetRange());
	}
	
	
}
