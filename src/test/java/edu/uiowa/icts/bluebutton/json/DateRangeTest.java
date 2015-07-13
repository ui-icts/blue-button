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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateRangeTest {
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");

	@Before
    public void setup() throws FileNotFoundException, IOException {
        // freeze time
        DateTime dt= format.parseDateTime("2015-02-04");
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
    }
    
    @After
    public void tearDown() throws Exception {
    	// unfreeze time
    	DateTimeUtils.setCurrentMillisSystem();
    }
    
	@Test
	public void isActiveInTheLastYearShouldReturnFalseBecauseNeitherStartNorEndDate() {
		DateRange dr = new DateRange();
		
		assertFalse(dr.isActiveIntheLastYear());
	}
	
	
	@Test
	public void isActiveInTheLastYearForFullDateAndTimeShouldReturnTrueBecauseStartDateInTheLastYearAndNoEndDate() {
		DateRange dr = new DateRange();
		dr.setStart("2014-05-28T05:00:00.000Z");
		
		assertTrue(dr.isActiveIntheLastYear());
	}
	
	@Test
	public void isActiveInTheLastYearShouldReturnFalseBecauseHasEndDate() {
		DateRange dr = new DateRange();
		dr.setStart("2014-05-28T05:00:00.000Z");
		dr.setEnd("2014-12-28T05:00:00.000Z");
		
		assertFalse(dr.isActiveIntheLastYear());
	}
	
	@Test
	public void isActiveInTheLastYearForFullDateTimeShouldReturnTrueBecauseStartDateIsExactlyOneYear() {
		DateRange dr = new DateRange();
		dr.setStart("2014-02-05T05:00:00.000Z");
		
		assertTrue(dr.isActiveIntheLastYear());
	}
	
	@Test
	public void isActiveInTheLastYearForFullDateTimeShouldReturnFalseBecauseStartDateIsExactlyOneYearPlusOneDay() {
		DateRange dr = new DateRange();
		dr.setStart("2014-02-04T00:00:00.000Z");
		
		assertFalse(dr.isActiveIntheLastYear());
	}
}
