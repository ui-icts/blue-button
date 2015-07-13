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

import org.junit.Test;

public class ScheduleTest {

	@Test
	public void getDisplayValueShouldBeEmptyString() {
		Schedule s = new Schedule();
		
		assertEquals("", s.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldOnlyBeType() {
		Schedule s = new Schedule();
		s.setType("frequency");
		
		assertEquals("frequency: ", s.getDisplayValue());
	}
	@Test
	public void getDisplayValueShouldIncludeTypeAndPeriodValue() {
		Schedule s = new Schedule();
		s.setType("frequency");
		s.setPeriod_value("12");
		
		assertEquals("frequency: 12", s.getDisplayValue());
	}
	@Test
	public void getDisplayValueShouldIncludeTypeAndPeriodValueAndPeriodUnits() {
		Schedule s = new Schedule();
		s.setType("frequency");
		s.setPeriod_value("12");
		s.setPeriod_unit("hours");
		
		assertEquals("frequency: 12hours", s.getDisplayValue());
	}
	
	@Test
	public void getDisplayValueShouldIncludePeriodValueAndPeriodUnits() {
		Schedule s = new Schedule();
		s.setPeriod_value("12");
		s.setPeriod_unit("hours");
		
		assertEquals("12hours", s.getDisplayValue());
	}
	@Test
	public void getDisplayValueShouldIncludeTypeAndPeriodUnits() {
		Schedule s = new Schedule();
		s.setType("frequency");
		s.setPeriod_unit("hours");
		
		assertEquals("frequency: hours", s.getDisplayValue());
	}

}
