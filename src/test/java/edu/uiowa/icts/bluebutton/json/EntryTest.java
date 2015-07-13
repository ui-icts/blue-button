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

public class EntryTest {
	
	@Test
	public void encountersShouldReturnDispalyDate() {
		Entry e = new Entry();
		e.setDate("2013-09-20T05:00:00.000Z");
		assertEquals("2013-09-20", e.getDisplayDate());
	}
	@Test
	public void encountersShouldReturnDateInMillis() {
		Entry e = new Entry();
		e.setDate("2013-09-20T00:00:00.000Z");
		assertEquals(new Long(1379653200000L), e.getDateInMillis());
	}
	
}
