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

import java.util.List;

import org.joda.time.DateTime;

import edu.uiowa.icts.bluebutton.json.Medication;

public class MedicationsMetaFinder {

	private List<Medication> list;
	
	public MedicationsMetaFinder(List<Medication> list){
		this.list = list;
	}
		
	public String getRecentMedications(){

		for(Medication m : this.list){
			if(m.getStartDateInMillis() != null && m.getStartDateInMillis() >= new DateTime().getMillis() -31556952000L){
				return "been prescribed medications";
			}
		}
		return "not had any medications prescribed";
	}
	
}
