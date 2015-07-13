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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class NameTest {

	@Test
	public void getFullNameShouldCombineFirstAndLstName(){
		Name n = new Name();
		List<String> given = new ArrayList<String>();
		given.add("John");
		n.setGiven(given);
		n.setFamily("Smith");
		assertEquals("John Smith", n.getFullName());
		
	}
	
	@Test
	public void getFullNameShouldReturnFullnameWithMultipleGivenNames(){
		Name n = new Name();
		List<String> given = new ArrayList<String>();
		given.add("John");
		given.add("Henry");
		n.setGiven(given);
		n.setFamily("Smith");
		assertEquals("John Henry Smith", n.getFullName());
	}
	
	@Test
	public void getFullNameWithThreeGivenNames(){
		Name n = new Name();
		List<String> given = new ArrayList<String>();
		given.add("John");
		given.add("Henry");
		given.add("James");
		n.setGiven(given);
		n.setFamily("Smith");
		assertEquals("John Henry James Smith", n.getFullName());
	}
	
}
